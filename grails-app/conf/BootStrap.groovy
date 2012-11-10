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
				
				Set tags = [tag1, tag2, tag3, tag4] as Set
				
				Scio example = new Scio(title: 'Title', owner: user)
				example.save(failOnError: true)
				
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
				Snapshot snapshot3 = new Snapshot(content: "First content branch b").save(failOnError: true)
				
				Branch master = new Branch(name : "master", head: snapshot2)
				Branch masterb = new Branch(name : "master_b", head: snapshot3)
				
				example.addToBranches(master)
				example.addToBranches(masterb)
				example.tags = tags
				example.save(failOnError: true)
			}
		}
    }
    def destroy = {
    }
}
