
<%@ page import="org.kozak127.phantom.Stall" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'stall.label', default: 'Stall')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-stall" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-stall" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="accepted" title="${message(code: 'stall.accepted.label', default: 'Accepted')}" />
					
						<g:sortableColumn property="creationDate" title="${message(code: 'stall.creationDate.label', default: 'Creation Date')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'stall.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="paid" title="${message(code: 'stall.paid.label', default: 'Paid')}" />
					
						<g:sortableColumn property="size" title="${message(code: 'stall.size.label', default: 'Size')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${stallInstanceList}" status="i" var="stallInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${stallInstance.id}">${fieldValue(bean: stallInstance, field: "accepted")}</g:link></td>
					
						<td><g:formatDate date="${stallInstance.creationDate}" /></td>
					
						<td>${fieldValue(bean: stallInstance, field: "name")}</td>
					
						<td><g:formatBoolean boolean="${stallInstance.paid}" /></td>
					
						<td>${fieldValue(bean: stallInstance, field: "size")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${stallInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
