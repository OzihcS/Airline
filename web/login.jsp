<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%@ include file="jspf/head.jspf" %>
    <form id="slick-login" action="log/login" method="post">
        <input type="text" name="login" class="placeholder" placeholder="{{translation.login}}" required>
        <input type="password" name="password" class="placeholder" placeholder="{{translation.password}}" required>
        <input type="submit" value="{{translation.login}}"><br>
    </form>
</div>
</body>
</html>
