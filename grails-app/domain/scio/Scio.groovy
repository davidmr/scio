package scio

class Scio {

	static hasMany = [branches : Branch, tags : Tag]

	private static final String MASTER_BRANCH = "master"

	String title

	User owner

	static constraints = { 
		title(blank: false)
		owner(nullable: false)
	}
}
