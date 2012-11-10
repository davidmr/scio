package scio

class ScioService {
	
	static transactional = true

    def create(String title, String content, String tags, User owner) {
		def tagSet = findTags(tags)
		Scio scio = new Scio(title: title, owner: owner).save(failOnError: true)
		scio.init(content, tagSet)
		scio.save(failOnError: true)
    }
	
	private Set findTags(String tags){
		def tagList = tags ? tags.replaceAll(/[ ]+/, ' ').trim().split(' ') : []
		tagList.collect { Tag.findOrSaveByName(it) } as Set
	}
}
