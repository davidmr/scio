<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<r:require modules="bbcode,bootstrap-typeahead,editScio"/>
</head>
<body>
	<h2 class="scio-color-green">Write a new SCIO</h2>
  	<g:form controller="scio" action="docreate" method="POST" autocomplete="off">
  		<label for="title" class="${hasErrors(bean: scioCommand, field: 'title', 'error')}">Title</label>
  		<g:textField name="title" value="${scioCommand?.title}" placeholder="SCIO title" required="true" />
  		<g:textArea name="content" id="scio-edit" required="true">${scioCommand?.content}</g:textArea>
  		<g:textField name="tagsSearch" id="tags" data-provide="typeahead" placeholder="Your SCIO tags"/> 
  		<span id="tagsFeedback">${scioCommand?.tags}</span>
  		<g:hiddenField name="tags" value="${scioCommand?.tags}" id="tagsField"/>
  		<br />
  		<input type="submit" value="Create SCIO"/>
  	</g:form>
</body>
</html>