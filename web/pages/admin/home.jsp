<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<%@ include file="../../jspf/user.jspf" %>

<table border="1">
    <c:forEach items="${requests}" var="request">
        <details>
            <summary>Request from user ${request.fromId} </summary>
            <p>${request.message}</p>
            <c:if test="${request.status == 'UNCONFIRMED'}">
                <form action="admin/home" method="post">
                    <input type="hidden" name="id" value="${request.id}">
                    <select name="status">
                        <option value="EXECUTED">Executed</option>
                        <option selected value="REJECTED">Rejected</option>
                    </select>
                    <input type="submit" value="Change">
                </form>
            </c:if>
            <c:if test="${request.status != 'UNCONFIRMED'}">
                <p>${request.status}</p>
            </c:if>
        </details>
    </c:forEach>
</table>
<form action="admin/users" method="get">
    <input type="submit" value="{{translation.users}}">
</form>
<form action="admin/flights" method="get">
    <input type="submit" value="{{translation.flights}}">
</form>
</body>
</html>
