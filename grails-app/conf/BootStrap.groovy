import scio.Branch
import scio.Role
import scio.Scio
import scio.Snapshot
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
				Set tags3 = [tag1, tag3] as Set
				Set tags4 = [tag2, tag4] as Set
				
				Scio example1 = new Scio(title: 'Title 1', owner: user, recommendations: 5)
				example1.save(failOnError: true)
				
				String bbcodeContent = """[b]strong[/b]
										[i]italic[/i]
										[u]underline[/u]
										[img]https://www.google.com/images/srpr/logo3w.png[/img]
										[url=link_url]link[/url]
										[size=2]big text[/size]
										[size=1]normal text[/size]
										[size=0.5]small text[/size]
										[list]
										[item]item[/item]
										[item]item 2[/item]
										[/list]
										[list=1]
										[item]item 1[/item]
										[item]item 2[/item]
										[/list]
										<script>alert('hola');</script>"""
				
				Snapshot snapshot1 = new Snapshot(content: "First [b]content[/b]").save(failOnError: true)
				Snapshot snapshot2 = new Snapshot(content: bbcodeContent, previous: snapshot1).save(failOnError: true)
				
				Branch master = new Branch(name : "master", head: snapshot2)
				
				example1.addToBranches(master)
				example1.tags = tags1
				example1.save(failOnError: true)
				
				Scio example2 = new Scio(title: 'Title 2', owner: user, recommendations: 4)
				example2.save(failOnError: true)
				example2.init("This is the content of the scio 2", tags2)
				example2.save(failOnError: true)
				
				Scio example3 = new Scio(title: 'Title 3', owner: user, recommendations: 3)
				example3.save(failOnError: true)
				example3.init("This is the content of the scio 3", tags3)
				example3.save(failOnError: true)
				
				Scio example4 = new Scio(title: 'Title 4', owner: user, recommendations: 0)
				example4.save(failOnError: true)
				example4.init("This is the content of the scio 4", tags4)
				example4.save(failOnError: true)
			}
		}
    }
    def destroy = {
    }
}
