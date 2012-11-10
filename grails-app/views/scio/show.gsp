<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
</head>
<body>
	<h1>${scio.title}</h1><g:if test="${canEdit}"><g:link controller="scio" action="edit" params="${[id: scio.id]}">Edit</g:link></g:if>
	<h2>By: ${scio.owner.username}</h2>
	<div class="tags">
		<g:each in="${scio.tags}">
			<span class="tag"><g:link controller="search" action="listByTag" params="${[tag: it.name]}">${it.name}</g:link></span>
		</g:each>
	</div>
	<div class="scio-content">
		<g:renderSnapshot snapshot="${scio.content()}" />
	</div>
	<g:link controller="scio" action="clone" params="${[id: scio.id]}">Improve this SCIO!</g:link>
	<h3>Recent history</h3>
	<ul>
	<g:each in="${scio.recentHistory()}">
		<li><g:link controller="scio" action="version" params="${[id: scio.id, snapshot: it.id]}"><g:snapshotDate snapshot="${it}"/></g:link>
	</g:each>
	</ul>
</body>
</html>