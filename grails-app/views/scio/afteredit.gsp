<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
</head>
<body>
	<g:renderErrors/>
	<g:if test="${scio != null}">
		<g:form controller="scio">
			<g:hiddenField name="id" value="${scio.id}" />
			<g:actionSubmit value="Ask ${scio.cloneOf.owner.username} to merge this SCIO" action="requestmerge"/>
			<g:actionSubmit value="Continue without merging" action="toshow" />
		</g:form>
	</g:if>
</body>
</html>