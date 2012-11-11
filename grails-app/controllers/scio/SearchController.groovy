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
	
	@Secured(['ROLE_USER'])
	def searchMineByTag() {
		if (params.tag) {
			def user = loggedUser()
			def tagsText = params.tag.replaceAll("[ ]+"," ")
			def tagsList = tagsText.split(" ")
			def scioList = findMineByTag(user, tagsList, 5)
			
			render(template: "listScios", model: [scioList: scioList])
		}
	}
	
	def listByTag() {
		if (params.tag) {
			def scioList = findByTag([params.tag], 10)
			User user = loggedUser()
			def followingTag = user ? user.followsTag(params.tag) : false
			render(view: "listScios", model: [tag: params.tag, scioList: scioList, followingTag : followingTag])
		}
		return
	}
	
	def searchFeatured() {
		def scioList = Scio.list([sort: 'recommendations', order: 'desc', max: 5])
		render(template: "listScios", model: [scioList : scioList])
	}
	
	@Secured(['ROLE_USER'])
	def searchMine() {
		def user = loggedUser()
		def scioList = Scio.findAllByOwner(user)
		render(template: "listScios", model: [scioList : scioList])
	}
	
	@Secured(['ROLE_USER'])
	def searchMonthsMine() {
		def user = loggedUser()
		def monthList = []
		render(template: "listMonths", model: [scioList : monthList])
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