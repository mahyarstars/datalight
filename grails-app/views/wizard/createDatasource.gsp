<%@ page import="com.acn.mydata.Record" %>
<%@ page import="com.acn.mydata.DataSource" %>
<html>
	<head>
		<meta name="layout" content="wizard"/>
		<title>Welcome to My Data</title>
	</head>
	<body>
		<div id="wizContainer">
			<div class="wizTitle">Step 2/7: Select DataSource</div>
			<div class="wizBody">
				<g:form name="myForm" url="[action:'saveDatasource',controller:'wizard']">
					<g:hiddenField name="recid" value="${recordInstance?.id}" />
					<g:each in="${DataSource.list().sort({it.name})}" status="i" var="dataSource">
						<div class="dsRadio">
							<g:radio name="datasourceid" value="${dataSource.id}"/>
							<span>${dataSource.name}</span>
						</div>
					</g:each>
					<div class="wizSubmitDiv">
						<g:submitButton name="next" class="wizNext" value="Next" />
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
