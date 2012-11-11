package scio

import org.htmlcleaner.HtmlCleaner
import org.htmlcleaner.TagNode

class ScioTagLib {
	
	def renderBBCodeService
	
	def snapshotDate = { attrs ->
		out << g.formatDate(format: "yyyy-MM-dd HH:mm", date: attrs.snapshot.dateCreated)
	}
	
	def renderSnapshot = { attrs ->
		out << renderBBCodeService.render(attrs.snapshot.content)
	}
	
	def renderBBCode = { attrs ->
		out << renderBBCodeService.render(attrs.text ?: "")
	}
	
	def renderSnippet = { attrs ->
		String html = renderBBCodeService.render(attrs.snapshot.content)
		HtmlCleaner cleaner = new HtmlCleaner()
		TagNode tagNode = cleaner.clean(html)
		String plain = tagNode.text
		int last = Math.min(plain.length(), 100)
		out << plain.substring(0, last) + "[...]"
	}
	
}
