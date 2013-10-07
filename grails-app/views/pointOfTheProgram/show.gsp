
<%@ page import="org.kozak127.phantom.PointOfTheProgram" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pointOfTheProgram.label', default: 'PointOfTheProgram')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-pointOfTheProgram" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-pointOfTheProgram" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list pointOfTheProgram">
			
				<g:if test="${pointOfTheProgramInstance?.creator}">
				<li class="fieldcontain">
					<span id="creator-label" class="property-label"><g:message code="pointOfTheProgram.creator.label" default="Creator" /></span>
					
						<span class="property-value" aria-labelledby="creator-label"><g:link controller="user" action="show" id="${pointOfTheProgramInstance?.creator?.id}">${pointOfTheProgramInstance?.creator?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pointOfTheProgramInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="pointOfTheProgram.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:link controller="event" action="show" id="${pointOfTheProgramInstance?.event?.id}">${pointOfTheProgramInstance?.event?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${pointOfTheProgramInstance?.accepted}">
				<li class="fieldcontain">
					<span id="accepted-label" class="property-label"><g:message code="pointOfTheProgram.accepted.label" default="Accepted" /></span>
					
						<span class="property-value" aria-labelledby="accepted-label"><g:formatBoolean boolean="${pointOfTheProgramInstance?.accepted}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${pointOfTheProgramInstance?.creationDate}">
				<li class="fieldcontain">
					<span id="creationDate-label" class="property-label"><g:message code="pointOfTheProgram.creationDate.label" default="Creation Date" /></span>
					
						<span class="property-value" aria-labelledby="creationDate-label"><g:formatDate date="${pointOfTheProgramInstance?.creationDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${pointOfTheProgramInstance?.timeInHours}">
				<li class="fieldcontain">
					<span id="timeInHours-label" class="property-label"><g:message code="pointOfTheProgram.timeInHours.label" default="Time In Hours" /></span>
					
						<span class="property-value" aria-labelledby="timeInHours-label"><g:fieldValue bean="${pointOfTheProgramInstance}" field="timeInHours"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${pointOfTheProgramInstance?.id}" />
					<g:link class="edit" action="edit" id="${pointOfTheProgramInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
