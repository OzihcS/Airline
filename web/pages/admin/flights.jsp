<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../jspf/header.jspf" %>
<table class="sortable">
    <form action="admin/pickUp" method="post">
        <td><input class="sinput" type="text" name="id" class="placeholder" placeholder="№">
        </td>
        <section>
            <ul class="style-5">
                <td><input class="input" type="text" name="from" placeholder="{{translation.from}} ">
                    <input class="input" type="text" name="to" placeholder="{{translation.to}}"></td>
                <td><input class="input" type="text" name="departure" placeholder="{{translation.departureDate}}">
                    <input class="input" type="text" name="arrive" placeholder="{{translation.arriveDate}}">
                </td>
            </ul>
        </section>
        <td></td>
        <td></td>
        <td><input class="btn" type="submit" value="{{translation.search}}"></td>
    </form>
    <thead>
    <tr>
        <th>{{translation.flight}}</th>
        <th>{{translation.from}} / {{translation.to}}</th>
        <th>{{translation.departureDate}} / {{translation.arriveDate}}</th>
        <th>{{translation.status}}</th>
        <th>{{translation.brigade}}</th>
        <th>Action</th>
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
                    {{translation.pilot}}: ${flight.brigade.pilot.firstName} ${flight.brigade.pilot.lastName}<br>
                    {{translation.radioman}}: ${flight.brigade.radioman.firstName} ${flight.brigade.radioman.lastName}
                    <br>
                    {{translation.navigator}}: ${flight.brigade.navigator.firstName} ${flight.brigade.navigator.lastName}
                    <br>
                    <c:forEach items="${flight.brigade.stewardess}" var="stewardess">
                        {{translation.stewardess}}: ${stewardess.firstName} ${stewardess.lastName}<br>
                    </c:forEach>
                </c:if>
                <c:if test="${flight.brigade == null}">
                    {{translation.noBrigade}}
                </c:if>
            </td>
            <td>
                <form action="admin/editFlight" method="get">
                    <input type="hidden" name="id" value="${flight.id}">
                    <input class="btn" type="submit" value="{{translation.edit}}">
                </form>
                <form action="admin/deleteFlight" method="get">
                    <input name="id" value="${flight.id}" type="hidden">
                    <input class="btn" type="submit" value="{{translation.delete}}">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>

    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td>
        <form action="admin/flights" method="get">
            <input class="btn" type="submit" value="{{translation.update}}">
        </form>
    </td>
    <td>
        <form action="admin/add" method="get">
            <input class="btn" type="submit" value="{{translation.add}}">
        </form>
    </td>
</table>
</body>
</html>
