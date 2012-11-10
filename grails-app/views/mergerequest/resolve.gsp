<%@page import="difflib.DiffRow"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<r:require module="diff" />
</head>
<body>
<h1>${merge.owner.username} is asking to merge.</h1>
<h2>Here is the difference with your SCIO</h2>
<table class="diff">
	<tr><th></th><th>Original</th><th>Revised</th></tr>
	<g:each in="${diffRows}" var="diff">
		<g:set var="classname" value="" />
		<g:set var="simbol" value="" />
		<g:if test="${diff.tag == DiffRow.Tag.INSERT}">
			<g:set var="classname" value="insert" />
			<g:set var="simbol" value="+" />
		</g:if>
		<g:if test="${diff.tag == DiffRow.Tag.DELETE}">
			<g:set var="classname" value="delete" />
			<g:set var="simbol" value="-" />
		</g:if>
		<g:if test="${diff.tag == DiffRow.Tag.CHANGE}">
			<g:set var="classname" value="change" />
			<g:set var="simbol" value="*" />
		</g:if>
		<tr class='${classname}'><td>${simbol}</td><td>${diff.oldLine}</td><td>${diff.newLine}</td></tr>
	</g:each>
</table>

<g:form controller="mergerequest">
	<g:hiddenField name="id" value="${merge.id}"/>
	<g:actionSubmit value="Accept" action="accept" />
	<g:actionSubmit value="Reject" action="reject" />
</g:form>
</body>
</html>