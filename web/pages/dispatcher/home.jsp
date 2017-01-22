<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
You are logged as :
<c:out value="${sessionScope.currentUser.getRole()}"/>
<c:out value="${sessionScope.currentUser.getLogin()}"/>
<form action="home" method="post">
    <input type="text" name="id" class="placeholder" placeholder="Flight number" required>
    <input type="submit" value="search">
</form>
<table border="1">
    <tr>
        <td>№</td>
        <td>lang.Name</td>
        <td>From</td>
        <td>To</td>
        <td>Status</td>
        <td>Departure date</td>
        <td>Arrive date</td>
        <td>Brigade</td>
    </tr>
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td>${flight.id}</td>
            <td>${flight.name}</td>
            <td>${flight.departureLocation}</td>
            <td>${flight.arriveLocation}</td>
            <td>${flight.status}</td>
            <td>${flight.departureDate}</td>
            <td>${flight.arriveDate}</td>
            <td>
                <details>
                    <summary>Show brigade</summary>
                    <p>Pilot : ${flight.brigade.pilot.lastName}</p>
                    <p>Radioman : ${flight.brigade.radioman.lastName}</p>
                    <p>Navigator : ${flight.brigade.navigator.lastName}</p>
                    <c:forEach items="${flight.brigade.stewardess}" var="stewardess">
                        <p>Stewardess: ${stewardess.lastName}</p>
                    </c:forEach>
                </details>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="home" method="post">
    <input type="submit" value="search">
</form>
</body>
</html>