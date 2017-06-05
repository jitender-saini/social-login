package com.login

import com.login.co.UpdateProfileCO
import com.login.co.UserCO
import grails.transaction.Transactional

@Transactional
class UserService {

    def updateUser(User userObj, UpdateProfileCO profileCO) {
        userObj.photoUrl = profileCO.photoUrl
        if (userObj.validate()) {
            userObj.merge(failonError: true)
            return !userObj.hasErrors()
        }
    }

    def register(UserCO userCO) {
        User user = new User()
        if (userCO.email) {
            user.email = userCO.email
        }
        user.name = userCO.name
        user.photoUrl = userCO.photoUrl
        if (!userCO.password) {
            user.password = "12345"
        }
        if (userCO.username) {
            user.username = userCO.username
        } else {
            user.username = userCO.email
        }

        if (user.validate()) {
            user.save(failonError: true)
            println "service+++++++++++++++++++++++++++"
            println user.username + "username *************************"
        } else {
            println "Errrors<<<<<<<<<<<<<<"
            user.errors.each {
                println it
            }
        }

        return user.username
    }
}
