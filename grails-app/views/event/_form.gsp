<%@ page import="org.kozak127.phantom.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="event.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${eventInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'reservations', 'error')} ">
	<label for="reservations">
		<g:message code="event.reservations.label" default="Reservations" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${eventInstance?.reservations?}" var="r">
    <li><g:link controller="reservation" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reservation" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reservation.label', default: 'Reservation')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'organizators', 'error')} ">
	<label for="organizators">
		<g:message code="event.organizators.label" default="Organizators" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${eventInstance?.organizators?}" var="o">
    <li><g:link controller="organizer" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="organizer" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'organizer.label', default: 'Organizer')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'volunteers', 'error')} ">
	<label for="volunteers">
		<g:message code="event.volunteers.label" default="Volunteers" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${eventInstance?.volunteers?}" var="v">
    <li><g:link controller="volunteer" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="volunteer" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'volunteer.label', default: 'Volunteer')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'dateEnd', 'error')} required">
	<label for="dateEnd">
		<g:message code="event.dateEnd.label" default="Date End" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateEnd" precision="day"  value="${eventInstance?.dateEnd}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'dateStart', 'error')} required">
	<label for="dateStart">
		<g:message code="event.dateStart.label" default="Date Start" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateStart" precision="day"  value="${eventInstance?.dateStart}"  />
</div>

