<%@ page import="org.kozak127.phantom.ProgramItem" %>



<div class="fieldcontain ${hasErrors(bean: programItemInstance, field: 'creator', 'error')} required">
	<label for="creator">
		<g:message code="programItem.creator.label" default="Creator" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="creator" name="creator.id" from="${org.kozak127.phantom.User.list()}" optionKey="id" required="" value="${programItemInstance?.creator?.id}" class="many-to-one"/>
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

<div class="fieldcontain ${hasErrors(bean: programItemInstance, field: 'timeInHours', 'error')} required">
	<label for="timeInHours">
		<g:message code="programItem.timeInHours.label" default="Time In Hours" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="timeInHours" type="number" value="${programItemInstance.timeInHours}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: programItemInstance, field: 'worker', 'error')} ">
	<label for="worker">
		<g:message code="programItem.worker.label" default="Worker" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${programItemInstance?.worker?}" var="w">
    <li><g:link controller="programItemWorker" action="show" id="${w.id}">${w?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="programItemWorker" action="create" params="['programItem.id': programItemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'programItemWorker.label', default: 'ProgramItemWorker')])}</g:link>
</li>
</ul>

</div>

