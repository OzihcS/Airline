<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<%@ include file="../../jspf/header.jspf" %>

<form action="dispatcher/newRequest" method="post">
    <input type="hidden" name="fromId" value="${sessionScope.currentUser.id}">
    <select name="toId">
        <c:forEach items="${admins}" var="admin">
            <option value="${admin.id}">${admin.name}</option>
        </c:forEach>
    </select>
    <textarea rows="10" cols="45" name="message"></textarea>
    <input type="submit" value="{{translation.send}}">
</form>

</body>
</html>
