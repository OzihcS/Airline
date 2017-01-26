<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/header.jspf" %>
<body>
<form class="request" action="dispatcher/newRequest" method="post">
    <input type="hidden" name="fromId" value="${sessionScope.currentUser.name}">
    <input type="text" name="title" placeholder="{{translation.title}}">
    <textarea rows="10" cols="45" name="message" placeholder="{{translation.message}}"></textarea>
    <select id="role" name="toId">
        <c:forEach items="${admins}" var="admin">
            <option value="${admin.id}">${admin.name}</option>
        </c:forEach>
    </select>
    <input class="btn" type="submit" value="{{translation.send}}">
</form>
</body>
</html>
