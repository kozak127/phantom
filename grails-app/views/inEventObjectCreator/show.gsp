
<%@ page import="org.kozak127.phantom.InEventObject.InEventObjectCreator" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-inEventObjectCreator" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-inEventObjectCreator" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list inEventObjectCreator">
            
                <g:if test="${inEventObjectCreatorInstance?.reservation}">
                <li class="fieldcontain">
                    <span id="reservation-label" class="property-label"><g:message code="inEventObjectCreator.reservation.label" default="Reservation" /></span>
                    
                        <span class="property-value" aria-labelledby="reservation-label"><g:link controller="reservation" action="show" id="${inEventObjectCreatorInstance?.reservation?.id}">${inEventObjectCreatorInstance?.reservation?.encodeAsHTML()}</g:link></span>
                    
                </li>
                </g:if>
            
                <g:if test="${inEventObjectCreatorInstance?.creationDate}">
                <li class="fieldcontain">
                    <span id="creationDate-label" class="property-label"><g:message code="inEventObjectCreator.creationDate.label" default="Creation Date" /></span>
                    
                        <span class="property-value" aria-labelledby="creationDate-label"><g:formatDate date="${inEventObjectCreatorInstance?.creationDate}" /></span>
                    
                </li>
                </g:if>
            
            </ol>
            <g:form>
                <fieldset class="buttons">
                    <g:hiddenField name="id" value="${inEventObjectCreatorInstance?.id}" />
                    <g:link class="edit" action="edit" id="${inEventObjectCreatorInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
