
<%@ page import="com.acn.mydata.Record" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'record.label', default: 'Record')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-record" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-record" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="record.country.label" default="Country" /></th>
					
						<th><g:message code="record.county.label" default="County" /></th>
					
						<g:sortableColumn property="description" title="${message(code: 'record.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="isShared" title="${message(code: 'record.isShared.label', default: 'Is Shared')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'record.name.label', default: 'Name')}" />
					
						<th><g:message code="record.state.label" default="State" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${recordInstanceList}" status="i" var="recordInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${recordInstance.id}">${fieldValue(bean: recordInstance, field: "country")}</g:link></td>
					
						<td>${fieldValue(bean: recordInstance, field: "county")}</td>
					
						<td>${fieldValue(bean: recordInstance, field: "description")}</td>
					
						<td><g:formatBoolean boolean="${recordInstance.isShared}" /></td>
					
						<td>${fieldValue(bean: recordInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: recordInstance, field: "state")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${recordInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
