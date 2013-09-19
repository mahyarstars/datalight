<%@ page import="com.acn.mydata.Record" %>
<html>
	<head>
		<meta name="layout" content="wizard"/>
		<title>Welcome to My Data</title>
	</head>
	<body>
		<div id="wizContainer">
			<div class="wizTitle">Step 1/7: Create New Record</div>
			<div class="wizBody">
				<g:form name="myForm" url="[action:'saveRecord', controller:'wizard']">
					<div class="wizRecName">
						<span>Record Name</span>
						<g:textField name="recname" value="" />
					</div>
					<div class="wizRecDesc">
						<span>Record Description</span>
						<g:textArea name="recdesc" value="" rows="5" cols="40"/>
					</div>
					<div class="wizRecShare">
						<span>Share with Community</span>
						<g:checkBox name="isshareable" value="" checked="true" />
					</div>
					<div class="wizSubmitDiv">
						<g:submitButton name="next" class="wizNext" value="Next" />
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
