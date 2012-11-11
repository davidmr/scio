package scio

class MergeRequest {
	
	Scio destination
	
	Snapshot source
	
	User owner
	
	boolean pending = true
	
	boolean accepted = false
	
	public void accept(){
		pending = false
		accepted = true
	}
	
	public void reject(){
		pending = false
		accepted = false
	}
	
	static constraints = {
		destination(nullable: false)
		source(nullable: false)
		owner(nullable: false)
	}
	
}
