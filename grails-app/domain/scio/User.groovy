package scio

class User {

	transient springSecurityService

	String fullname
	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		fullname blank: false, nullable: false
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
	
	Set<Tag> getFollowedTags(){
		UserTag.findAllByUser(this).collect { it.tag } as Set
	}
	
	public boolean followsTag(String tagName){
		Tag tag = Tag.findByName(tagName)
		UserTag userTag = UserTag.findByUserAndTag(this, tag)
		return userTag != null
	}
}
