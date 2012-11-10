<g:each in="${scioList}" var="scio">
	<g:link controller="scio" action="show" id="${scio.id}" class="decorated">
		<h4 class="scio-color-green">${scio.title}</h4>
	</g:link>
	<p>
		<span class="font-little">${scio.owner}</span>
	</p>          			
</g:each>