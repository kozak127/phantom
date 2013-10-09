<%@ page import="org.kozak127.phantom.Stall" %>



<div class="fieldcontain ${hasErrors(bean: stallInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="stall.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${org.kozak127.phantom.Staff.StallOwner.list()}" optionKey="id" required="" value="${stallInstance?.owner?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stallInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="stall.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${org.kozak127.phantom.Event.list()}" optionKey="id" required="" value="${stallInstance?.event?.id}" class="many-to-one"/>
</div>

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

<div class="fieldcontain ${hasErrors(bean: stallInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="stall.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${stallInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stallInstance, field: 'paid', 'error')} ">
	<label for="paid">
		<g:message code="stall.paid.label" default="Paid" />
		
	</label>
	<g:checkBox name="paid" value="${stallInstance?.paid}" />
</div>

<div class="fieldcontain ${hasErrors(bean: stallInstance, field: 'size', 'error')} required">
	<label for="size">
		<g:message code="stall.size.label" default="Size" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="size" type="number" value="${stallInstance.size}" required=""/>
</div>

