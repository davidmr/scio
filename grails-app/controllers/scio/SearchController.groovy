package scio

class SearchController {

    def index() { }
	
	def searchByTag() {
		if (params.tag) {
			def tagsText = params.tag.replaceAll("[ ]+"," ")
			def tagsList = tagsText.split(" ")
			def scioList = findByTag(tagsList)
			
			render(template: "listScios", model:[scioList: scioList])
		}
	}
	
	def searchFeatured() {
		render(template: "listScios", model: [scioList : []])
	}
	
	List findByTag(tagsList) {
		def scioList = Scio.createCriteria().listDistinct() {
			tags {
				or {
					tagsList.each { tag ->
						eq('name', tag)
					}
				}
			}
		}
		return scioList
	}
	
}
