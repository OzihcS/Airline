<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%@ include file="../../jspf/head.jspf" %>

<form action="dispatcher/newBrigade" method="post">

    <input type="hidden" name="id" value="${flightId}">
    <label>Pilot</label>
    <select name="pilot">
        <c:forEach items="${pilots}" var="pilot">
            <option selected value="${pilot.id}">${pilot.lastName}</option>
        </c:forEach>
    </select>

    <label>Radioman</label>
    <select name="radioman">
        <c:forEach items="${radiomen}" var="radioman">
            <option selected value="${radioman.id}">${radioman.lastName}</option>
        </c:forEach>
    </select>
    <label>Navigator</label>
    <select name="navigator">
        <c:forEach items="${navigators}" var="navigator">
            <option selected value="${navigator.id}">${navigator.lastName}</option>
        </c:forEach>
    </select>
    <label>Stewardess</label>
    <select name="firstStewardess">
        <c:forEach items="${stewardesses}" var="stewardess">
            <option selected value="${stewardess.id}">${stewardess.lastName}</option>
        </c:forEach>
    </select>
    <label>Stewardess</label>
    <select name="secondStewardess">
        <c:forEach items="${stewardesses}" var="stewardess">
            <option selected value="${stewardess.id}">${stewardess.lastName}</option>
        </c:forEach>
    </select><label>Stewardess</label>
    <select name="thirdStewardess">
        <c:forEach items="${stewardesses}" var="stewardess">
            <option selected value="${stewardess.id}">${stewardess.lastName}</option>
        </c:forEach>
    </select>

    <input type="submit" value="{{translation.add}}">
</form>

<form action="dispatcher/newRequest" method="get">
    <input type="submit" value="{{translation.sendRequest}}">
</form>

<%--<form action="dispatcher/sendRequest" method="post">--%>
<%--&lt;%&ndash;<input type="">&ndash;%&gt;--%>
<%--<input type="text" name="message" required>--%>
<%--<input type="submit" value="Send">--%>
<%--</form>--%>
</div>
</body>
</html>
