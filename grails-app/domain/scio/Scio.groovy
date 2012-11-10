package scio

import java.util.concurrent.ConcurrentSkipListMap.HeadIndex;

class Scio {

	static hasMany = [branches : Branch, tags : Tag]

	private static final String MASTER_BRANCH = "master"

	String title

	User owner

	public void init(String content, Set tags){
		Snapshot snapshot = new Snapshot(content : content).save(failOnError: true)
		Branch master = new Branch(name : MASTER_BRANCH, head : snapshot)
		this.addToBranches(master)
		master.save(failOnError: true)
		this.tags = tags
	}
	
	def transient getMasterBranch(){
		return MASTER_BRANCH
	}

	public Snapshot masterContent(){
		defaultContentForBranch(MASTER_BRANCH)
	}
	
	public List masterRecentHistory(){
		recentHistory(MASTER_BRANCH)
	}

	public Snapshot defaultContentForBranch(String branchName){
		def branch = branches.find { it.name == branchName }
		branch?.defaultContent()
	}

	public List recentHistory(String branchName){
		def branch = branches.find { it.name == branchName }
		branch?.recentHistory()
	}
	
	public boolean hasBranch(String branchName){
		branches.find { it.name == branchName } != null
	}
	
	public boolean hasBranchAndSnapshot(String branchName, Integer snapshot){
		def branch = branches.find { it.name == branchName }
		branch?.hasSnapshot(snapshot)
	}
	
	public Snapshot findSnapshot(String branchName, Integer snapshot){
		def branch = branches.find { it.name == branchName }
		branch?.findSnapshot(snapshot)
	}
	
	public boolean canEdit(User user){
		owner.username == user?.username
	}
	
	public void addSnapshotToBranch(String content, String branchName){
		def branch = branches.find { it.name == branchName }
		Snapshot head = branch.head
		Snapshot newSnapshot = new Snapshot(content: content, previous: head).save(failOnError: true)
		branch.head = newSnapshot
		branch.save(failOnError: true)
	}

	static constraints = {
		title(blank: false)
		owner(nullable: false)
	}
}
