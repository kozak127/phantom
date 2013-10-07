<%@ page import="org.kozak127.phantom.PointOfTheProgram" %>



<div class="fieldcontain ${hasErrors(bean: pointOfTheProgramInstance, field: 'creator', 'error')} required">
	<label for="creator">
		<g:message code="pointOfTheProgram.creator.label" default="Creator" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="creator" name="creator.id" from="${org.kozak127.phantom.User.list()}" optionKey="id" required="" value="${pointOfTheProgramInstance?.creator?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointOfTheProgramInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="pointOfTheProgram.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${org.kozak127.phantom.Event.list()}" optionKey="id" required="" value="${pointOfTheProgramInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pointOfTheProgramInstance, field: 'accepted', 'error')} ">
	<label for="accepted">
		<g:message code="pointOfTheProgram.accepted.label" default="Accepted" />
		
	</label>
	<g:checkBox name="accepted" value="${pointOfTheProgramInstance?.accepted}" />
</div>

<div class="fieldcontain ${hasErrors(bean: pointOfTheProgramInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="pointOfTheProgram.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${pointOfTheProgramInstance?.creationDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: pointOfTheProgramInstance, field: 'timeInHours', 'error')} required">
	<label for="timeInHours">
		<g:message code="pointOfTheProgram.timeInHours.label" default="Time In Hours" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="timeInHours" type="number" value="${pointOfTheProgramInstance.timeInHours}" required=""/>
</div>

