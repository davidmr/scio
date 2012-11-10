class UrlMappings {

	static mappings = {

		"/$controller/$action?/$id?"{
			constraints {
				controller(validator: { it != "scio" }) //excludes scio controller from default mapping
			}
		}

		"/scio/$user/$id"{
			controller = "scio"
			action = "show"
		}

		"/scio/$user/$branchname/$id"{
			controller = "scio"
			action = "branch"
		}

		"/scio/$user/$branchname/$id/$version"{
			controller = "scio"
			action = "version"
		}

		"/scio/$user/create"{
			controller = "scio"
			action = "create"
		}

		"/scio/clone/$user/$id"{
			controller = "scio"
			action = "clone"
		}


		"/"(controller: "home", action: "index")
		"500"(view:'/error')
	}
}
