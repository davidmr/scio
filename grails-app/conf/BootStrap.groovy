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
				
				Set tags1 = [tag1, tag2] as Set
				Set tags2 = [tag3, tag4] as Set
				
				Scio scio1 = new Scio(title: 'Title 1', owner: user)
				scio1.save(failOnError: true)
				scio1.init("This is the content of the scio 1", tags1)
				scio1.save(failOnError: true)
				
				Scio scio2 = new Scio(title: 'Title 2', owner: user)
				scio2.save(failOnError: true)
				scio2.init("This is the content of the scio 2", tags2)
				scio2.save(failOnError: true)
			}
		}
    }
    def destroy = {
    }
}
