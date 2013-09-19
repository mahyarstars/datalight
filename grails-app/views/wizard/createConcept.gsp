<%@ page import="com.acn.mydata.DataSource" %>
<%@ page import="com.acn.mydata.Concept" %>

<html>
	<head>
		<meta name="layout" content="wizard"/>
		<title>Welcome to My Data</title>
		<script>
			$(document).ready(function(){
				$("#conceptIds").val("");
			});
		</script>
	</head>
	<body>
		<div id="wizContainer">
			<div class="wizTitle">Step 4/7: Select Concept</div>
			<div class="wizBody">
				<g:form name="myForm" id="conceptForm" url="[action:'saveConcept', controller:'wizard']">
					<g:hiddenField name="recid" value="${recordInstance?.id}" />
					<g:hiddenField id="conceptIds" name="conceptids" value="" />
					<div class="filterDiv"><input title="Type to filter Concept list" id="conceptFilter" type="text"></div>
					<div class="conceptContainer">
						<div class="conceptList" id="availConcepts">
							<g:each in="${conceptList}" status="i" var="concept">
								<div title="Double click to select Concept" class="availConcept" conceptId="${concept.conceptId}">${concept.name.toUpperCase()}</div>
							</g:each>
						</div>
						<div class="conceptContainerTitle">Available Concepts</div>
					</div>
					<div id="conceptButtons">&nbsp</div>
					<div class="conceptContainer">
						<div class="conceptList" id="selectedConcepts">
						</div>
						<div class="conceptContainerTitle">Selected Concepts</div>
					</div>
					<div class="wizSubmitDiv">
						<g:submitButton name="next" class="wizNext" value="Next" />
					</div>
				</g:form>
			
			</div>
		</div>
	</body>
</html>
