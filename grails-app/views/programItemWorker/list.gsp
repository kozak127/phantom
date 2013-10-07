
<%@ page import="org.kozak127.phantom.Staff.ProgramItemWorker" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'programItemWorker.label', default: 'ProgramItemWorker')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-programItemWorker" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-programItemWorker" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="programItemWorker.reservation.label" default="Reservation" /></th>
					
						<th><g:message code="programItemWorker.programItem.label" default="Program Item" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${programItemWorkerInstanceList}" status="i" var="programItemWorkerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${programItemWorkerInstance.id}">${fieldValue(bean: programItemWorkerInstance, field: "reservation")}</g:link></td>
					
						<td>${fieldValue(bean: programItemWorkerInstance, field: "programItem")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${programItemWorkerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
