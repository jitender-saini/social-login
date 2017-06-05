package com.login


class ApplicationInterceptor {

    ApplicationInterceptor() {
//        matchAll().excludes(controller: '*')
    matchAll()
        match(controller:'*')
    }
//
//    boolean before() {
//        if (!session['user']) {
//            redirect controller: "login", action: "index"
//        }
//        true
//    }

    boolean before() {
        log.info "param: ${params}"
        println ">>>>>>"+params
        true
    }

    void afterView() {
        // no-op
    }
}
