<%@ page import="org.kozak127.phantom.Reservation" %>



<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'paid', 'error')} ">
	<label for="paid">
		<g:message code="reservation.paid.label" default="Paid" />
		
	</label>
	<g:checkBox name="paid" value="${reservationInstance?.paid}" />
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="reservation.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${org.kozak127.phantom.User.list()}" optionKey="id" required="" value="${reservationInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="reservation.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${org.kozak127.phantom.Event.list()}" optionKey="id" required="" value="${reservationInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'dateOfCreation', 'error')} required">
	<label for="dateOfCreation">
		<g:message code="reservation.dateOfCreation.label" default="Date Of Creation" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateOfCreation" precision="day"  value="${reservationInstance?.dateOfCreation}"  />
</div>

