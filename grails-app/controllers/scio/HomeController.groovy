package scio

class HomeController {
	
	def springSecurityService

    def index() {
		
	}
	
	def user(){
		def username = springSecurityService.principal.username
		def user = User.findByUsername(username)
		def scioList = Scio.findAllByOwner(user) 
		
		[username : username, scioList: scioList]
	}
	
}
