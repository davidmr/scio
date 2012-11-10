package scio

class ScioTagLib {
	
	def renderBBCodeService
	
	def snapshotDate = { attrs ->
		out << g.formatDate(format: "yyyy-MM-dd HH:mm", date: attrs.snapshot.dateCreated)
	}
	
	def renderSnapshot = { attrs ->
		out << renderBBCodeService.render(attrs.snapshot.content)
	}
	
}
