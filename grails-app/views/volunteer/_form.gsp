<%@ page import="org.kozak127.phantom.Volunteer" %>



<div class="fieldcontain ${hasErrors(bean: volunteerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="volunteer.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${org.kozak127.phantom.User.list()}" optionKey="id" required="" value="${volunteerInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: volunteerInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="volunteer.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${org.kozak127.phantom.Event.list()}" optionKey="id" required="" value="${volunteerInstance?.event?.id}" class="many-to-one"/>
</div>

