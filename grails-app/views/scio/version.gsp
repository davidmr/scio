<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
</head>
<body>
	<div class="container">
		<h2 class="scio-color-green">${scio.title}</h2>
		<h4 class="scio-color-brown">Version ${snapshot.id } of <g:snapshotDate snapshot="${snapshot}"/></h4>
		<h4 class="scio-color-brown">
			<img src="${resource(dir: 'images/icons', file: 'user.png')}" /> ${scio.owner.username} <g:if test="${scio.clone}">cloned from ${scio.cloneOf.owner.username}</g:if>
			<img src="${resource(dir: 'images/icons', file: 'calendar.png')}" /> <g:snapshotDate snapshot="${scio?.content()}" />
			<img src="${resource(dir: 'images/icons', file: 'like.png')}" /> ${scio.recommendations}
		</h4>
		<div>
			<g:each in="${scio.tags}">
				<span class="label label-success">
					<g:link controller="search" action="listByTag" params="${[tag: it.name]}" class="link-tag">${it.name}</g:link>
				</span>
			</g:each>
		</div>
		
		<div class="mywell">
			<g:renderSnapshot snapshot="${snapshot}" />
		</div>
	
		<div class="history">
			<h4 class="scio-color-green">Recent history</h4>
			<ul>
			<g:each in="${scio.recentHistory()}">
				<li><g:link controller="scio" action="version" params="${[id: scio.id, snapshot: it.id]}"><g:snapshotDate snapshot="${it}"/></g:link>
			</g:each>
			</ul>
		</div>
	</div>
</body>
</html>