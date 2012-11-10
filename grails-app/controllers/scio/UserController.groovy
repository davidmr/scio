package scio

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST"]

    def index() {
    }

    def create() {
        [user: new User(params)]
    }
	
	def save = { UserRegistrationCommand urc ->
		if (urc.hasErrors()) {
			render(view: "create", model: [userInstance: urc])
		} else {
			def userInstance = new User(urc.properties)
			if (!userInstance.save(flush: true)) {
	            render(view: "create", model: [userInstance: userInstance])
	            return
	        }
	        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
	        redirect(controller: "login", action: "index", id: userInstance.id)
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
		
		password(size: 6..8, blank: false,
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

	
