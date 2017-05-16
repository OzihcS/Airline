<%@ page pageEncoding="UTF-8" %>
<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@ include file="../../jspf/header.jspf" %>
<table class="sortable">
    <thead>
    <tr>
        <th>№</th>
        <th>{{translation.userName}}</th>
        <th>{{translation.userLogin}}</th>
        <th>{{translation.role}}</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <c:if test="${f:isAdmin(user)}">
                <td>{{translation.admin}}</td>
            </c:if>
            <c:if test="${f:isDispatcher(user)}">
                <td>{{translation.dispatcher}}</td>
            </c:if>
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
