<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
</head>
<body>
	<g:if test="${scio != null}">
		<div class="dialog">
		<p>This SCIO is a clone of another one. You can ask the original author to merge the changes
		done to this SCIO or you can continue without asking to merge. What do you want to do?</p>
		<g:form controller="scio">
			<g:hiddenField name="id" value="${scio.id}" />
			<g:actionSubmit value="Ask ${scio.cloneOf.owner.username} to merge this SCIO" action="requestmerge"/>
			<g:actionSubmit value="Continue without merging" action="toshow" />
		</g:form>
		</div>
	</g:if>
</body>
</html>