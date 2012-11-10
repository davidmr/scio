import scio.Role
import scio.Scio
import scio.Tag
import scio.User
import scio.UserRole

class BootStrap {

    def init = { servletContext ->
		environments{
			development{
				User user = new User(fullname: 'scio', username: 'scio', password: 'password', enabled: true).save(failOnError: true)
				Role roleUser = new Role(authority: 'ROLE_USER').save(failOnError: true)
				UserRole.create(user, roleUser, true)
				
				Tag tag1 = new Tag(name: "tag1").save(failOnError: true)
				Tag tag2 = new Tag(name: "abc").save(failOnError: true)
				Tag tag3 = new Tag(name: "123").save(failOnError: true)
				Tag tag4 = new Tag(name: "tag100").save(failOnError: true)
				
				Set tags = [tag1, tag2, tag3, tag4] as Set
				
				Scio example = new Scio(title: 'Title', owner: user)
				example.save(failOnError: true)
				example.init("This is the content of the scio", tags)
				example.save(failOnError: true)
			}
		}
    }
    def destroy = {
    }
}
