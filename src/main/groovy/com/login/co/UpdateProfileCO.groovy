package com.login.co

import grails.validation.Validateable

class UpdateProfileCO implements Validateable {
   String photoUrl

    static constraints = {
        photoUrl nullable: false
    }
}
