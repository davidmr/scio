package scio

import java.util.concurrent.ConcurrentSkipListMap.HeadIndex;

class Scio {

	static hasMany = [tags : Tag]

	String title

	User owner
	
	Integer recommendations = 0
	
	Snapshot head

	public void init(String content, Set tags){
		Snapshot snapshot = new Snapshot(content : content).save(failOnError: true)
		this.head = snapshot
		this.tags = tags
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
	
	public boolean hasSnapshot(Integer snapshotId){
		findSnapshot(snapshotId) != null
	}
	
	public boolean canEdit(User user){
		owner.username == user?.username
	}
	
	public void addSnapshot(String content){
		Snapshot newSnapshot = new Snapshot(content: content, previous: head).save(failOnError: true)
		head = newSnapshot
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
	
	public Snapshot content(){
		head
	}
	
	public void addRecommendation() {
		recommendations++
	}

	static constraints = {
		title(blank: false)
		owner(nullable: false)
	}
}
