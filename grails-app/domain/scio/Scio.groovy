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
	
	public String defaultContent(){
		def branch = branches.find { it.name == MASTER_BRANCH}
		branch.defaultContent()
	}

	static constraints = {
		title(blank: false)
		owner(nullable: false)
	}
}
