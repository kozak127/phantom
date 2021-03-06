
<%@ page import="org.kozak127.phantom.Volunteer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'volunteer.label', default: 'Volunteer')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-volunteer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-volunteer" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                    <tr>
                    
                        <g:sortableColumn property="accepted" title="${message(code: 'volunteer.accepted.label', default: 'Accepted')}" />
                    
                        <g:sortableColumn property="organizer" title="${message(code: 'volunteer.organizer.label', default: 'Organizer')}" />
                    
                    	<g:sortableColumn property="event" title="${message(code: 'volunteer.event.label', default: 'Event')}" />
                    
                        <th><g:message code="volunteer.user.label" default="User" /></th>
                    
                    </tr>
                </thead>
                <tbody>
                <g:each in="${volunteerInstanceList}" status="i" var="volunteerInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    
                        <td><g:link action="show" id="${volunteerInstance.id}">${fieldValue(bean: volunteerInstance, field: "accepted")}</g:link></td>
                    
                        <td><g:formatBoolean boolean="${volunteerInstance.organizer}" /></td>
                    
                    	<td>${fieldValue(bean: volunteerInstance, field: "reservation.event.name")}</td>
                    
                        <td>${fieldValue(bean: volunteerInstance, field: "reservation.user.username")}</td>
                    
                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${volunteerInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
