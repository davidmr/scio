<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<h2 class="scio-color-green">SCIOs tagged: &quot;${tag}&quot;</h2>
		<g:if test="${followingTag}">
			<g:form action="unfollow" controller="tag">
				<g:hiddenField name="tag" value="${tag}"/>
				<input type="submit" value="Stop following &quot;${tag}&quot;" class="btn btn-scio-light" />
			</g:form>
		</g:if>
		<g:else>
			<g:form action="follow" controller="tag">
				<g:hiddenField name="tag" value="${tag}"/>
				<input type="submit" value="Follow SCIOs about &quot;${tag}&quot;" class="btn btn-scio" />
			</g:form>
		</g:else>
		<g:render template="listScios" model="${[scioList: scioList]}" />
	</body>
</html>