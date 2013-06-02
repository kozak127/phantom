<%@ page import="org.kozak127.phantom.Organizer" %>



<div class="fieldcontain ${hasErrors(bean: organizerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="organizer.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${org.kozak127.phantom.User.list()}" optionKey="id" required="" value="${organizerInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: organizerInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="organizer.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${org.kozak127.phantom.Event.list()}" optionKey="id" required="" value="${organizerInstance?.event?.id}" class="many-to-one"/>
</div>

