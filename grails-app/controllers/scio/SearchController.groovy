package scio

class SearchController {

    def listScios() { }
	
	def searchByTag() {
		if (params.tag) {
			def tagsText = params.tag.replaceAll("[ ]+"," ")
			def tagsList = tagsText.split(" ")
			def scioList = findByTag(tagsList, 5)
			
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
	
	List findByTag(tagsList, max) {
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
	
}