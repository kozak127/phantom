<%@ page import="org.kozak127.phantom.Staff.ProgramItemCreator" %>



<div class="fieldcontain ${hasErrors(bean: programItemCreatorInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="programItemCreator.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${org.kozak127.phantom.Reservation.list()}" optionKey="id" required="" value="${programItemCreatorInstance?.reservation?.id}" class="many-to-one"/>
</div>

