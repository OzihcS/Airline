<%@include file="../jspf/directive.jspf" %>
<html>
<%@ include file="../jspf/head.jspf" %>
<body>
<div ng-app="myApp" ng-controller="myController">
    <section>
        <select id="lang" ng-change="translate()" ng-model="selectedLanguage">
            <c:forEach items="${applicationScope.locales}" var="locale" varStatus="status">
                <option value="${locale}">${locale}</option>
            </c:forEach>
        </select>
    </section>
    <form id="slick-login" action="log/login" method="post">
        <input type="text" name="login" class="placeholder" placeholder="{{translation.login}}" required>
        <input type="password" name="password" class="placeholder" placeholder="{{translation.password}}" required>
        <img src="http://localhost:8080/Airline/log/captcha"/>
        <input type="text" name="captcha">
        <input type="submit" value="{{translation.login}}">
    </form>
</div>
</body>
</html>
