
<%@ page import="org.kozak127.phantom.InEventObject.InEventObjectCreator" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-inEventObjectCreator" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-inEventObjectCreator" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="inEventObjectCreator.reservation.label" default="Reservation" /></th>
					
						<th><g:message code="inEventObjectCreator.inEventObject.label" default="In Event Object" /></th>
					
						<g:sortableColumn property="creationDate" title="${message(code: 'inEventObjectCreator.creationDate.label', default: 'Creation Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${inEventObjectCreatorInstanceList}" status="i" var="inEventObjectCreatorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${inEventObjectCreatorInstance.id}">${fieldValue(bean: inEventObjectCreatorInstance, field: "reservation")}</g:link></td>
					
						<td>${fieldValue(bean: inEventObjectCreatorInstance, field: "inEventObject")}</td>
					
						<td><g:formatDate date="${inEventObjectCreatorInstance.creationDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${inEventObjectCreatorInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
