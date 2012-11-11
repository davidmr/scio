<%@ page import="org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils" %>

<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Scio - Share knowledge</title>
	
		<r:require module="core"/>
		<link rel="shortcut icon" href="${resource(dir: 'images/icons', file: 'owl.png')}" type="image/x-icon">

		<g:layoutHead />
		<r:layoutResources />
	</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active">
							<a class="brand" href="${createLink(uri: '/')}">
								<img src="${resource(dir: 'images/icons', file: 'owl.png')}" /> SCIO
							</a>
						</li>
						<li class="active"><g:link controller="home" action="about">About</g:link></li>
					</ul>
					<sec:ifNotLoggedIn>
						<form action='${request.contextPath}${SpringSecurityUtils.securityConfig.apf.filterProcessesUrl}' method='POST' id='loginForm' class='navbar-form little pull-right' autocomplete='off'>
							<input name='j_username' id='username' class="span2" type="text" placeholder="Username"> 
							<input name='j_password' id='password' class="span2" type="password" placeholder="Password">
							
							<button type="submit" class="btn btn-scio-light">Sign in</button>
							<g:link controller="user" action="create" class="btn">Sign up</g:link>
						</form>
					</sec:ifNotLoggedIn>
					<sec:ifLoggedIn>
						<div class="pull-right">
							<span class="scio-username">
								<sec:loggedInUserInfo field="username"/>
							</span>
							<g:link controller="home" action="user" class="btn">Control Panel</g:link>
							<g:link controller="logout" class="btn">Log out</g:link>
						</div>
					</sec:ifLoggedIn>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container scio-container-plus main">
	
		<g:if test="${flash.message}">
			<div class="alert alert-info">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<b>Hey: </b>${flash.message}
			</div>
		</g:if>
		
		<g:if test="${flash.error}">
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<b>Oops: </b>${flash.error}
			</div>
		</g:if>
		
		<g:hasErrors>
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<g:renderErrors/></div>
		</g:hasErrors>
			
		<g:layoutBody />
	
		<r:layoutResources/>
	</div>
	
	<div class="footer">
		<p>© SCIO 2012</p>
	</div>
</body>
</html>
