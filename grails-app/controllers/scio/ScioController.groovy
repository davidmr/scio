package scio

class ScioController {

	def springSecurityService

	def scioService

	def show(){
	}

	def branch(){
	}

	def version(){
	}

	def create(){
	}

	def docreate(CreateSCIOCommand scioCommand){
		if(scioCommand.hasErrors()){
			render(view: "create", model: [scioCommand : scioCommand])
		}else{
			User owner = User.findByUsername(springSecurityService.principal.username)
			Scio scio = scioService.create(scioCommand.title, scioCommand.content, scioCommand.tags, owner)
			redirect action: "show", params : [id : scio.id]
		}
	}

	def clone(){
	}
}

class CreateSCIOCommand{

	String title

	String content
	
	String tags

	static constraints = {
		title(blank: false)
		content(blank: false)
		tags(nullable: true, blank: true)
	}
}
