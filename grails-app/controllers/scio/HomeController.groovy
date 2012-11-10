package scio

class HomeController {
	
	def springSecurityService

    def index() {
		
	}
	
	def user(){
		[username : springSecurityService.principal.username]
	}
	
}
