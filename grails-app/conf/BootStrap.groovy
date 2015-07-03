import com.lftechnology.findMe.auth.Auth
import com.lftechnology.findMe.auth.UserRole
import com.lftechnology.findMe.auth.UserRoleAuth

class BootStrap {

    def init = { servletContext ->
        def adminRole = Auth.findByAuthority('ROLE_ADMIN') ?: new Auth(authority: 'ROLE_ADMIN').save(failOnError: true)
        def adminUser = UserRole.findByUsername('hello@findme.com') ?: new UserRole(username:'hello@findme.com', password:'findme').save(failOnError:true)
        UserRoleAuth.create(adminUser,adminRole,true)
    }
    def destroy = {
    }
}
