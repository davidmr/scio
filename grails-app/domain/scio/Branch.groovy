package scio

class Branch {
	
	static belongsTo = [scio : Scio]
	
	static hasMany = [snapshots : Snapshot]
	
	String name
	
	Snapshot head
	
    static constraints = {
		name(blank: false)
		head(nullable: true)
    }
}
