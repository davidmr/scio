<%@ page import="scio.Scio" %>

<!doctype html>
<html>
	<head>
		<meta name='layout' content='main'/>
	</head>
	<body>
		<div class="row-fluid scio-container-minus">
			<div class="span12">
				<div class="hero-unit">
					<div class="span4">
						<img src="${resource(dir: 'images', file: 'owl.png')}" width="150px" />
					</div>
					<div>
						<h1>
							Scio
						</h1>
						<p>Scio is a place to share knowledge.</p>
						
						<form class="form-search">
							<input type="text" placeholder="What do you want to know?" size="500">
							<button type="submit" class="btn btn-scio">Search</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span12">
          		<h2 class="scio-color-brown">
          			<img src="${resource(dir: 'images/icons', file: 'star.png')}" /> Featured Scios
          		</h2>
          		<g:each in="${Scio.list()}" var="scio">
          			<g:link controller="scio" action="show" id="${scio.id}" class="decorated">
          				<h4 class="scio-color-green">${scio.title}</h4>
          			</g:link>
					<p>
	          			
	          			<br />
	          			<span class="font-little">${scio.owner}</span>
	          		</p>          			
          		</g:each>
       		</div>
		</div>
	</body>
</html>