<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../jspf/head.jspf" %>
<%@ include file="../../jspf/user.jspf" %>
<table border="1" class="sortable">
    <thead>
    <tr>
        <td>№</td>
        <td>{{translation.userName}}</td>
        <td>{{translation.userLogin}}</td>
        <td>{{translation.role}}</td>
    </tr>
    </thead>
    <tbody>
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
                <form action="admin/editUser" method="get">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="{{translation.edit}}">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="admin/users" method="post">
    <input type="text" name="id">
    <input type="submit" value="{{translation.search}}">
</form>

<form action="admin/users" method="post">
    <input type="submit" value="{{translation.update}}">
</form>

<form action="admin/newUser" method="get">
    <input type="submit" value="{{translation.add}}">
</form>
</div>
</body>
</html>
