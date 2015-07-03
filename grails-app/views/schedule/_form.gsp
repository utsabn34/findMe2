<%@ page import="com.lftechnology.findMe.Schedule" %>



<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'endDate', 'error')} required">
    <label for="endDate">
        <g:message code="schedule.endDate.label" default="End Date"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="endDate" precision="day" value="${scheduleInstance?.endDate}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'startDate', 'error')} required">
    <label for="startDate">
        <g:message code="schedule.startDate.label" default="Start Date"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="startDate" precision="day" value="${scheduleInstance?.startDate}"/>

</div>

