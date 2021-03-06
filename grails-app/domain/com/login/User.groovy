package com.login

import com.login.co.UserSearchCO
import grails.plugin.springsecurity.SpringSecurityService
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

//@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	SpringSecurityService springSecurityService

	String username
	String password
	String name
	String photoUrl
	String email
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	User() {}
	User(String username, String password) {
		this()
		this.username = username
		this.password = password
	}

	Set<Role> getAuthorities() {
		(UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		println "password encoder: ${springSecurityService}"
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		password password: true
		username unique: true
		email email: true, unique: true, nullable: true
		photoUrl nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	static namedQueries = {
		search { UserSearchCO co ->
			co.q = co.q ?: ''
			or {
				ilike('name', "%${co.q}%")
				ilike('username', "%${co.q}%")
				ilike('email', "%${co.q}%")
			}
			if (co.sort) {
				order(co.sort, co.order)
			}
			if (co.enabled != null) {
				if (co.enabled) {
					eq('enabled', true)
				} else {
					eq('enabled', false)
				}
			}
			maxResults(co.max)
			firstResult(co.offset)
		}
	}
}
