import scio.Role
import scio.User
import scio.UserRole

class BootStrap {

    def init = { servletContext ->
		environments{
			development{
				User user = new User(username: 'scio', password: 'password', enabled: true).save(failOnError: true)
				Role roleUser = new Role(authority: 'ROLE_USER').save(failOnError: true)
				UserRole.create(user, roleUser, true)
			}
		}
    }
    def destroy = {
    }
}
