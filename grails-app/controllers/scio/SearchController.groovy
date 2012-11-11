package scio

import grails.plugins.springsecurity.Secured

class SearchController {
	
	def springSecurityService

    def listScios() { }
	
	def searchByTag() {
		if (params.tag) {
			def tagsText = params.tag.replaceAll("[ ]+"," ")
			def tagsList = tagsText.split(" ")
			def scioList = findByTag(tagsList, 5)
			
			render(template: "listScios", model: [scioList: scioList])
		}
	}
	
	def searchMineByTag() {
		if (params.tag) {
			def username = springSecurityService.principal.username
			def user = User.findByUsername(username)
			def tagsText = params.tag.replaceAll("[ ]+"," ")
			def tagsList = tagsText.split(" ")
			def scioList = findMineByTag(user, tagsList, 5)
			
			render(template: "listScios", model: [scioList: scioList])
		}
	}
	
	def listByTag() {
		if (params.tag) {
			def scioList = findByTag([params.tag], 10)
			render(view: "listScios", model: [tag: params.tag, scioList: scioList])
		}
		return
	}
	
	def searchFeatured() {
		def scioList = Scio.list([sort: 'recommendations', order: 'desc', max: 5])
		render(template: "listScios", model: [scioList : scioList])
	}
	
	def searchMine() {
		def username = springSecurityService.principal.username
		def user = User.findByUsername(username)
		def scioList = Scio.findAllByOwner(user)
		render(template: "listScios", model: [scioList : scioList])
	}
	
	def searchMonthsMine() {
		def username = springSecurityService.principal.username
		def user = User.findByUsername(username)
		def monthList = []
		render(template: "listMonths", model: [scioList : monthList])
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
		redirect action: "listByTag", params: [tag : tag]
	}
	
	private List findByTag(tagsList, max) {
		def scioList = Scio.createCriteria().listDistinct() {
			tags {
				or {
					tagsList.each { tag ->
						eq('name', tag)
					}
				}
			}
			maxResults(max)
		}
		return scioList
	}
	
	private List findMineByTag(user, tagsList, max) {
		def scioList = Scio.createCriteria().listDistinct() {
			and {
				eq('owner', user)
				tags {
					or {
						tagsList.each { tag ->
							eq('name', tag)
						}
					}
				}
			}
			maxResults(max)
		}
		return scioList
	}
	
	private User loggedUser(){
		if(springSecurityService.loggedIn){
			User.findByUsername(springSecurityService.principal.username)
		}
	}
	
}