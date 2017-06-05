package com.login

import com.login.co.UserCO
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class LoginController extends grails.plugin.springsecurity.LoginController{


    UserService userService
    def springSecurityService

    @Secured('permitAll')
    def index() {
        if (springSecurityService.isLoggedIn()) {
            log.info("Hello")
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
        } else {
            log.info("Hello2 ")
            render view:'index'
        }

//        User user = session.user
//        if (user) {
//            println user
//            redirect(controller: "user", action: "index")
//        } else {
//            render view: 'index'
//        }
    }


    /** Show the login page. */
    def auth() {

        def conf = getConf()

        if (springSecurityService.isLoggedIn()) {
            redirect uri: conf.successHandler.defaultTargetUrl
            return
        }

        String postUrl = request.contextPath + conf.apf.filterProcessesUrl
        render view: 'auth', model: [postUrl: postUrl,
                                     rememberMeParameter: conf.rememberMe.parameter,
                                     usernameParameter: conf.apf.usernameParameter,
                                     passwordParameter: conf.apf.passwordParameter,
                                     gspLayout: conf.gsp.layoutAuth]
    }

    def loginHandler(String userName, String password) {
        User user = User.createCriteria().get {
            projections {
                or {
                    eq('username', userName)
                    eq('email', userName)
                }
                eq('password', password)
            }
        } as User
        if (user) {
            if (user.enabled) {
                session.user = user
                redirect(controller: "user", action: "index")
            } else {
                flash.error = "Your account is not active"
                redirect(controller: "login", action: "index")
            }
        } else {
            flash.error = "User not found"
            redirect(controller: "login", action: "index")
        }
    }

    def logout() {
        session.invalidate()
        return true
    }

    def googleLogin(UserCO userCO) {
        println "<<<<<<<<"
        User user = User.findByUsername(userCO.username)
        if (user) {
            springSecurityService.reauthenticate(user.username)
//            session.user = user
        } else {
            def username =userService.register(userCO)
            def test = User.findByUsername(username)
            println "<<<<<<<<<<test" + test
            springSecurityService.reauthenticate(username)
//            session.user = userService.register(userCO)
        }
        return true
    }

}
