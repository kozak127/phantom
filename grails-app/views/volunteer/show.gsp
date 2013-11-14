
<%@ page import="org.kozak127.phantom.Volunteer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'volunteer.label', default: 'Volunteer')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-volunteer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-volunteer" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list volunteer">
            
                <g:if test="${volunteerInstance?.accepted}">
                <li class="fieldcontain">
                    <span id="accepted-label" class="property-label"><g:message code="volunteer.accepted.label" default="Accepted" /></span>
                    
                        <span class="property-value" aria-labelledby="accepted-label"><g:formatBoolean boolean="${volunteerInstance?.accepted}" /></span>
                    
                </li>
                </g:if>
            
                <g:if test="${volunteerInstance?.organizer}">
                <li class="fieldcontain">
                    <span id="organizer-label" class="property-label"><g:message code="volunteer.organizer.label" default="Organizer" /></span>
                    
                        <span class="property-value" aria-labelledby="organizer-label"><g:formatBoolean boolean="${volunteerInstance?.organizer}" /></span>
                    
                </li>
                </g:if>
            
                <g:if test="${volunteerInstance?.reservation}">
                <li class="fieldcontain">
                    <span id="reservation-label" class="property-label"><g:message code="volunteer.reservation.label" default="Reservation" /></span>
                    
                        <span class="property-value" aria-labelledby="reservation-label"><g:link controller="reservation" action="show" id="${volunteerInstance?.reservation?.id}">${volunteerInstance?.reservation?.encodeAsHTML()}</g:link></span>
                    
                </li>
                </g:if>
            
            </ol>
            <g:form>
                <fieldset class="buttons">
                    <g:hiddenField name="id" value="${volunteerInstance?.id}" />
                    <g:link class="edit" action="edit" id="${volunteerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
