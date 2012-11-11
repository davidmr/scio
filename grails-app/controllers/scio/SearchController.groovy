package scio

import grails.plugins.springsecurity.Secured

class SearchController {
	
	def springSecurityService

    def listScios() { }
	
	def searchByTag() {
		def scioList = []
		if (params.criteria) {
			def tagsText = params.criteria.replaceAll("[ ]+"," ")
			def tagsList = tagsText.split(" ")
			scioList = findByTag(tagsList, 5)
		}
		render(template: "listScios", model: [scioList: scioList])
	}
	
	@Secured(['ROLE_USER'])
	def searchMineByTag() {
		def scioList = []
		if (params.criteria) {
			def user = loggedUser()
			def tagsText = params.criteria.replaceAll("[ ]+"," ")
			def tagsList = tagsText.split(" ")
			scioList = findMineByTag(user, tagsList, 5)
		}
		render(template: "listScios", model: [scioList: scioList])
	}
	
	def searchMineByMonth() {
		def scioList = []
		if (params.criteria) {
			def user = loggedUser()
			scioList = findMineByMonth(user, params.criteria, 5)
		}
		render(template: "listScios", model: [scioList: scioList])
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
		def monthList = Scio.withCriteria {
			and {
				eq('owner', user)
				head {
					projections {
						groupProperty("monthCreated")
						count("id")
					}
				}
			}
		}
		
		def monthMap = monthList.inject([:]) { map, month ->
			map[month[0]] = month[1]; map
		}
		
		render(template: "listMonths", model: [monthMap : monthMap])
	}
	
	def listByTag() {
		def scioList = []
		def followingTag = false
		
		if (params.tag) {
			User user = loggedUser()
			scioList = findByTag([params.tag], 10)
			followingTag = user ? user.followsTag(params.tag) : false
		}
		render(view: "listScios", model: [tag: params.tag, scioList: scioList, followingTag : followingTag])
	}
	
	private List findByTag(tagsList, max) {
		def scioList = Scio.createCriteria().listDistinct() {
			tags {
				or {
					tagsList.each { tag ->
						like('name', "%" + tag + "%")
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
							like('name', "%" + tag + "%")
						}
					}
				}
			}
			maxResults(max)
		}
		return scioList
	}
	
	private List findMineByMonth(user, month, max) {
		def scioList = Scio.createCriteria().listDistinct() {
			and {
				eq('owner', user)
				head {
					eq('monthCreated', month)
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