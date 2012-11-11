package scio

class UserTag {
	
	User user
	
	Tag tag
	
    static constraints = {
		user(nullable: false)
		tag(nullable: false)
    }
}
