<g:each in="${scioList}" var="scio">
	<g:link controller="scio" action="show" id="${scio.id}" class="decorated">
		<h4 class="scio-color-green">${scio.title}</h4>
	</g:link>
	<div class="tags">
		<g:each in="${scio.tags}">
			<span class="tag">${it.name}</span>
		</g:each>
	</div>
	<p>
		<span class="font-little">${scio.owner}</span> - 
		<span class="font-little">
			<g:snapshotDate snapshot="${scio.masterContent()}" />
		</span>
	</p>
</g:each>