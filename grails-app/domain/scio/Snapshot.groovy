package scio

class Snapshot {

	String content

	Snapshot previous

	static constraints = {
		content(blank: false)
		previous(nullable: true)
	}

	static mapping = { content(type: 'text') }
}
