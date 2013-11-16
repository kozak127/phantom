
<%@ page import="org.kozak127.phantom.Event" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-event" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </sec:ifAllGranted>
            </ul>
        </div>
        <div id="list-event" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                    <tr>
                    
                        <g:sortableColumn property="name" title="${message(code: 'event.name.label', default: 'Name')}" />
                    
                        <th><g:message code="event.creator.label" default="Creator" /></th>
                    
                        <g:sortableColumn property="dateEnd" title="${message(code: 'event.dateEnd.label', default: 'Date End')}" />
                    
                        <g:sortableColumn property="dateStart" title="${message(code: 'event.dateStart.label', default: 'Date Start')}" />
                    
                    </tr>
                </thead>
                <tbody>
                <g:each in="${eventInstanceList}" status="i" var="eventInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    
                        <td><g:link action="show" id="${eventInstance.id}">${fieldValue(bean: eventInstance, field: "name")}</g:link></td>
                    
                        <td>${fieldValue(bean: eventInstance, field: "creator")}</td>
                    
                        <td><g:formatDate date="${eventInstance.dateEnd}" /></td>
                    
                        <td><g:formatDate date="${eventInstance.dateStart}" /></td>
                    
                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${eventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
