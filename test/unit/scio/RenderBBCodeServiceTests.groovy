package scio



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RenderBBCodeService)
class RenderBBCodeServiceTests {

    void testRender() {
		String bbcode = """[b]strong[/b]
[i]italic[/i]
[u]underline[/u]
[img]aaa[/img]
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
        def html = service.render(bbcode)
		
		String expected = """<b>strong</b>
<i>italic</i>
<u>underline</u>
<img src="aaa" />
<a href="link_url" target="_blank">link</a>
<span style="font-size:2em;">big text</span>
<span style="font-size:1em;">normal text</span>
<span style="font-size:0.5em;">small text</span>
<ul>
<li>item</li>
<li>item 2</li>
</ul>
<ol start="1">
<li>item 1</li>
<li>item 2</li>
</ol>
&lt;script&gt;alert('hola');&lt;/script&gt;"""
		assert html.replaceAll(/\n/, "") == expected.replaceAll(/\n/, "")
		
    }
}
