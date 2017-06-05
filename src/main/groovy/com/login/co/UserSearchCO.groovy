package com.login.co

import grails.validation.Validateable

class UserSearchCO extends SearchCO implements Validateable {
    Boolean isActive

    static constraints = {
        isActive nullable: true
    }
}
