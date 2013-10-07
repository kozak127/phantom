
<%@ page import="org.kozak127.phantom.ProgramItem" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'programItem.label', default: 'ProgramItem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-programItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-programItem" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="programItem.creator.label" default="Creator" /></th>
					
						<th><g:message code="programItem.event.label" default="Event" /></th>
					
						<g:sortableColumn property="accepted" title="${message(code: 'programItem.accepted.label', default: 'Accepted')}" />
					
						<g:sortableColumn property="creationDate" title="${message(code: 'programItem.creationDate.label', default: 'Creation Date')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'programItem.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="timeInHours" title="${message(code: 'programItem.timeInHours.label', default: 'Time In Hours')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${programItemInstanceList}" status="i" var="programItemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${programItemInstance.id}">${fieldValue(bean: programItemInstance, field: "creator")}</g:link></td>
					
						<td>${fieldValue(bean: programItemInstance, field: "event")}</td>
					
						<td><g:formatBoolean boolean="${programItemInstance.accepted}" /></td>
					
						<td><g:formatDate date="${programItemInstance.creationDate}" /></td>
					
						<td>${fieldValue(bean: programItemInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: programItemInstance, field: "timeInHours")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${programItemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
