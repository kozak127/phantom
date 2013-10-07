<%@ page import="org.kozak127.phantom.Staff.EventOrganizer" %>



<div class="fieldcontain ${hasErrors(bean: eventOrganizerInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="eventOrganizer.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${org.kozak127.phantom.Reservation.list()}" optionKey="id" required="" value="${eventOrganizerInstance?.reservation?.id}" class="many-to-one"/>
</div>

