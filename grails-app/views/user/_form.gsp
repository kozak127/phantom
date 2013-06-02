<%@ page import="org.kozak127.phantom.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${userInstance?.password}"/>
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

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'reservation', 'error')} ">
	<label for="reservation">
		<g:message code="user.reservation.label" default="Reservation" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.reservation?}" var="r">
    <li><g:link controller="reservation" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reservation" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reservation.label', default: 'Reservation')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'organizer', 'error')} ">
	<label for="organizer">
		<g:message code="user.organizer.label" default="Organizer" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.organizer?}" var="o">
    <li><g:link controller="organizer" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="organizer" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'organizer.label', default: 'Organizer')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'volunteer', 'error')} ">
	<label for="volunteer">
		<g:message code="user.volunteer.label" default="Volunteer" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.volunteer?}" var="v">
    <li><g:link controller="volunteer" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="volunteer" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'volunteer.label', default: 'Volunteer')])}</g:link>
</li>
</ul>

</div>

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

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'birthDate', 'error')} required">
	<label for="birthDate">
		<g:message code="user.birthDate.label" default="Birth Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="birthDate" precision="day"  value="${userInstance?.birthDate}"  />
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
