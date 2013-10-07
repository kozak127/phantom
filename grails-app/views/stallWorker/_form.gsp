<%@ page import="org.kozak127.phantom.StallWorker" %>



<div class="fieldcontain ${hasErrors(bean: stallWorkerInstance, field: 'stall', 'error')} required">
	<label for="stall">
		<g:message code="stallWorker.stall.label" default="Stall" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="stall" name="stall.id" from="${org.kozak127.phantom.Stall.list()}" optionKey="id" required="" value="${stallWorkerInstance?.stall?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stallWorkerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="stallWorker.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${org.kozak127.phantom.User.list()}" optionKey="id" required="" value="${stallWorkerInstance?.user?.id}" class="many-to-one"/>
</div>

