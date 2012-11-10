<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
</head>
<body>
	<g:renderErrors />
	<h1>${scio.title}</h1>
	<h2>By: ${scio.owner.username}</h2>
	<h3>Version ${snapshot.id } of <g:snapshotDate snapshot="${snapshot}"/></h3>
	<div class="tags">
		<g:each in="${scio.tags}">
			<span class="tag"><g:link controller="search" params="${[id: it.name]}">${it.name}</g:link></span>
		</g:each>
	</div>
	<div class="scio-content">
		<g:renderSnapshot snapshot="${snapshot}" />
	</div>
	<h3>Recent history</h3>
	<ul>
	<g:each in="${scio.recentHistory()}">
		<li><g:link controller="scio" action="version" params="${[id: scio.id, snapshot: it.id]}"><g:snapshotDate snapshot="${it}"/></g:link>
	</g:each>
	</ul>
</body>
</html>