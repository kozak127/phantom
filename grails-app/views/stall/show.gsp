
<%@ page import="org.kozak127.phantom.Stall" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'stall.label', default: 'Stall')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-stall" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-stall" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list stall">
			
				<g:if test="${stallInstance?.owner}">
				<li class="fieldcontain">
					<span id="owner-label" class="property-label"><g:message code="stall.owner.label" default="Owner" /></span>
					
						<span class="property-value" aria-labelledby="owner-label"><g:link controller="stallOwner" action="show" id="${stallInstance?.owner?.id}">${stallInstance?.owner?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${stallInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="stall.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:link controller="event" action="show" id="${stallInstance?.event?.id}">${stallInstance?.event?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${stallInstance?.accepted}">
				<li class="fieldcontain">
					<span id="accepted-label" class="property-label"><g:message code="stall.accepted.label" default="Accepted" /></span>
					
						<span class="property-value" aria-labelledby="accepted-label"><g:formatBoolean boolean="${stallInstance?.accepted}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${stallInstance?.creationDate}">
				<li class="fieldcontain">
					<span id="creationDate-label" class="property-label"><g:message code="stall.creationDate.label" default="Creation Date" /></span>
					
						<span class="property-value" aria-labelledby="creationDate-label"><g:formatDate date="${stallInstance?.creationDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${stallInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="stall.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${stallInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${stallInstance?.paid}">
				<li class="fieldcontain">
					<span id="paid-label" class="property-label"><g:message code="stall.paid.label" default="Paid" /></span>
					
						<span class="property-value" aria-labelledby="paid-label"><g:formatBoolean boolean="${stallInstance?.paid}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${stallInstance?.size}">
				<li class="fieldcontain">
					<span id="size-label" class="property-label"><g:message code="stall.size.label" default="Size" /></span>
					
						<span class="property-value" aria-labelledby="size-label"><g:fieldValue bean="${stallInstance}" field="size"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${stallInstance?.id}" />
					<g:link class="edit" action="edit" id="${stallInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
