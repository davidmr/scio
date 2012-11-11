<g:if test="${requests}">
	<ul>
	<g:each in="${requests}">
		<li>
			${it.owner.username} is asking to merge. 
			<g:link controller="mergerequest" action="resolve" params="${[id: it.id]}">Resolve</g:link>
		</li>
	</g:each>
	</ul>
</g:if>
<g:else>
	You don't have merge requests
</g:else>