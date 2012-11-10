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

	def version(VersionSCIOCommand versionCommand){
		if(versionCommand.hasErrors()){
			render(view : "version", model : [versionCommand : versionCommand])
		}else{
			Scio scio = Scio.get(versionCommand.id)
			Snapshot snapshot = scio.findSnapshot(versionCommand.branch, versionCommand.snapshot)
			[scio: scio, snapshot: snapshot]
		}
	}

	def create() {
	}

	def docreate(CreateSCIOCommand scioCommand) {
		if(scioCommand.hasErrors()){
			render(view: "create", model: [scioCommand : scioCommand])
		}else{
			User owner = loggedUser()
			Scio scio = scioService.create(scioCommand.title, scioCommand.content, scioCommand.tags, owner)
			redirect action: "show", params : [id : scio.id]
		}
	}

	def createbranch(){
	}

	def clone(CloneSCIOCommand cloneCommand){
		if(cloneCommand.hasErrors()){
			render(view: "clone", model : [cloneCommand : cloneCommand])
		}else{
			return [scio : Scio.get(params.id as Integer)]
		}
	}

	def doclone(CloneSCIOCommand cloneCommand){
		if(cloneCommand.hasErrors()){
			render(view: "clone", model : [cloneCommand : cloneCommand])
		}else{
			User owner = loggedUser()
			Scio clonedScio = scioService.cloneScio(params.id as Integer, owner)
			redirect action: "show", params : [id: clonedScio.id]
		}
	}

	private User loggedUser(){
		User.findByUsername(springSecurityService.principal.username)
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

class VersionSCIOCommand {

	Integer id

	String branch

	Integer snapshot

	static constraints = {
		id(nullable: false)
		branch(blank: false, validator : { val, obj ->
			if(!obj.id){
				//does not validate if id not specified
				return true
			}

			def scio = Scio.get(obj.id)
			if(scio && scio.hasBranch(val)){
				return true
			}else{
				return "notfound"
			}
		})
		snapshot(nullable: false, validator : { val, obj ->
			if(!obj.id || !obj.branch){
				//does not validate if id or branch not specified
				return true
			}

			def scio = Scio.get(obj.id)
			if(scio && scio.hasBranchAndSnapshot(obj.branch, val)){
				return true
			}else{
				return "notfound"
			}
		})
	}
}

class CloneSCIOCommand {

	Integer id

	static constraints = {
		id(nullable: true, validator : {
			Scio scio = Scio.get(it)
			if(it && scio){
				return true
			}else{
				return "nullable"
			}
		})
	}
}
