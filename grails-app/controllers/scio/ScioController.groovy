package scio

class ScioController {

	def springSecurityService

	def scioService

	def show(String id) {
		Scio scio = safeGetScio(id)
		User user = loggedUser()
		[scio : scio, canEdit: scio.canEdit(user)]
	}

	def version(VersionSCIOCommand versionCommand){
		if(versionCommand.hasErrors()){
			render(view : "version", model : [versionCommand : versionCommand])
		}else{
			Scio scio = Scio.get(versionCommand.id)
			Snapshot snapshot = scio.findSnapshot(versionCommand.snapshot)
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

	def edit(String id){
		Scio scio = safeGetScio(id)
		User user = loggedUser()
		if(scio.canEdit(user)){
			return [editCommand : scioToEditCommand(scio)]
		}else{
			redirect controller: "login", action: "denied"
		}
	}

	def doedit(EditSCIOCommand editCommand){
		if(editCommand.hasErrors()){
			render(view: "edit", model : [editCommand : editCommand])
		}else{
			User user = loggedUser()
			Scio scio = Scio.get(editCommand.id)
			if(scio.canEdit(user)){
				scioService.editScio(editCommand.id, editCommand.content, editCommand.tags)
				if(scio.isClone()){
					redirect action: "afteredit", params : [id: scio.id]
				}else{
					redirect action: "show", params : [id: scio.id]
				}
			}else{
				redirect controller: "login", action: "denied"
			}
		}
	}

	def afteredit(String id){
		[scio : safeGetScio(id)]
	}

	def toshow(String id){
		redirect action: "show", params : [id : id]
	}

	def requestmerge(String id){
		Scio scio = safeGetScio(id)
		User owner = loggedUser()
		def merge = new MergeRequest(destination: scio.cloneOf, source: scio.head, owner: owner)
		if(merge.save()){
			flash.message = "A merge request has been seend to ${scio.cloneOf.owner.username}"
			redirect action: "show", params : [id : id]
		}else{
			render(view: "afteredit", model: [merge : merge, scio : scio])
		}
	}
	
	private User loggedUser(){
		if(springSecurityService.loggedIn){
			User.findByUsername(springSecurityService.principal.username)
		}
	}

	private EditSCIOCommand scioToEditCommand(Scio scio){
		return new EditSCIOCommand(
		id: scio.id,
		title: scio.title,
		content: scio.content().content,
		tags: scio.tags*.name.join(' '))
	}

	private Scio safeGetScio(String id){
		if(id){
			try{
				Integer scioId = id as Integer
				Scio scio = Scio.get(scioId)
				if(scio != null){
					return scio
				}
			}catch(NumberFormatException e){}
		}
		throw new SCIONotFoundException()
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

	Integer snapshot

	static constraints = {
		id(nullable: false)
		snapshot(nullable: false, validator : { val, obj ->
			if(!obj.id){
				//does not validate if id not specified
				return true
			}

			def scio = Scio.get(obj.id)
			if(scio && scio.hasSnapshot(val)){
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

class EditSCIOCommand {

	Integer id

	String title

	String content

	String tags

	static constraints = {
		id(validator : {
			Scio scio = Scio.get(it)
			if(it && scio){
				return true
			}else{
				return "nullable"
			}
		})
		title(blank: false)
		content(blank: false)
		tags(nullable: true, blank: true)
	}
}
