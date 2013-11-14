
<%@ page import="org.kozak127.phantom.InEventObject.InEventObjectWorker" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'inEventObjectWorker.label', default: 'InEventObjectWorker')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-inEventObjectWorker" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-inEventObjectWorker" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                    <tr>
                    
                        <th><g:message code="inEventObjectWorker.reservation.label" default="Reservation" /></th>
                    
                        <th><g:message code="inEventObjectWorker.inEventObject.label" default="In Event Object" /></th>
                    
                        <g:sortableColumn property="accepted" title="${message(code: 'inEventObjectWorker.accepted.label', default: 'Accepted')}" />
                    
                        <g:sortableColumn property="creationDate" title="${message(code: 'inEventObjectWorker.creationDate.label', default: 'Creation Date')}" />
                    
                    </tr>
                </thead>
                <tbody>
                <g:each in="${inEventObjectWorkerInstanceList}" status="i" var="inEventObjectWorkerInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    
                        <td><g:link action="show" id="${inEventObjectWorkerInstance.id}">${fieldValue(bean: inEventObjectWorkerInstance, field: "reservation")}</g:link></td>
                    
                        <td>${fieldValue(bean: inEventObjectWorkerInstance, field: "inEventObject")}</td>
                    
                        <td><g:formatBoolean boolean="${inEventObjectWorkerInstance.accepted}" /></td>
                    
                        <td><g:formatDate date="${inEventObjectWorkerInstance.creationDate}" /></td>
                    
                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${inEventObjectWorkerInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
