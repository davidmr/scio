<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<r:require module="bbcode"/>
<r:script>
	(function($){
		$(document).ready(function(){
			$("#scio-edit").markItUp(mySettings);
		});
	})(jQuery);
</r:script>
</head>
<body>
	<h1>create scio</h1>
	<g:renderErrors />
  	<g:form controller="scio" action="docreate" method="POST" >
  		<label for="title" class="${hasErrors(bean: scioCommand, field: 'title', 'error')}">Title</label>
  		<g:textField name="title" value="${scioCommand?.title}"/>
  		<g:textArea name="content" id="scio-edit">${scioCommand?.content}</g:textArea>
  		<label for="tags">Tags (separated by space)</label>
  		<g:textField name="tags" value="${scioCommand?.tags}" />
  		<input type="submit" value="Create SCIO"/>
  	</g:form>
</body>
</html>