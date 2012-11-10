package scio

import grails.converters.JSON

class TagController {

	def search(String q) {
		def tags = Tag.findAllByNameLike("${q}%")
		render(contentType: "text/json"){ options = (tags*.name << q).unique() }
	}
}
