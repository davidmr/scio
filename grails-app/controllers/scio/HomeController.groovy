package scio

import grails.plugins.springsecurity.Secured

class HomeController {
	
	def springSecurityService

    def index() {
		
	}
	
	@Secured(['ROLE_USER'])
	def user(){
		def username = springSecurityService.principal.username
		def user = User.findByUsername(username)
		def scioList = Scio.findAllByOwner(user) 
		
		[username : username, scioList: scioList]
	}
	
}
