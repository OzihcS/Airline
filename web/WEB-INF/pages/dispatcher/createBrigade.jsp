<%@ page pageEncoding="UTF-8" %>
<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@ include file="../../jspf/header.jspf" %>
<form action="dispatcher/newRequest" method="get">
    <input class="btn" type="submit" value="{{translation.sendRequest}}">
</form>
<form class="brigade" action="dispatcher/newBrigade" method="post">
    <input type="hidden" name="id" value="${flightId}">
    <div>
        <select name="pilot">
            <option selected disabled>{{translation.pilot}}</option>
            <c:forEach items="${sessionScope.pilots}" var="pilot">
                <option value="${pilot.id}">${pilot.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="radioman">
            <option selected disabled>{{translation.radioman}}</option>
            <c:forEach items="${sessionScope.radiomen}" var="radioman">
                <option value="${radioman.id}">${radioman.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="navigator">
            <option selected disabled>{{translation.navigator}}</option>
            <c:forEach items="${sessionScope.navigators}" var="navigator">
                <option value="${navigator.id}">${navigator.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="firstStewardess">
            <option selected disabled>{{translation.stewardess}}</option>
            <c:forEach items="${sessionScope.stewardesses}" var="stewardess">
                <option value="${stewardess.id}">${stewardess.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="secondStewardess">
            <option selected disabled>{{translation.stewardess}}</option>
            <c:forEach items="${sessionScope.stewardesses}" var="stewardess">
                <option value="${stewardess.id}">${stewardess.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="thirdStewardess">
            <option selected disabled>{{translation.stewardess}}</option>
            <c:forEach items="${sessionScope.stewardesses}" var="stewardess">
                <option value="${stewardess.id}">${stewardess.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <input class="btn" type="submit" value="{{translation.add}}">
    </div>
</form>
</div>
</body>
</html>
