package scio

class UserService {

    def createUser(UserRegistrationCommand command) {
		def user = new User(command.properties)
		if(user.save(flush: true)){
			Role userRole = Role.findByAuthority("ROLE_USER")
			def ur = UserRole.create(user, userRole, true)
			if(ur.hasErrors()){
				throw new IllegalStateException("UserRole couldn't be saved")
			}
		}	
		return user
    }
}
