<%@ page import="org.kozak127.phantom.Staff.StallWorker" %>



<div class="fieldcontain ${hasErrors(bean: stallWorkerInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="stallWorker.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${org.kozak127.phantom.Reservation.list()}" optionKey="id" required="" value="${stallWorkerInstance?.reservation?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stallWorkerInstance, field: 'stall', 'error')} required">
	<label for="stall">
		<g:message code="stallWorker.stall.label" default="Stall" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="stall" name="stall.id" from="${org.kozak127.phantom.Stall.list()}" optionKey="id" required="" value="${stallWorkerInstance?.stall?.id}" class="many-to-one"/>
</div>

