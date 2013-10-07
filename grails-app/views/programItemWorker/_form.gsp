<%@ page import="org.kozak127.phantom.ProgramItemWorker" %>



<div class="fieldcontain ${hasErrors(bean: programItemWorkerInstance, field: 'programItem', 'error')} required">
	<label for="programItem">
		<g:message code="programItemWorker.programItem.label" default="Program Item" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="programItem" name="programItem.id" from="${org.kozak127.phantom.ProgramItem.list()}" optionKey="id" required="" value="${programItemWorkerInstance?.programItem?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: programItemWorkerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="programItemWorker.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${org.kozak127.phantom.User.list()}" optionKey="id" required="" value="${programItemWorkerInstance?.user?.id}" class="many-to-one"/>
</div>

