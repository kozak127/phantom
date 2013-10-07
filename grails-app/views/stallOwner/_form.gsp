<%@ page import="org.kozak127.phantom.Staff.StallOwner" %>



<div class="fieldcontain ${hasErrors(bean: stallOwnerInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="stallOwner.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${org.kozak127.phantom.Reservation.list()}" optionKey="id" required="" value="${stallOwnerInstance?.reservation?.id}" class="many-to-one"/>
</div>

