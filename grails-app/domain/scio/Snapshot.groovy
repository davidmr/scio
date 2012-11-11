package scio

class Snapshot {

	String content

	Snapshot previous

	Date dateCreated
	
	String monthCreated
	
	public List contentLines(){
		content.readLines()
	}

	static constraints = {
		content(blank: false)
		dateCreated(blank: false)
		previous(nullable: true)
	}

	static mapping = {
		content(type: 'text')
		monthCreated formula: "MONTHNAME(DATE_CREATED)"
	}
	
	String getMonth() {
		return "December"
	}
	
}
