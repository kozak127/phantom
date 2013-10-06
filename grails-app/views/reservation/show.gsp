
<%@ page import="org.kozak127.phantom.Reservation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-reservation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-reservation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list reservation">
			
				<g:if test="${reservationInstance?.paid}">
				<li class="fieldcontain">
					<span id="paid-label" class="property-label"><g:message code="reservation.paid.label" default="Paid" /></span>
					
						<span class="property-value" aria-labelledby="paid-label"><g:formatBoolean boolean="${reservationInstance?.paid}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="reservation.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${reservationInstance?.user?.id}">${reservationInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="reservation.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:link controller="event" action="show" id="${reservationInstance?.event?.id}">${reservationInstance?.event?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${reservationInstance?.dateOfCreation}">
				<li class="fieldcontain">
					<span id="dateOfCreation-label" class="property-label"><g:message code="reservation.dateOfCreation.label" default="Date Of Creation" /></span>
					
						<span class="property-value" aria-labelledby="dateOfCreation-label"><g:formatDate date="${reservationInstance?.dateOfCreation}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${reservationInstance?.id}" />
					<g:link class="edit" action="edit" id="${reservationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
