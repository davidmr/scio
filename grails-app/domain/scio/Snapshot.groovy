package scio

class Snapshot {

	static belongsTo = [ branch : Branch]

	String content

	static constraints = { content(blank: false) }

	static mapping = { content(type: 'text') }
}
