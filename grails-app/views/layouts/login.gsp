<html>
	<head>
		<title><g:layoutTitle default="Grails"/></title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'census.css')}" type="text/css">
	</head>
	<body>
		<div class="wrapper">
				<div class="grid_header">
					<div id="logo">
	                	<a href="${request.contextPath}">
	                    	<img title="U.S. Census Bureau" alt="United States Census Bureau" alt="" src="${resource(dir:'images/census', file:'census-logo.png')}">
	                    </a>
	            	</div>
				</div>
		</div>
		
		<div id="loginPalette" class="shadow">
			<div id="content">
				<g:layoutBody/>
			</div>
		</div>
	</body>
</html>
