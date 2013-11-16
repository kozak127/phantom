<%@ page import="org.kozak127.phantom.InEventObject.InEventObjectStaff" %>



<div class="fieldcontain ${hasErrors(bean: inEventObjectStaffInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="inEventObjectStaff.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${org.kozak127.phantom.Reservation.list()}" optionKey="id" required="" value="${inEventObjectStaffInstance?.reservation?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: inEventObjectStaffInstance, field: 'inEventObject', 'error')} required">
	<label for="inEventObject">
		<g:message code="inEventObjectStaff.inEventObject.label" default="In Event Object" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="inEventObject" name="inEventObject.id" from="${org.kozak127.phantom.InEventObject.InEventObject.list()}" optionKey="id" required="" value="${inEventObjectStaffInstance?.inEventObject?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: inEventObjectStaffInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="inEventObjectStaff.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${inEventObjectStaffInstance?.creationDate}"  />
</div>

