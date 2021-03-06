<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<r:require modules="bbcode,bootstrap-typeahead,editScio"/>
</head>
<body>
	<div class="container">
		<h2 class="scio-color-green">Edit &quot;${editCommand.title}&quot;</h2>
	  	<g:form controller="scio" method="POST" autocomplete="off">
	  		<g:textArea name="content" id="scio-edit">${editCommand.content}</g:textArea>
	  		
	  		<g:textField name="tagsSearch" id="tags" data-provide="typeahead" placeholder="Add new Scio tags"/>
	  		 
	  		<span id="tagsFeedback">${editCommand.tags}</span>
	  		
	  		<g:hiddenField name="id" value="${params.id}"/>
	  		<g:hiddenField name="tags" value="${editCommand.tags}" id="tagsField"/>
	  		<g:hiddenField name="findTagsUrl" id="findTagsUrl" value="${createLink(action: 'search', controller: 'tag')}"/>
	  		<g:hiddenField name="title" value="${editCommand.title}"/>
	  		<g:hiddenField name="bbcodepreview" id="bbcodepreview" value="${createLink(action: 'preview', controller: 'scio')}" />
	  		
	  		<br />
	  		<g:actionSubmit value="Edit Scio" class="btn btn-scio" action="doedit" />
	  		<g:actionSubmit value="Delete Scio (Cannot be undone)" class="btn btn-scio-light" action="dodelete" />
	  	</g:form>
	</div>
</body>
</html>
