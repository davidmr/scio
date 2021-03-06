<g:each in="${scioList}" var="scio">
	<div class="scio">
	<g:link controller="scio" action="show" id="${scio.id}" class="decorated">
		<h4 class="scio-color-green">${scio.title}</h4>
	</g:link>
	<div class="snippet">
		<g:renderSnippet snapshot="${scio.content()}"/>
	</div>
	<div class="tags">
		<g:each in="${scio.tags}">
			<g:link controller="search" action="listByTag" params="${[tag: it.name]}" class="link-tag">
				<g:if test="${tag && it.name.contains(tag)}">
					<span class="label label-success">${it.name}</span>
				</g:if>
				<g:else>
					<span class="label">${it.name}</span>
				</g:else>
			</g:link>
		</g:each>
	</div>
	<p>
		<img src="${resource(dir: 'images/icons', file: 'user.png')}" />
		<span class="font-little">${scio?.owner?.username}</span> -
		
		<img src="${resource(dir: 'images/icons', file: 'calendar.png')}" />
		<span class="font-little"><g:snapshotDate snapshot="${scio?.content()}" /></span> -
		 
		<img src="${resource(dir: 'images/icons', file: 'like.png')}" />
		<span class="font-little">${scio?.recommendations}</span>
	</p>
	</div>
</g:each>