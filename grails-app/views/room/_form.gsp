<%@ page import="com.lftechnology.findMe.Room" %>



<div class="fieldcontain ${hasErrors(bean: roomInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="room.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${roomInstance?.name}"/>

</div>

