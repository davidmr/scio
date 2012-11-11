<html>
	<head>
		<meta name="layout" content="main"/>
		<r:require modules="searchScio"/>
	</head>
	<body>
		<div class="container">
			<h2 class="center scio-color-green">Hi <sec:loggedInUserInfo field="username"/></h2>
			<div class="span3">
				<div class="nav-well">
					<form class="form-search">
						<input type="text" class="input-auto" id="textMyScioByTag" placeholder="Tag to search" />
						<button type="submit" class="btn btn-scio" id="buttonMySciosByTag">Search</button>
					</form>
	            </div>
	            <div class="nav-well">
	            	<div class="nav-well-title">Followed Tags</div>
	            	<g:include controller="tag" action="followed" />
	            </div>
	            <div class="nav-well">
	            	<div class="nav-well-title">Merge Requests</div>
	            	<g:include controller="mergerequest" action="list" />
	            </div>
	            <div class="nav-well">
					<div class="nav-well-title">Archives</div>
					<g:include controller="search" action="searchMonthsMine" />
	            </div>
			</div>
			<div class="span6">
				<div class="mywell well-all">
					<div id="panelSciosByTag" style="display: none;">			
						<h3 id="titleSciosByTag" class="scio-color-green">
							<img src="${resource(dir: 'images/icons', file: 'star.png')}" /> Scios
							<g:link controller="scio" action="create" class="btn btn-scio pull-right">Create SCIO</g:link>
						</h3>
						<div id="contentSciosByTag">
						</div>
					</div>
					
					<div id="panelNoScios" style="display: none;">
						<h2 id="titleNoScios" class="scio-color-green">
							<img src="${resource(dir: 'images/icons', file: 'star.png')}" /> Scios
						</h2>
						<div id="contentNoScios">
							Sorry. We don't find a Scio match the search.
						</div>
					</div>
					
					<div id="panelSciosMine">
						<h3 id="titleSciosMine" class="scio-color-green">
							<img src="${resource(dir: 'images/icons', file: 'star.png')}" /> My Last Scios
							<g:link controller="scio" action="create" class="btn btn-scio pull-right">Create SCIO</g:link>
						</h3>
						<div id="contentSciosMine">
							<g:include controller="search" action="searchMine" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<g:hiddenField name="urlSearchByTag" id="urlSearchByTag" value="${createLink(controller: 'search', action: 'searchMineByTag')}"/>
	</body>
</html>