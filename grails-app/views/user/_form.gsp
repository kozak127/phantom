<%@ page import="org.kozak127.phantom.User" %>


<sec:ifAllGranted roles="ROLE_ADMIN">
    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
        <label for="username">
            <g:message code="user.username.label" default="Username" />
            <span class="required-indicator">*</span>
        </label>
        <g:textField name="username" required="" value="${userInstance?.username}"/>
    </div>
</sec:ifAllGranted>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
    <label for="password">
        <g:message code="user.password.label" default="New password" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="password" required="" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'confirmPassword', 'error')} required">
    <label for="confirmPassword">
        <g:message code="user.password.confirm.label" default="Confirm password" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="confirmPassword" required="" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
    <label for="email">
        <g:message code="user.email.label" default="Email" />
        <span class="required-indicator">*</span>
    </label>
    <g:field type="email" name="email" required="" value="${userInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
    <label for="firstName">
        <g:message code="user.firstName.label" default="First Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="firstName" required="" value="${userInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
    <label for="lastName">
        <g:message code="user.lastName.label" default="Last Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="lastName" required="" value="${userInstance?.lastName}"/>
</div>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'birthDate', 'error')} required">
    <label for="birthDate">
        <g:message code="user.birthDate.label" default="Birth Date" />
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="birthDate" precision="day"  value="${userInstance?.birthDate}"  />
</div>

<sec:ifAllGranted roles="ROLE_ADMIN">
    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountExpired', 'error')} ">
        <label for="accountExpired">
            <g:message code="user.accountExpired.label" default="Account Expired" />
            
        </label>
        <g:checkBox name="accountExpired" value="${userInstance?.accountExpired}" />
    </div>

    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountLocked', 'error')} ">
        <label for="accountLocked">
            <g:message code="user.accountLocked.label" default="Account Locked" />
            
        </label>
        <g:checkBox name="accountLocked" value="${userInstance?.accountLocked}" />
    </div>

    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
        <label for="enabled">
            <g:message code="user.enabled.label" default="Enabled" />
            
        </label>
        <g:checkBox name="enabled" value="${userInstance?.enabled}" />
    </div>

    <div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
        <label for="passwordExpired">
            <g:message code="user.passwordExpired.label" default="Password Expired" />
            
        </label>
        <g:checkBox name="passwordExpired" value="${userInstance?.passwordExpired}" />
    </div>
</sec:ifAllGranted>
