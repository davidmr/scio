package scio

class Snapshot {

	String content

	Snapshot previous

	Date dateCreated

	static constraints = {
		content(blank: false)
		previous(nullable: true)
	}

	static mapping = {
		content(type: 'text')
	}
	
}
