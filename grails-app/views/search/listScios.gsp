<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<h1 class="scio-color-green">Search by tag: ${tag}</h1>
		<g:render template="listScios" model="${[scioList: scioList]}" />
		<g:form action="follow">
			<g:hiddenField name="tag" value="${tag}"/>
			<input type="submit" value="Follow SCIOs about &quot;${tag}&quot;" class="btn" />
		</g:form>
	</body>
</html>