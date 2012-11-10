<!doctype html>
<html lang="en" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Scio - What do you know?</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${resource(dir: 'images', file: 'owl.png')}" type="image/x-icon">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" media="screen">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'mybootstrap.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}" type="text/css">
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
						<li class="active"><a href="#">About</a></li>
					</ul>
					<sec:ifNotLoggedIn>
						<form action='${postUrl}' method='POST' id='loginForm' class='navbar-form little pull-right' autocomplete='off'>
							<input name='j_username' id='username' class="span2" type="text" placeholder="Username"> 
							<input name='j_password' id='password' class="span2" type="password" placeholder="Password">
							
							<button type="submit" class="btn btn-scio-light">Sign in</button>
							<g:link controller="user" action="create" class="btn">Sign up</g:link>
						</form>
					</sec:ifNotLoggedIn>
					<sec:ifLoggedIn>
						<g:link controller="logout" class="btn pull-right">Log out</g:link>
					</sec:ifLoggedIn>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container scio-container-plus">
		<g:if test="${flash.message}">
			<div class="alert-info">${flash.message}</div>
		</g:if>
		
		<div class="row">
			<g:layoutBody />
		</div>

		<div class="row footer">
			<div class="span12">
				<p>© SCIO 2012</p>
			</div>
		</div>
	</div>
	
	<r:layoutResources/>
	<!-- /container -->
</body>
</html>
