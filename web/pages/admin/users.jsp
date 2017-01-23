<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../jspf/head.jspf" %>
<%@ include file="../../jspf/user.jspf" %>
<table border="1">
    <tr>
        <td>№</td>
        <td>{{translation.userName}}</td>
        <td>{{translation.userLogin}}</td>
        <td>{{translation.role}}</td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.role}</td>
            <td>
                <form action="admin/delete" method="get">
                    <input name="id" value="${user.id}" type="hidden">
                    <input type="submit" value="{{translation.delete}}">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="admin/newUser" method="get">
    <input type="submit" value="{{translation.add}}">
</form>
</div>
</body>
</html>
