<%@ include file="../../jspf/directive/page.jspf" %>
<%@ include file="../../jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Fligths"/>
<%@ include file="../../jspf/head.jspf" %>
You are logged as :
<c:out value="${sessionScope.currentUser.getRole()}"/>
<c:out value="${sessionScope.currentUser.getLogin()}"/>

<form id="find-flight-by-id" action="dispatcher/flights" method="post">
    <input type="text" name="id" class="placeholder" placeholder="Flight number" required>
    <input type="submit" value="search">
</form>

<form id="slick-login" action="dispatcher/flights" method="post">
    Amount: ${amount}
    <table border="1">
        <tr>
            <td>№</td>
            <td>Name</td>
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
            <%--<td>${flight.status}</td>--%>
            <td>${flight.departureDate}</td>
            <td>${flight.arriveDate}</td>
            <td>${flight.brigade.pilot.lastName}</td>
        </tr>
        </c:forEach>
        <input type="submit" value="Load">
</form>
</body>
</html>