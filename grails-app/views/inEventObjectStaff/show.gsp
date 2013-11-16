
<%@ page import="org.kozak127.phantom.InEventObject.InEventObjectStaff" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-inEventObjectStaff" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-inEventObjectStaff" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list inEventObjectStaff">
			
				<g:if test="${inEventObjectStaffInstance?.reservation}">
				<li class="fieldcontain">
					<span id="reservation-label" class="property-label"><g:message code="inEventObjectStaff.reservation.label" default="Reservation" /></span>
					
						<span class="property-value" aria-labelledby="reservation-label"><g:link controller="reservation" action="show" id="${inEventObjectStaffInstance?.reservation?.id}">${inEventObjectStaffInstance?.reservation?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${inEventObjectStaffInstance?.inEventObject}">
				<li class="fieldcontain">
					<span id="inEventObject-label" class="property-label"><g:message code="inEventObjectStaff.inEventObject.label" default="In Event Object" /></span>
					
						<span class="property-value" aria-labelledby="inEventObject-label"><g:link controller="inEventObject" action="show" id="${inEventObjectStaffInstance?.inEventObject?.id}">${inEventObjectStaffInstance?.inEventObject?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${inEventObjectStaffInstance?.creationDate}">
				<li class="fieldcontain">
					<span id="creationDate-label" class="property-label"><g:message code="inEventObjectStaff.creationDate.label" default="Creation Date" /></span>
					
						<span class="property-value" aria-labelledby="creationDate-label"><g:formatDate date="${inEventObjectStaffInstance?.creationDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${inEventObjectStaffInstance?.id}" />
					<g:link class="edit" action="edit" id="${inEventObjectStaffInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
