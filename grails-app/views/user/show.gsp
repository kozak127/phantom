
<%@ page import="org.kozak127.phantom.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </sec:ifAllGranted>
            </ul>
        </div>
        <div id="show-user" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list user">
            
                <g:if test="${userInstance?.username}">
                <li class="fieldcontain">
                    <span id="username-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>
                    
                        <span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
                    
                </li>
                </g:if>

                <g:if test="${userInstance?.email}">
                <li class="fieldcontain">
                    <span id="email-label" class="property-label"><g:message code="user.email.label" default="Email" /></span>
                    
                        <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${userInstance}" field="email"/></span>
                    
                </li>
                </g:if>
            
                <g:if test="${userInstance?.firstName}">
                <li class="fieldcontain">
                    <span id="firstName-label" class="property-label"><g:message code="user.firstName.label" default="First Name" /></span>
                    
                        <span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${userInstance}" field="firstName"/></span>
                    
                </li>
                </g:if>
            
                <g:if test="${userInstance?.lastName}">
                <li class="fieldcontain">
                    <span id="lastName-label" class="property-label"><g:message code="user.lastName.label" default="Last Name" /></span>
                    
                        <span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${userInstance}" field="lastName"/></span>
                    
                </li>
                </g:if>          
            
                <g:if test="${userInstance?.birthDate}">
                <li class="fieldcontain">
                    <span id="birthDate-label" class="property-label"><g:message code="user.birthDate.label" default="Birth Date" /></span>
                    
                        <span class="property-value" aria-labelledby="birthDate-label"><g:formatDate date="${userInstance?.birthDate}" /></span>
                    
                </li>
                </g:if>
            
            </ol>
            <g:form>
                <fieldset class="buttons">
                    <g:hiddenField name="id" value="${userInstance?.id}" />
                    <g:link class="edit" action="edit" id="${userInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </sec:ifAllGranted>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
