<%@ page import="org.kozak127.phantom.Stall" %>



<div class="fieldcontain ${hasErrors(bean: stallInstance, field: 'accepted', 'error')} ">
	<label for="accepted">
		<g:message code="stall.accepted.label" default="Accepted" />
		
	</label>
	<g:checkBox name="accepted" value="${stallInstance?.accepted}" />
</div>

<div class="fieldcontain ${hasErrors(bean: stallInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="stall.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${stallInstance?.creationDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: stallInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="stall.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${org.kozak127.phantom.Event.list()}" optionKey="id" required="" value="${stallInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stallInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="stall.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${org.kozak127.phantom.User.list()}" optionKey="id" required="" value="${stallInstance?.owner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stallInstance, field: 'workers', 'error')} ">
	<label for="workers">
		<g:message code="stall.workers.label" default="Workers" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${stallInstance?.workers?}" var="w">
    <li><g:link controller="stallWorker" action="show" id="${w.id}">${w?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="stallWorker" action="create" params="['stall.id': stallInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'stallWorker.label', default: 'StallWorker')])}</g:link>
</li>
</ul>

</div>

