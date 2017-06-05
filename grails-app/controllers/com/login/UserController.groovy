package com.login

import com.login.co.UpdateProfileCO
import com.login.co.UserSearchCO
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.web.multipart.MultipartFile


class UserController {

    UserService userService
    def springSecurityService

    def index() {
        def user = springSecurityService.currentUser
        render view: "index", model: [user: user]
    }


    @Secured(['ROLE_ADMIN'])
    def usersList(UserSearchCO userSearchCO) {
        userSearchCO = userSearchCO ?: new UserSearchCO([max: 5, offset: 0])
        List<User> users = User.search(userSearchCO).list()
        render(view: 'usersList', model: [users: users,
                                          count: User.count()])
    }

    @Secured(['ROLE_ADMIN'])
    def toggleActive(Long userId) {
        User user = User.get(userId)
        if (user.isActive) {
            User.executeUpdate("update User set isActive=:active where id=:id", [active: false, id: userId])
        } else {
            User.executeUpdate("update User set isActive=:active where id=:id", [active: true, id: userId])
        }
        redirect(action: 'usersList')
    }

    def updateProfile() {
        User user = session.user
        if (User.findByUsername(params.userName)) {
            flash.message = "UserName already exist please try different username "
            redirect(controller: "user", action: "editProfile")
        } else {
            UpdateProfileCO profileCO = new UpdateProfileCO()
            bindData(profileCO, params)
            profileCO.profilePic = params.profilePic.getBytes()
            boolean result = userService.updateUser(user, profileCO)
            if (result) {
                flash.success = "User Update Success"
                redirect(controller: "user", action: "index")

            } else {
                flash.error = "User Update Failed"
                redirect(controller: "user", action: "index")
            }
        }
    }

    def updateImage() {
        User user = session.user
        MultipartFile file = params.file
        String extension = '.' + file.originalFilename.tokenize('.').last()
        String folderPath = grailsApplication.config.resource.images.folderPath
        File directory = new File(folderPath)
        String fullPath = folderPath + user.id + extension
        boolean result = false
        if (!directory.exists()) {
            directory.mkdirs()
        }
        if (file.empty) {
            flash.message = "File can't be empty"
        } else {
            UpdateProfileCO userCO = new UpdateProfileCO()
            userCO.photoUrl = fullPath
            result = userService.updateUser(user, userCO)
            file.transferTo(new File(fullPath))
        }
        return result
    }

}
