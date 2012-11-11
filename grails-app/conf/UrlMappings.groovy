import scio.MergeRequestNotFoundException;
import scio.SCIONotFoundException;

class UrlMappings {

	static mappings = {

		"/$controller/$action?/$id?"{
			constraints {
			}
		}

		"/"(controller: "home", action: "index")
		"500"(view:'/error')
		"500"(controller: "home", action: "user", exception: SCIONotFoundException)
		"500"(controller: "home", action: "user", exception: MergeRequestNotFoundException)
	}
}
