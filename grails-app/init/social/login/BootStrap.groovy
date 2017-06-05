package social.login

import com.login.Role
import com.login.User
import com.login.UserRole
import com.login.enums.UserRoles

class BootStrap {

    def init = { servletContext ->
        log.info("Application Running")
//        createAdminUser()
    }

    def createAdminUser(){
        Role role = Role.findByAuthority(UserRoles.ROLE_ADMIN) ?:(new Role(authority: UserRoles.ROLE_ADMIN).save(flush:true,failOnError:true))
        User adminUser = User.findByUsername("admin") ?: (new User(username: "admin", password: "12345", name: "admin",email: "admin@ttn.com").save(flush: true,failOnError:true))
        UserRole userRole = UserRole.findByUserAndRole(adminUser, role) ?: (new UserRole(user: adminUser, role: role).save(flush: true,failOnError:true))
    }
    def destroy = {
        log.info("Application Stopped")
    }
}
