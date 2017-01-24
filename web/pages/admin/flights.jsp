<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../jspf/head.jspf" %>
<%@ include file="../../jspf/user.jspf" %>
<form action="admin/flights" method="post">
    <input type="text" name="id" class="placeholder" placeholder="{{translation.flightNum}}" required>
    <input type="submit" value="{{translation.search}}">
</form>

<table border="1" class="sortable">
    <thead>
    <tr>
        <td>{{translation.flight}}</td>
        <td>{{translation.from}} / {{translation.to}}</td>
        <td>{{translation.departureDate}} / {{translation.arriveDate}}</td>
        <td>{{translation.status}}</td>
        <td>{{translation.brigade}}</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td>${flight.id}</td>
            <td>${flight.departureLocation} / ${flight.arriveLocation}</td>
            <td>${flight.departureDate} / ${flight.arriveDate}</td>
            <td>${flight.status}</td>
            <td>
                <c:if test="${flight.brigade != null}">
                    <details>
                        <summary>Show brigade</summary>
                        {{translation.pilot}}: ${flight.brigade.pilot.firstName} ${flight.brigade.pilot.lastName}<br>
                        {{translation.radioman}}: ${flight.brigade.radioman.firstName} ${flight.brigade.radioman.lastName}<br>
                        {{translation.navigator}}: ${flight.brigade.navigator.firstName} ${flight.brigade.navigator.lastName}<br>
                        <c:forEach items="${flight.brigade.stewardess}" var="stewardess">
                            {{translation.stewardess}}: ${stewardess.firstName} ${stewardess.lastName}<br>
                        </c:forEach>
                    </details>
                </c:if>
            </td>
            <td>
                <form action="admin/deleteFlight" method="get">
                    <input name="id" value="${flight.id}" type="hidden">
                    <input type="submit" value="{{translation.delete}}">
                </form>
                <form action="admin/editFlight" method="get">
                    <input type="hidden" name="id" value="${flight.id}">
                    <input type="submit" value="{{translation.edit}}">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="admin/flights" method="post">
    <input type="submit" value="{{translation.update}}">
</form>
<form action="admin/add" method="get">
    <input type="submit" value="{{translation.add}}">
</form>
<form action="admin/pickUp" method="post">
    <input type="text" name="from">
    <input type="text" name="to">
    <input type="text" name="departure">
    <input type="submit" value="{{translation.search}}">
</form>

</body>
</html>
