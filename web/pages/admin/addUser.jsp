<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../jspf/user.jspf" %>
<%@ include file="../../jspf/head.jspf" %>
<form id="slick-login" action="admin/newUser" method="post">
    <input type="text" name="name" class="placeholder" placeholder="{{translation.userName}}" required>
    <input type="text" name="login" class="placeholder" placeholder="{{translation.userLogin}}" required>
    <input type="password" name="password" class="placeholder" placeholder="{{translation.password}}" required>
    <input type="password" name="confirmPassword" class="placeholder" placeholder="{{translation.confirmPassword}}"
           required>
    <select name="role">
        <option disabled>{{translation.role}}</option>
        <option value="ADMIN">{{translation.admin}}</option>
        <option selected value="DISPATCHER">{{translation.dispatcher}}</option>
    </select>
    <input type="submit" value="{{translation.add}}">
</form>
</div>
</body>
</html>
