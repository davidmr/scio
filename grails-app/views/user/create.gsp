<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>
		<div class="span12">
			<h2 class="scio-color-green">New to Scio? Sign up</h2>
			
			<div class="alert-error">
				<g:hasErrors bean="${userInstance}">
					<ul role="alert">
						<g:eachError bean="${userInstance}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
								<g:message error="${error}"/>
							</li>
						</g:eachError>
					</ul>
				</g:hasErrors>
			</div>
			
			<g:form action="save" class="well form-horizontal">
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<button type="submit" class="btn btn-scio">Sign in</button>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
