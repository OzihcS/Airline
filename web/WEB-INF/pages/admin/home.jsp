<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/header.jspf" %>

<form action="admin/users" method="get">
    <input class="btn" type="submit" value="{{translation.users}}">
</form>
<form action="admin/flights" method="get">
    <input class="btn" type="submit" value="{{translation.flights}}">
</form>

<div class="spoiler-wrapper">
    <c:forEach items="${requests}" var="request">
        <div class="spoiler folded" href="javascript:void(0);">
            № ${request.id} ${request.title}
        </div>
        <div class="spoiler-text">
            <label>From: ${request.from}</label>
            <hr>
            <label>Message: ${request.message}</label>
            <c:if test="${request.status == 'UNCONFIRMED'}">
                <form class="st-change" action="admin/home" method="post">
                    <input type="hidden" name="id" value="${request.id}">
                    <select id="status" name="status">
                        <option value="EXECUTED">Executed</option>
                        <option selected value="REJECTED">Rejected</option>
                    </select>
                    <input class="btn" type="submit" value="Change">
                </form>
            </c:if>
            <c:if test="${request.status != 'UNCONFIRMED'}">
                <p>${request.status}</p>
            </c:if>
        </div>
    </c:forEach>
</div>
</html>
