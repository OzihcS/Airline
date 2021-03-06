<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@ include file="../../jspf/header.jspf" %>
<form action="admin/users" method="get">
    <input class="btn" type="submit" value="{{translation.users}}">
</form>
<form action="admin/flights" method="get">
    <input class="btn" type="submit" value="{{translation.flights}}">
</form>
<div id="st">
    <label> {{translation.executed}}: ${statistic.execute}</label><br>
    <label> {{translation.rejected}}: ${statistic.reject}</label>
</div>
<div class="spoiler-wrapper">
    <c:forEach items="${requests}" var="request">
        <div class="spoiler folded" href="javascript:void(0);">
            № ${request.id} ${request.title}
        </div>
        <div class="spoiler-text">
            <label>{{translation.sender}}: ${request.from}</label>
            <hr>
            <label>{{translation.message}}: ${request.message}</label>
            <c:if test="${f:isUnconfirmed(request)}">
                <form class="st-change" action="admin/home" method="post">
                    <input type="hidden" name="adminId" value="${request.toId}">
                    <input type="hidden" name="id" value="${request.id}">
                    <select id="status" name="status">
                        <option value="EXECUTED">{{translation.executed}}</option>
                        <option selected value="REJECTED">{{translation.rejected}}</option>
                    </select>
                    <input class="btn" type="submit" value="{{translation.change}}">
                </form>
            </c:if>
            <c:if test="${!f:isUnconfirmed(request)}">
                <p>{{translation.status}}:
                    <c:if test="${f:isExecuted(request)}">
                        {{translation.executed}}
                    </c:if>
                    <c:if test="${f:isRejected(request)}">
                        {{translation.rejected}}
                    </c:if>
                </p>
                <form action="admin/deleteRequest" method="get">
                    <input type="hidden" name="id" value="${request.id}">
                    <input class="btn" type="submit" value="{{translation.delete}}">
                </form>
            </c:if>
        </div>
    </c:forEach>
</div>
</html>
