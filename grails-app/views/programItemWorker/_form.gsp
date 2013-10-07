<%@ page import="org.kozak127.phantom.Staff.ProgramItemWorker" %>



<div class="fieldcontain ${hasErrors(bean: programItemWorkerInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="programItemWorker.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${org.kozak127.phantom.Reservation.list()}" optionKey="id" required="" value="${programItemWorkerInstance?.reservation?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: programItemWorkerInstance, field: 'programItem', 'error')} required">
	<label for="programItem">
		<g:message code="programItemWorker.programItem.label" default="Program Item" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="programItem" name="programItem.id" from="${org.kozak127.phantom.ProgramItem.list()}" optionKey="id" required="" value="${programItemWorkerInstance?.programItem?.id}" class="many-to-one"/>
</div>

