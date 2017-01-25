<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/header.jspf" %>

<div class="spoiler-wrapper">
    <c:forEach items="${requests}" var="request">
        <div class="spoiler folded" href="javascript:void(0);"><a>${request.title} ({{translation.from
            }} ${request.fromId}</a>
        </div>
        <div class="spoiler-text"><p>${request.message}</p>
            <c:if test="${request.status == 'UNCONFIRMED'}">
                <form action="admin/home" method="post">
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
<form action="admin/users" method="get">
    <input class="btn" type="submit" value="{{translation.users}}">
</form>
<form action="admin/flights" method="get">
    <input class="btn" type="submit" value="{{translation.flights}}">
</form>

</html>
