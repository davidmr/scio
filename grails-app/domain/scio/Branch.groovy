package scio

class Branch {
	
	static belongsTo = [scio : Scio]
	
	String name
	
	Snapshot head
	
	public Snapshot defaultContent(){
		head
	}
	
	public List recentHistory(){
		List history = []
		Snapshot snapshot = head
		while(snapshot != null && history.size() < 5){
			history << snapshot
			snapshot = head.previous
		}
		history
	}
	
    static constraints = {
		name(blank: false)
		head(nullable: false)
    }
}
