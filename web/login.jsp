<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Authorization"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
    <form id="slick-login" action="login" method="post">
        <input type="text" name="login" class="placeholder" placeholder="Login" required>
        <input type="password" name="password" class="placeholder" placeholder="password" required>
        <input type="submit" value="Log in"><br>
    </form>
</table>
</body>
</html>