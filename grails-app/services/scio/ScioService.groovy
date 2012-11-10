package scio

class ScioService {
	
	static transactional = true

    def create(String title, String content, String tags, User owner) {
		Scio scio = new Scio(title: title, owner: owner).save(failOnError: true)
		scio.init(content, findTags(tags))
		scio.save(failOnError: true)
    }
	
	private Set findTags(String tags){
		def tagList = tags ? tags.replaceAll(/[ ]+/, ' ').split(' ') : []
		tagList.collect { Tag.findOrSaveByName(it) } as Set
	}
}
