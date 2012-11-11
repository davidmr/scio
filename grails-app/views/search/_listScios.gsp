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
		<img src="${resource(dir: 'images/icons', file: 'user.png')}" />
		<span class="font-little">${scio?.owner?.username}</span> -
		
		<img src="${resource(dir: 'images/icons', file: 'calendar.png')}" />
		<span class="font-little">
			<g:snapshotDate snapshot="${scio?.content()}" />
		</span>
	</p>
</g:each>