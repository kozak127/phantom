<%@ page import="org.kozak127.phantom.InEventObject.InEventObject" %>



<div class="fieldcontain ${hasErrors(bean: inEventObjectInstance, field: 'creator', 'error')} required">
	<label for="creator">
		<g:message code="inEventObject.creator.label" default="Creator" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="creator" name="creator.id" from="${org.kozak127.phantom.InEventObject.InEventObjectCreator.list()}" optionKey="id" required="" value="${inEventObjectInstance?.creator?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: inEventObjectInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="inEventObject.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${org.kozak127.phantom.Event.list()}" optionKey="id" required="" value="${inEventObjectInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: inEventObjectInstance, field: 'accepted', 'error')} ">
	<label for="accepted">
		<g:message code="inEventObject.accepted.label" default="Accepted" />
		
	</label>
	<g:checkBox name="accepted" value="${inEventObjectInstance?.accepted}" />
</div>

<div class="fieldcontain ${hasErrors(bean: inEventObjectInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="inEventObject.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${inEventObjectInstance?.creationDate}"  />
</div>

