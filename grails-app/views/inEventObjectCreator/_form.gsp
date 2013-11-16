<%@ page import="org.kozak127.phantom.InEventObject.InEventObjectCreator" %>



<div class="fieldcontain ${hasErrors(bean: inEventObjectCreatorInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="inEventObjectCreator.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${org.kozak127.phantom.Reservation.list()}" optionKey="id" required="" value="${inEventObjectCreatorInstance?.reservation?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: inEventObjectCreatorInstance, field: 'inEventObject', 'error')} required">
	<label for="inEventObject">
		<g:message code="inEventObjectCreator.inEventObject.label" default="In Event Object" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="inEventObject" name="inEventObject.id" from="${org.kozak127.phantom.InEventObject.InEventObject.list()}" optionKey="id" required="" value="${inEventObjectCreatorInstance?.inEventObject?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: inEventObjectCreatorInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="inEventObjectCreator.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${inEventObjectCreatorInstance?.creationDate}"  />
</div>

