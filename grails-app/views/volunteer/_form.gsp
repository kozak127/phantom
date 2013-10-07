<%@ page import="org.kozak127.phantom.Staff.Volunteer" %>



<div class="fieldcontain ${hasErrors(bean: volunteerInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="volunteer.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${org.kozak127.phantom.Reservation.list()}" optionKey="id" required="" value="${volunteerInstance?.reservation?.id}" class="many-to-one"/>
</div>

