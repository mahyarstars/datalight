<%@ page import="com.acn.mydata.DataSource" %>
<%@ page import="com.acn.mydata.Concept" %>

<html>
	<head>
		<meta name="layout" content="wizard"/>
		<title>Welcome to My Data</title>
	</head>
	<body>
		<div id="wizContainer">
			<div class="wizTitle">Step 5/7: Select Variable</div>
			<div class="wizBody">
				<g:form name="myForm" id="variableForm" url="[action:'saveVariable', controller:'wizard']">
					<g:hiddenField name="recid" value="${recordInstance?.id}" />
					<div id="variableContainer">
						<div id="variableList">
							<g:each in="${variableMap.keySet()}" status="i" var="conceptId">
								<h3 class="conceptHeader">${Concept.findByConceptId(conceptId)?.name?.toUpperCase() }</h3	>
								<div>
								<g:each in="${variableMap.get(conceptId)}" status="j" var="concept">
									<div class="variableItem">
										<g:checkBox name="variableids" value="${concept?.variableId}" checked="false" />
										<span>${concept?.variableId} - ${concept?.variable }</span>
									</div>
								</g:each>
								</div>
							</g:each>
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
