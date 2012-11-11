<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<r:require modules="bbcode,bootstrap-typeahead,editScio"/>
</head>
<body>
	<h1>Edit &quot;${editCommand.title}&quot;</h1>
	<g:renderErrors />
  	<g:form controller="scio" action="doedit" method="POST" autocomplete="off">
  		<g:textArea name="content" id="scio-edit">${editCommand.content}</g:textArea>
  		<g:textField name="tagsSearch" id="tags" data-provide="typeahead" placeholder="Tags"/> 
  		<span id="tagsFeedback">${editCommand.tags}</span>
  		<g:hiddenField name="id" value="${params.id}"/>
  		<g:hiddenField name="tags" value="${editCommand.tags}" id="tagsField"/>
  		<g:hiddenField name="findTagsUrl" value="${createLink(controller: 'tag', action: 'search')}"/>
  		<g:hiddenField name="title" value="${editCommand.title}"/>
  		<br />
  		<input type="submit" value="Edit SCIO"/>
  	</g:form>
</body>
</html>