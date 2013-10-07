<%@ page import="org.kozak127.phantom.ProgramItem" %>



<div class="fieldcontain ${hasErrors(bean: programItemInstance, field: 'creator', 'error')} required">
	<label for="creator">
		<g:message code="programItem.creator.label" default="Creator" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="creator" name="creator.id" from="${org.kozak127.phantom.Staff.ProgramItemCreator.list()}" optionKey="id" required="" value="${programItemInstance?.creator?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: programItemInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="programItem.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${org.kozak127.phantom.Event.list()}" optionKey="id" required="" value="${programItemInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: programItemInstance, field: 'accepted', 'error')} ">
	<label for="accepted">
		<g:message code="programItem.accepted.label" default="Accepted" />
		
	</label>
	<g:checkBox name="accepted" value="${programItemInstance?.accepted}" />
</div>

<div class="fieldcontain ${hasErrors(bean: programItemInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="programItem.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${programItemInstance?.creationDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: programItemInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="programItem.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${programItemInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: programItemInstance, field: 'timeInHours', 'error')} required">
	<label for="timeInHours">
		<g:message code="programItem.timeInHours.label" default="Time In Hours" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="timeInHours" type="number" value="${programItemInstance.timeInHours}" required=""/>
</div>

