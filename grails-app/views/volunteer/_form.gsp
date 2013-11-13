<%@ page import="org.kozak127.phantom.Volunteer" %>



<div class="fieldcontain ${hasErrors(bean: volunteerInstance, field: 'accepted', 'error')} ">
	<label for="accepted">
		<g:message code="volunteer.accepted.label" default="Accepted" />
		
	</label>
	<g:checkBox name="accepted" value="${volunteerInstance?.accepted}" />
</div>

<div class="fieldcontain ${hasErrors(bean: volunteerInstance, field: 'organizer', 'error')} ">
	<label for="organizer">
		<g:message code="volunteer.organizer.label" default="Organizer" />
		
	</label>
	<g:checkBox name="organizer" value="${volunteerInstance?.organizer}" />
</div>

<div class="fieldcontain ${hasErrors(bean: volunteerInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="volunteer.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reservation" name="reservation.id" from="${org.kozak127.phantom.Reservation.list()}" optionKey="id" required="" value="${volunteerInstance?.reservation?.id}" class="many-to-one"/>
</div>

