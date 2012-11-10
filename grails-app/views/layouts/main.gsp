<!doctype html>
<html lang="en" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Welcome to Scio</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${resource(dir: 'images', file: 'owl.png')}" type="image/x-icon">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" media="screen">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'mybootstrap.css')}" type="text/css">
	<g:layoutHead />
	<r:layoutResources />
</head>
<body>
	<g:render template="topbar" />
	
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
	<!-- /container -->
</body>
</html>