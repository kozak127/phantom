<%@ page import="org.kozak127.phantom.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="event.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${eventInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'creator', 'error')} required">
	<label for="creator">
		<g:message code="event.creator.label" default="Creator" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="creator" name="creator.id" from="${org.kozak127.phantom.User.list()}" optionKey="id" required="" value="${eventInstance?.creator?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'dateEnd', 'error')} required">
	<label for="dateEnd">
		<g:message code="event.dateEnd.label" default="Date End" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateEnd" precision="day"  value="${eventInstance?.dateEnd}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'dateStart', 'error')} required">
	<label for="dateStart">
		<g:message code="event.dateStart.label" default="Date Start" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateStart" precision="day"  value="${eventInstance?.dateStart}"  />
</div>

