<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div class="container">
			<h2 class="center scio-color-green">Hi <sec:loggedInUserInfo field="username"/></h2>
			<div class="span3">
				<div class="nav-well">
					<form class="form-search">
						<input type="text" class="input-auto">
						<button type="submit" class="btn btn-scio">Search</button>
					</form>
	            </div>
	            <div class="nav-well">
					<div class="nav-well-title">Archives</div>
					<ul class="nav nav-list">
						<li><a href="#">December</a></li>
						<li><a href="#">November</a></li>
						<li><a href="#">October</a></li>
					</ul>
	            </div>
			</div>
			<div class="span6">
				<div class="mywell well-all">
					<g:link controller="scio" action="create">Create SCIO</g:link>
				</div>
			</div>
		</div>
	</body>
</html>