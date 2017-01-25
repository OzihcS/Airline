<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="jspf/directive.jspf" %>
<html>
<%@ include file="jspf/head.jspf" %>
<body>
<div ng-app="myApp" ng-controller="myController">
    <section>
        <select id="lang" ng-change="translate()" ng-model="selectedLanguage">
            <option value="en">English</option>
            <option value="ru">Русский</option>
        </select>
    </section>

    <form id="slick-login" action="log/login" method="post">
        <input type="text" name="login" class="placeholder" placeholder="{{translation.login}}" required>
        <input type="password" name="password" class="placeholder" placeholder="{{translation.password}}" required>
        <input type="submit" value="{{translation.login}}"><br>
    </form>
</div>
</body>
</html>
