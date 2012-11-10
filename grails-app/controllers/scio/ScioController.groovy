package scio

class ScioController {

	def springSecurityService

	def scioService

	def show(Integer id) {
		if(id) {
			[scio : Scio.get(id) ]
		}else{
			render status: 404
		}
	}
	
	def searchByTag() {
		if (params.tag) {
			def tagsText = params.tag.replaceAll("[ ]+"," ")
			def tagsList = tagsText.split(" ")
			
			def scioList = Scio.createCriteria().listDistinct() {
				tags {
					or {
						tagsList.each { tag ->
							eq('name', tag)
						}
					}
				}
			}
			render(template: "list", model:[scioList: scioList])
		}
	}
	
	def searchFeatured() {
		render(template: "list", model: [scioList : []])
	}

	def branch() {
	}

	def version() {
	}

	def create() {
	}

	def docreate(CreateSCIOCommand scioCommand) {
		if(scioCommand.hasErrors()){
			render(view: "create", model: [scioCommand : scioCommand])
		}else{
			User owner = User.findByUsername(springSecurityService.principal.username)
			Scio scio = scioService.create(scioCommand.title, scioCommand.content, scioCommand.tags, owner)
			redirect action: "show", params : [id : scio.id]
		}
	}
	
	def createbranch() {
	}

	def clone() {
	}
}

class CreateSCIOCommand {

	String title

	String content
	
	String tags

	static constraints = {
		title(blank: false)
		content(blank: false)
		tags(nullable: true, blank: true)
	}
}
