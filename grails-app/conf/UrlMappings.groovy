class UrlMappings {

	static mappings = {

		"/$controller/$action?/$id?"{
			constraints {
			}
		}

		"/"(controller: "home", action: "index")
		"500"(view:'/error')
	}
}
