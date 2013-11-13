<%@ page import="org.kozak127.phantom.InEventObject.InEventObjectWorker" %>



<div class="fieldcontain ${hasErrors(bean: inEventObjectWorkerInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="inEventObjectWorker.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${org.kozak127.phantom.Reservation.list()}" optionKey="id" required="" value="${inEventObjectWorkerInstance?.reservation?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: inEventObjectWorkerInstance, field: 'inEventObject', 'error')} required">
	<label for="inEventObject">
		<g:message code="inEventObjectWorker.inEventObject.label" default="In Event Object" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="inEventObject" name="inEventObject.id" from="${org.kozak127.phantom.InEventObject.InEventObject.list()}" optionKey="id" required="" value="${inEventObjectWorkerInstance?.inEventObject?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: inEventObjectWorkerInstance, field: 'accepted', 'error')} ">
	<label for="accepted">
		<g:message code="inEventObjectWorker.accepted.label" default="Accepted" />
		
	</label>
	<g:checkBox name="accepted" value="${inEventObjectWorkerInstance?.accepted}" />
</div>

<div class="fieldcontain ${hasErrors(bean: inEventObjectWorkerInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="inEventObjectWorker.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${inEventObjectWorkerInstance?.creationDate}"  />
</div>

