<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../jspf/head.jspf" %>
<%@ include file="../../jspf/header.jspf" %>

<%--<form action="admin/users" method="post">--%>
    <%--<input type="text" name="id">--%>
    <%--<input type="submit" value="{{translation.search}}">--%>
<%--</form>--%>


<table class="sortable">
    <thead>
    <tr>
        <th>№</th>
        <th>{{translation.userName}}</th>
        <th>{{translation.userLogin}}</th>
        <th>{{translation.role}}</th>
        <th>Action</th>
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
                <form action="admin/editUser" method="get">
                    <input type="hidden" name="id" value="${user.id}">
                    <input class="btn" type="submit" value="{{translation.edit}}">
                </form>
                <form action="admin/delete" method="get">
                    <input name="id" value="${user.id}" type="hidden">
                    <input class="btn" type="submit" value="{{translation.delete}}">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <td></td>
    <td></td>
    <td></td>
    <td>
        <form action="admin/users" method="post">
            <input class="btn" type="submit" value="{{translation.update}}">
        </form>
    </td>
    <td>
        <form action="admin/newUser" method="get">
            <input class="btn" type="submit" value="{{translation.add}}">
        </form>
    </td>
</table>
</div>
</body>
</html>
