
<%@ page import="com.acn.mydata.Record" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'record.label', default: 'Record')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-record" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-record" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list record">
			
				<g:if test="${recordInstance?.country}">
				<li class="fieldcontain">
					<span id="country-label" class="property-label"><g:message code="record.country.label" default="Country" /></span>
					
						<span class="property-value" aria-labelledby="country-label"><g:link controller="country" action="show" id="${recordInstance?.country?.id}">${recordInstance?.country?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${recordInstance?.county}">
				<li class="fieldcontain">
					<span id="county-label" class="property-label"><g:message code="record.county.label" default="County" /></span>
					
						<span class="property-value" aria-labelledby="county-label"><g:link controller="county" action="show" id="${recordInstance?.county?.id}">${recordInstance?.county?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${recordInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="record.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${recordInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recordInstance?.isShared}">
				<li class="fieldcontain">
					<span id="isShared-label" class="property-label"><g:message code="record.isShared.label" default="Is Shared" /></span>
					
						<span class="property-value" aria-labelledby="isShared-label"><g:formatBoolean boolean="${recordInstance?.isShared}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${recordInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="record.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${recordInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recordInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="record.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:link controller="state" action="show" id="${recordInstance?.state?.id}">${recordInstance?.state?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${recordInstance?.id}" />
					<g:link class="edit" action="edit" id="${recordInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
