<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
</head>
<body>
	<g:if test="${scio != null}">
		<div class="dialog">
			<div>To improve this SCIO you first must clone it and then edit it. <br />Are you sure you want to clone &quot;${scio.title}&quot; by ${scio.owner.username}?</div>
			<g:form controller="scio" action="doclone">
				<g:hiddenField name="id" value="${scio.id}" />
				<input type="submit" class="btn" value="Clone"/>
				<g:link controller="scio" action="show" params="${[id: scio.id]}">Cancel</g:link>
			</g:form>
		</div>
	</g:if>
	
</body>
</html>