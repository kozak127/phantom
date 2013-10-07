
<%@ page import="org.kozak127.phantom.ProgramItem" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'programItem.label', default: 'ProgramItem')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-programItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-programItem" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list programItem">
			
				<g:if test="${programItemInstance?.creator}">
				<li class="fieldcontain">
					<span id="creator-label" class="property-label"><g:message code="programItem.creator.label" default="Creator" /></span>
					
						<span class="property-value" aria-labelledby="creator-label"><g:link controller="user" action="show" id="${programItemInstance?.creator?.id}">${programItemInstance?.creator?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${programItemInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="programItem.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:link controller="event" action="show" id="${programItemInstance?.event?.id}">${programItemInstance?.event?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${programItemInstance?.accepted}">
				<li class="fieldcontain">
					<span id="accepted-label" class="property-label"><g:message code="programItem.accepted.label" default="Accepted" /></span>
					
						<span class="property-value" aria-labelledby="accepted-label"><g:formatBoolean boolean="${programItemInstance?.accepted}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${programItemInstance?.creationDate}">
				<li class="fieldcontain">
					<span id="creationDate-label" class="property-label"><g:message code="programItem.creationDate.label" default="Creation Date" /></span>
					
						<span class="property-value" aria-labelledby="creationDate-label"><g:formatDate date="${programItemInstance?.creationDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${programItemInstance?.timeInHours}">
				<li class="fieldcontain">
					<span id="timeInHours-label" class="property-label"><g:message code="programItem.timeInHours.label" default="Time In Hours" /></span>
					
						<span class="property-value" aria-labelledby="timeInHours-label"><g:fieldValue bean="${programItemInstance}" field="timeInHours"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${programItemInstance?.worker}">
				<li class="fieldcontain">
					<span id="worker-label" class="property-label"><g:message code="programItem.worker.label" default="Worker" /></span>
					
						<g:each in="${programItemInstance.worker}" var="w">
						<span class="property-value" aria-labelledby="worker-label"><g:link controller="programItemWorker" action="show" id="${w.id}">${w?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${programItemInstance?.id}" />
					<g:link class="edit" action="edit" id="${programItemInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
