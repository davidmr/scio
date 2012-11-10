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
	
	def cloneScio(Integer originalId, User owner){
		Scio original = Scio.get(originalId)
		Scio clone = new Scio(title: original.title, owner : owner)
		clone.tags = new HashSet(original.tags)
		clone.head = original.head
		clone.save(failOnError: true)
	}
	
	def editScio(Integer scioId, String content, String tags) {
		Scio scio = Scio.get(scioId)
		scio.addSnapshot(content)
		def tagSet = findTags(tags)
		scio.tags = tagSet
		scio.save(failOnError: true)
	}
}
