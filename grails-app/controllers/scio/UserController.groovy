package scio

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST"]
	
	def userService

    def index() {
    }

    def create() {
        [user: new User(params)]
    }
	
	def save = { UserRegistrationCommand urc ->
		if (urc.hasErrors()) {
			render(view: "create", model: [userInstance: urc])
		} else {
			def userInstance = userService.createUser(urc)
			if (userInstance.hasErrors()) {
	            render(view: "create", model: [userInstance: userInstance])
	            return
	        }
	        flash.message = message(code: 'welcome.message')
	        redirect(controller: "login", action: "auth")
		}
    }

}

class UserRegistrationCommand {
	
	String fullname
	String username
	String password
	String passwordRepeat

	static constraints = {
		fullname(nullable: true)
		
		username(size: 3..20)
		
		password(size: 6..20, blank: false,
			validator: { passwd, urc ->
				return passwd != urc.username
			})

		passwordRepeat(nullable: false,
			validator: { passwd2, urc ->
				return passwd2 == urc.password
			})
	}
	
	boolean isEnabled() {
		return true
	}
	
}

	
