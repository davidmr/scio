package scio

class Branch {
	
	static belongsTo = [scio : Scio]
	
	String name
	
	Snapshot head
	
	public String defaultContent(){
		head.content
	}
	
    static constraints = {
		name(blank: false)
		head(nullable: false)
    }
}
