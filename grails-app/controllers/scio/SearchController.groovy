package scio

class SearchController {

    def index() { }
	
	def searchByTag() {
		if (params.tag) {
			def tagsText = params.tag.replaceAll("[ ]+"," ")
			def tagsList = tagsText.split(" ")
			def scioList = findByTag(tagsList, 5)
			
			render(template: "listScios", model:[scioList: scioList])
		}
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