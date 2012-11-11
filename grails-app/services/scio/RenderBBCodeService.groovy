package scio

import grails.plugin.cache.Cacheable

//Based on http://blog.hydronitrogen.com/663/groovygrails-bbcode-markup-tutorial/
class RenderBBCodeService {

	static map = [
		'[b](.*?)[/b]':'<b>$1</b>',
		'[i](.*?)[/i]':'<i>$1</i>',
		'[u](.*?)[/u]':'<u>$1</u>',
		'[img](.*?)[/img]':'<img src="$1" />',
		'[url=(.*?)](.*?)[/url]':'<a href="$1" target="_blank">$2</a>',
		'[size=(.*?)](.*?)[/size]':'<span style="font-size:$1em;">$2</span>',
		'[list](.*?)[/list]' : '<ul>$1</ul>',
		'[list=(.*?)](.*?)[/list]' : '<ol start="$1">$2</ol>',
		'[item](.*?)[/item]' : '<li>$1</li>',
		'[quote](.*?)[/quote]' : '<blockquote>$1</blockquote>',
		'[break][/break]' : "<br />"
	]

	@Cacheable('bbcode')
	def render(String bbcode) {
		def result = bbcode.replaceAll(/</,"&lt;")
		result = result.replaceAll(/>/, "&gt;")
		
		result = result.readLines().join("<br />")
		result = result.replaceAll(/[\s]+/, " ")
		
		map.each { key, value ->
			def escapedKey = key.replaceAll('\\[','\\\\[').replaceAll('\\]','\\\\]')
			result = convert(result, escapedKey, value)
		}
		return result.trim()
	}
	
	private String convert(String original, String key, String value){
		String last = original
		String current = original.replaceAll(key, value)
		while(current != last){
			last = current
			current = current.replaceAll(key, value)
		}
		current
	}
}
