<!doctype html>
<html>
	<head>
		<meta name='layout' content='main'/>
		<r:require modules="searchScio"/>
	</head>
	<body>
		<div class="row-fluid scio-container-minus">
			<div class="span12">
				<div class="hero-unit">
					<h1>
						Scio
					</h1>
					<p>Scio is a place to share knowledge.</p>
					
					<form class="form-search">
						<input type="text" placeholder="What do you want to know?" id="textScioByTag" size="500">
						<a href="#" class="btn btn-scio" id="buttonSciosByTag">Search</a>
					</form>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="mywell well-center">
				<div id="panelSciosByTag" style="display: none;">			
					<h2 id="titleSciosByTag" class="scio-color-green">
						<img src="${resource(dir: 'images/icons', file: 'star.png')}" /> Scios
					</h2>
					<div id="contentSciosByTag">
					</div>
				</div>
				<div id="panelSciosFeatured">
					<h2 id="titleSciosFeatured" class="scio-color-green">
						<img src="${resource(dir: 'images/icons', file: 'star.png')}" /> Featured Scios
					</h2>
					<div id="contentSciosFeatured">
						<g:include controller="search" action="searchFeatured" />
					</div>
				</div>
			</div>
		</div>
		<g:hiddenField name="urlSearchByTag" id="urlSearchByTag" value="${createLink(controller: 'search', action: 'searchByTag')}"/>
	</body>
</html>