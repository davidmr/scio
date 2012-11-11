<g:if test="${tags}">
	<ul>
	<g:each in="${tags}">
		<span class="label label-success">
			<g:link controller="search" action="listByTag" params="${[tag: it.name]}" class="link-tag">${it.name}</g:link>
		</span>
	</g:each>
	</ul>
</g:if>
<g:else>
	You don't follow any tags. Search for a tag and follow it.
</g:else>