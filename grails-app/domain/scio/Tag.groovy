package scio

class Tag {

	String name

	static constraints = {
		name(blank: false, unique: true)
	}
}
