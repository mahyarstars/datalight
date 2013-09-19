<%@ page import="com.acn.mydata.DataSource" %>
<%@ page import="com.acn.mydata.Concept" %>
<%@ page import="com.acn.mydata.Country" %>


<html>
	<head>
		<meta name="layout" content="wizard"/>
		<title>Welcome to My Data</title>
	</head>
	<body>
		<div id="wizContainer">
			<div class="wizTitle">Step 6/7: Add Geography</div>
			<div class="wizBody">
				<g:form name="myForm" id="variableForm" url="[action:'saveGeography', controller:'wizard']">
					<g:hiddenField name="recid" value="${recordInstance?.id}" />
					<div id="geoContainer">
						<div id="geoList">
							<div class="country">
								<div class="countryItem">
									<g:checkBox name="countryfipscode" value="${country?.fipsCode}" checked="false" />
									<span class="countrySpan">${country.name} (${country.abbreviation})</span>
									<div class="states">
										<g:each in="${states}" status="i" var="state">
										<div class="stateItem" fipscode="${state?.fipsCode}">
											<g:checkBox name="statefipscode" value="${state?.fipsCode}" checked="false" />
											<span title="Click to show list of Counties" class="stateSpan">${state.name} (${state.abbreviation?.toUpperCase()})</span>
										</div>
										</g:each>
									</div>
								</div> 
							</div>
						</div>
					</div>
					<div class="wizSubmitDiv">
						<g:submitButton name="next" class="wizNext" value="Next" />
					</div>
				</g:form>
			
			</div>
		</div>
	</body>
</html>
