package scio

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
		'[quote](.*?)[/quote]' : '<blockquote>$1</blockquote>'
	]

	def render(String bbcode) {
		def result = bbcode.replaceAll(/\n/, "")
		result = result.replaceAll(/</,"&lt;")
		result = result.replaceAll(/>/, "&gt;")
		map.each { key, value ->
			def escapedKey = key.replaceAll('\\[','\\\\[').replaceAll('\\]','\\\\]')
			result = result.replaceAll(escapedKey, value)
		}
		return result.trim()
	}
}
