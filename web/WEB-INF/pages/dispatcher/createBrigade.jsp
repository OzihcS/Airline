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
            <option selected disabled>Pilot</option>
            <c:forEach items="${pilots}" var="pilot">
                <option value="${pilot.id}">${pilot.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="radioman">
            <option selected disabled>Radioman</option>
            <c:forEach items="${radiomen}" var="radioman">
                <option value="${radioman.id}">${radioman.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="navigator">
            <option selected disabled>Navigator</option>
            <c:forEach items="${navigators}" var="navigator">
                <option value="${navigator.id}">${navigator.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="firstStewardess">
            <option selected disabled>Stewardess</option>
            <c:forEach items="${stewardesses}" var="stewardess">
                <option value="${stewardess.id}">${stewardess.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="secondStewardess">
            <option selected disabled>Stewardess</option>
            <c:forEach items="${stewardesses}" var="stewardess">
                <option value="${stewardess.id}">${stewardess.lastName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="thirdStewardess">
            <option selected disabled>Stewardess</option>
            <c:forEach items="${stewardesses}" var="stewardess">
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
