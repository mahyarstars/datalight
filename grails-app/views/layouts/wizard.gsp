<html>
	<head>
		<title><g:layoutTitle default="Grails"/></title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'census.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui-1.10.3.custom.min.css')}" type="text/css">
		
		<script language="javascript" src="${resource(dir: 'js', file: 'jquery-1.9.1.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js', file: 'jquery-ui-1.10.3.custom.min.js')}"></script>
		<script language="javascript" src="${resource(dir: 'js', file: 'wizard.js')}"></script>
		<g:layoutHead/>
	</head>
	<body>
		<div class="wrapper">
				<div class="grid_header">
					<div id="logo">
	                	<a href="${request.contextPath}">
	                    	<img title="U.S. Census Bureau" alt="United States Census Bureau" alt="" src="${resource(dir:'images/census', file:'census-logo.png')}">
	                    </a>
	            	</div>
	            	<div id="logout">
		           		<span>Hi, <b><sec:username /></b>:</span> 
		           		(<g:link controller="logout">Log Out</g:link>)
		           	</div>
				</div>
		</div>
		
		<div id="wizardPalette" class="shadow">
			<g:layoutBody/>
		</div>
	</body>
</html>
