
<%@ page import="org.kozak127.phantom.InEventObject.InEventObjectStaff" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-inEventObjectStaff" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-inEventObjectStaff" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="inEventObjectStaff.reservation.label" default="Reservation" /></th>
					
						<th><g:message code="inEventObjectStaff.inEventObject.label" default="In Event Object" /></th>
					
						<g:sortableColumn property="creationDate" title="${message(code: 'inEventObjectStaff.creationDate.label', default: 'Creation Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${inEventObjectStaffInstanceList}" status="i" var="inEventObjectStaffInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${inEventObjectStaffInstance.id}">${fieldValue(bean: inEventObjectStaffInstance, field: "reservation")}</g:link></td>
					
						<td>${fieldValue(bean: inEventObjectStaffInstance, field: "inEventObject")}</td>
					
						<td><g:formatDate date="${inEventObjectStaffInstance.creationDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${inEventObjectStaffInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
