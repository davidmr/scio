package scio

import grails.plugins.springsecurity.Secured

class TagController {
	
	def springSecurityService

	def search(String q) {
		def tags = Tag.findAllByNameLike("${q}%")
		render(contentType: "text/json"){ options = (tags*.name << q).unique() }
	}
	
	@Secured(['ROLE_USER'])
	def follow(String tag){
		if(tag){
			Tag tagObj = Tag.findByName(tag)
			if(tagObj){
				UserTag userTag = new UserTag(user: loggedUser(), tag: tagObj).save(failOnError: true)
				flash.message = "${tag} followed"
			}
		}
		redirect controller: "search", action: "listByTag", params: [tag : tag]
	}
	
	@Secured(['ROLE_USER'])
	def unfollow(String tag){
		if(tag){
			Tag tagObj = Tag.findByName(tag)
			if(tagObj){
				UserTag userTag = UserTag.findByUser(loggedUser(), tagObj)
				userTag.delete()
				flash.message = "${tag} is no longer followed"
			}
		}
		redirect controller: "search", action: "listByTag", params: [tag : tag]
	}
	
	@Secured(['ROLE_USER'])
	def followed(){
		User user = loggedUser()
		render(template: "followed", model: [tags : user.followedTags])
	}
	
	private User loggedUser(){
		if(springSecurityService.loggedIn){
			User.findByUsername(springSecurityService.principal.username)
		}
	}
}
