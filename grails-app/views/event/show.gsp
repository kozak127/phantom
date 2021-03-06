
<%@ page import="org.kozak127.phantom.Event" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-event" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </sec:ifAllGranted>
            </ul>
        </div>
        <div id="show-event" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list event">
            
                <g:if test="${eventInstance?.name}">
                <li class="fieldcontain">
                    <span id="name-label" class="property-label"><g:message code="event.name.label" default="Name" /></span>
                    
                        <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${eventInstance}" field="name"/></span>
                    
                </li>
                </g:if>
            
                <g:if test="${eventInstance?.creator}">
                <li class="fieldcontain">
                    <span id="creator-label" class="property-label"><g:message code="event.creator.label" default="Creator" /></span>
                    
                        <span class="property-value" aria-labelledby="creator-label"><g:link controller="user" action="show" id="${eventInstance?.creator?.id}">${eventInstance?.creator?.encodeAsHTML()}</g:link></span>
                    
                </li>
                </g:if>
            
                <g:if test="${eventInstance?.dateEnd}">
                <li class="fieldcontain">
                    <span id="dateEnd-label" class="property-label"><g:message code="event.dateEnd.label" default="Date End" /></span>
                    
                        <span class="property-value" aria-labelledby="dateEnd-label"><g:formatDate date="${eventInstance?.dateEnd}" /></span>
                    
                </li>
                </g:if>
            
                <g:if test="${eventInstance?.dateStart}">
                <li class="fieldcontain">
                    <span id="dateStart-label" class="property-label"><g:message code="event.dateStart.label" default="Date Start" /></span>
                    
                        <span class="property-value" aria-labelledby="dateStart-label"><g:formatDate date="${eventInstance?.dateStart}" /></span>
                    
                </li>
                </g:if>
            
            </ol>
            <g:form>
                <fieldset class="buttons">
                    <g:hiddenField name="id" value="${eventInstance?.id}" />
                    <g:link class="edit" action="edit" id="${eventInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:link class="create" controller="reservation" action="create"><g:message code="view.button.reservation.create" default="Make reservation" /></g:link>
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </sec:ifAllGranted>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
