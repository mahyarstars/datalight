<html>
	<head>
		<meta name="layout" content="dashboard"/>
		<title>Welcome to My Data</title>
	</head>
	<body>
		<div id="navContainer">
			<a href="${createLink(controller:"wizard", action: 'createRecord')}">
			<div class="navButton">
				<div class="navImage">
					<g:img dir="images" file="magic_wand.png" width="48" height="48"/>
				</div>
				<div class="navWords">
					<div class="navTitle">Build</div>
					<div class="navDesc">Build a custom data table from the Census API</div>
				</div>
				<div style="clear: both;"></div>
			</div>
			</a>
			<a href="${createLink(controller:"record", action: 'myRecords')}">
			<div class="navButton">
				<div class="navImage">
					<g:img dir="images" file="cloud_upload.png" width="48" height="48"/>
				</div>
				<div class="navWords">
					<div class="navTitle">My Records</div>
					<div class="navDesc">View and Share the data tables you've built</div>
				</div>
				<div style="clear: both;"></div>
			</div>
			</a>
			<a href="${createLink(controller:"community")}">
			<div class="navButton">
				<div class="navImage">
					<g:img dir="images" file="cloud_download.png" width="48" height="48"/>
				</div>
				<div class="navWords">
					<div class="navTitle">Community</div>
					<div class="navDesc">Download and edit tables built by the community</div>
				</div>
				<div style="clear: both;"></div>
			</div>
			</a>
		</div>
	</body>
</html>
