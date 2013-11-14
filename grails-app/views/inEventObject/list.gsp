
<%@ page import="org.kozak127.phantom.InEventObject.InEventObject" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'inEventObject.label', default: 'InEventObject')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-inEventObject" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-inEventObject" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                    <tr>
                    
                        <th><g:message code="inEventObject.creator.label" default="Creator" /></th>
                    
                        <th><g:message code="inEventObject.event.label" default="Event" /></th>
                    
                        <g:sortableColumn property="accepted" title="${message(code: 'inEventObject.accepted.label', default: 'Accepted')}" />
                    
                        <g:sortableColumn property="creationDate" title="${message(code: 'inEventObject.creationDate.label', default: 'Creation Date')}" />
                    
                    </tr>
                </thead>
                <tbody>
                <g:each in="${inEventObjectInstanceList}" status="i" var="inEventObjectInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    
                        <td><g:link action="show" id="${inEventObjectInstance.id}">${fieldValue(bean: inEventObjectInstance, field: "creator")}</g:link></td>
                    
                        <td>${fieldValue(bean: inEventObjectInstance, field: "event")}</td>
                    
                        <td><g:formatBoolean boolean="${inEventObjectInstance.accepted}" /></td>
                    
                        <td><g:formatDate date="${inEventObjectInstance.creationDate}" /></td>
                    
                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${inEventObjectInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
