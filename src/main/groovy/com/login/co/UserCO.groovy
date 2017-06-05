package com.login.co

import grails.validation.Validateable


class UserCO implements Validateable{
    String name
    String photoUrl
    String email
    String username
    String password

//    static constraints = {
//        fullName nullable: false
//        email email: true, unique: true, blank: true
//        username unique: true, blank: true
//        photoUrl nullable: true
//        password nullable: true
//    }


}
