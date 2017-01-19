<%@ include file="jspf/directive/page.jspf" %>
<%@ include file="jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Authorization"/>
<%@ include file="jspf/head.jspf" %>
<body>
<form id="slick-login" action="log/login" method="post">
    <input type="text" name="login" class="placeholder" placeholder="Login" required>
    <input type="password" name="password" class="placeholder" placeholder="password" required>
    <input type="submit" value="Log in"><br>
</form>
</body>
</html>