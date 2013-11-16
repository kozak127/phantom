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

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'creationDate', 'error')} required">
    <label for="creationDate">
        <g:message code="reservation.creationDate.label" default="Creation Date" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="creationDate" disabled="true" value="${reservationInstance?.creationDate}"  />
</div>

