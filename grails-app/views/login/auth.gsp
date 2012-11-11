<html>
	<head>
		<meta name='layout' content='main'/>
	</head>

	<body>
		<div class="span12">
			<h2 class="center scio-color-green">Sign in</h2>
			
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
			
			<form action='${postUrl}' method='POST' id='loginForm' class='well form-horizontal' autocomplete='off'>
				<fieldset class="form">
					<div class="control-group">
						<label class="control-label" for="username">
							<g:message code="springSecurity.login.username.label"/>
						</label>
						<div class="controls">
							<input type='text' class='text_' name='j_username' id='username'/>
						</div>
					</div>
					
					<div class="control-group fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
						<label class="control-label" for="password">
							<g:message code="springSecurity.login.password.label"/>
						</label>
						<div class="controls">
							<input type='password' class='text_' name='j_password' id='password'/>
						</div>
					</div>
					
					 <div class="control-group">
						<div class="controls">
						  <label class="checkbox">
						    <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
						    <g:message code="springSecurity.login.remember.me.label"/>
						  </label>
						  <button type="submit" class="btn pull-right">${message(code: "springSecurity.login.button")}</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</body>
</html>
