<%@ page import="com.lftechnology.findMe.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'authority', 'error')} required">
    <label for="authority">
        <g:message code="user.authority.label" default="Authority"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="authority" required="" value="${userInstance?.authority}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
    <label for="email">
        <g:message code="user.email.label" default="Email"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="email" required="" value="${userInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="user.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${userInstance?.name}"/>

</div>

