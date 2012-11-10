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
			snapshot = snapshot.previous
		}
		history
	}
	
	public boolean hasSnapshot(Integer snapshotId){
		findSnapshot(snapshotId) != null
	}
	
	public Snapshot findSnapshot(Integer snapshotId){
		Snapshot snapshot = head
		while(snapshot != null){
			if(snapshot.id == snapshotId){
				return snapshot
			}
			snapshot = snapshot.previous
		}
		return null
	}
	
    static constraints = {
		name(blank: false)
		head(nullable: false)
    }
	
}