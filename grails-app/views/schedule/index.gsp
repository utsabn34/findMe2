<%@ page import="com.lftechnology.findMe.Schedule" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'schedule.label', default: 'Schedule')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-schedule" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-schedule" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="endDate"
                              title="${message(code: 'schedule.endDate.label', default: 'End Date')}"/>

            <g:sortableColumn property="startDate"
                              title="${message(code: 'schedule.startDate.label', default: 'Start Date')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${scheduleInstanceList}" status="i" var="scheduleInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${scheduleInstance.id}">${fieldValue(bean: scheduleInstance, field: "endDate")}</g:link></td>

                <td><g:formatDate date="${scheduleInstance.startDate}"/></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${scheduleInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
