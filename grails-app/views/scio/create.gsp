<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<r:require modules="bbcode,bootstrap-typeahead"/>
<r:script>
	(function($){
		$(document).ready(function(){
			$("#scio-edit").markItUp(mySettings);
			$("#tags").typeahead({
				source: findTags,
				updater : function(item){
					var tagsField = $("#tagsField");
					var tagsFeedback = $("#tagsFeedback");
					var old = tagsField.val();
					var tags =  old + ' ' + item;
					tagsField.val(tags);
					tagsFeedback.text(tags);
					return null;
				}
			});
		});
		
		function findTags(query, process){
			$.get("${createLink(controller: 'tag', action: 'search')}", {q: query}, function(data){
				process(data.options);
			});
		}
	})(jQuery);
</r:script>
</head>
<body>
	<h1>create scio</h1>
	<g:renderErrors />
  	<g:form controller="scio" action="docreate" method="POST" autocomplete="off">
  		<label for="title" class="${hasErrors(bean: scioCommand, field: 'title', 'error')}">Title</label>
  		<g:textField name="title" value="${scioCommand?.title}"/>
  		<g:textArea name="content" id="scio-edit">${scioCommand?.content}</g:textArea>
  		<g:textField name="tagsSearch" id="tags" data-provide="typeahead" placeholder="Tags"/> 
  		<span id="tagsFeedback">${scioCommand?.tags}</span>
  		<g:hiddenField name="tags" value="${scioCommand?.tags}" id="tagsField"/>
  		<br />
  		<input type="submit" value="Create SCIO"/>
  	</g:form>
</body>
</html>