<html>
	<head>
		<meta name='layout' content='main'/>
	</head>

	<body>
		<div class="span12">
			<h2 class="scio-color-green">Sign in</h2>
			
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
					<p>
					<label for='username'><g:message code="springSecurity.login.username.label"/>:</label>
					<input type='text' class='text_' name='j_username' id='username'/>
				</p>
	
				<p>
					<label for='password'><g:message code="springSecurity.login.password.label"/>:</label>
					<input type='password' class='text_' name='j_password' id='password'/>
				</p>
	
				<p id="remember_me_holder">
					<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
					<label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
				</p>
	
				<p>
					<input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}'/>
				</p>
				</fieldset>
			</form>
		</div>
	</body>
</html>
