package social.login

import com.login.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils

class ApplicationTagLib {
//    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    SpringSecurityService securityService
    static defaultEncodeAs = "raw"

    static namespace = "ls"


    def userImage = { attr, body ->
        User user = User.get(attr.id)
        if (user.photoUrl) {
            out << " <img src='${user.photoUrl}' style='height: 200px;'>"
        } else {
            out << body()
        }
    }

}
