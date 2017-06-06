package com.login.co

import grails.validation.Validateable

class UserSearchCO extends SearchCO implements Validateable {
    Boolean enabled

    static constraints = {
        enabled nullable: true
    }
}
