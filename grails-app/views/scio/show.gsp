<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main"/>
</head>
<body>
	<div class="container">
		<h2 class="scio-color-green">${scio.title}</h2>

		<h4 class="scio-color-brown">
			<img src="${resource(dir: 'images/icons', file: 'user.png')}" /> ${scio.owner.username}
			<img src="${resource(dir: 'images/icons', file: 'like.png')}" /> ${scio.recommendations}
			<img src="${resource(dir: 'images/icons', file: 'calendar.png')}" /> <g:snapshotDate snapshot="${scio?.content()}" />
		</h4>

		<div>
			<g:each in="${scio.tags}">
				<span class="label label-success">
					<g:link controller="search" action="listByTag" params="${[tag: it.name]}" class="link-tag">${it.name}</g:link>
				</span>
			</g:each>
		</div>
	</div>
	
	<div class="mywell">
		<g:renderSnapshot snapshot="${scio.content()}" />
	</div>
	
	<div class="pull-right">
		<g:link controller="scio" action="clone" params="${[id: scio.id]}" class="btn btn-scio">Improve this SCIO!</g:link>
		<g:if test="${!flash.recommend}">
			<g:link controller="scio" action="recommend" params="${[id: scio.id]}" elementId="linkRecommendScio" class="btn btn-scio" >Recommend</g:link>
		</g:if>
		<g:if test="${canEdit}">
			<g:link controller="scio" action="edit" params="${[id: scio.id]}" class="btn btn-scio" >Edit</g:link>
		</g:if>
	</div>
	
	<h3>Recent history</h3>
	<ul>
	<g:each in="${scio.recentHistory()}">
		<li><g:link controller="scio" action="version" params="${[id: scio.id, snapshot: it.id]}"><g:snapshotDate snapshot="${it}"/></g:link>
	</g:each>
	</ul>
	
</body>
</html>
