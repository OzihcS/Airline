<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../jspf/head.jspf" %>
<%@ include file="../../jspf/user.jspf" %>

<form action="admin/editUser" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <input type="text" name="name" value="${user.name}">
    <input type="text" name="login" value="${user.login}">
    <input type="password" name="password">
    <select name="role">
        <c:if test="${user.role == 'ADMINISTRATOR'}">
            <option value="ADMINISTRATOR" selected="selected">{{translation.admin}}</option>
            <option value="DISPATCHER">{{translation.dispatcher}}</option>
        </c:if>
        <c:if test="${user.role != 'ADMINISTRATOR'}">
            <option value="ADMINISTRATOR">{{translation.admin}}</option>
            <option value="DISPATCHER" selected="selected">{{translation.dispatcher}}</option>
        </c:if>
    </select>
    <input type="submit" value="{{translation.edit}}">
</form>
</head>
<body>
</body>
</html>
