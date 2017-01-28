<%@ page pageEncoding="UTF-8" %>
<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@ include file="../../jspf/header.jspf" %>
<table>
    <thead>
    <tr>
        <th>{{translation.flight}}</th>
        <th>{{translation.from}} / {{translation.to}}</th>
        <th>{{translation.departureDate}} / {{translation.arriveDate}}</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <form action="admin/pickUp" method="post">
            <td><input class="sinput" type="text" name="id" class="placeholder" placeholder="№"></td>
            <section>
                <div class="style-5">
                    <td><input class="input" type="text" name="departureLocation" placeholder="{{translation.from}} ">
                        <input class="input" type="text" name="arriveLocation" placeholder="{{translation.to}}"></td>
                    <td><input class="input" type="text" name="departureDate"
                               placeholder="{{translation.departureDate}}">
                        <input class="input" type="text" name="arriveDate" placeholder="{{translation.arriveDate}}">
                    </td>
                </div>
            </section>
            <td><input class="btn" type="submit" value="{{translation.search}}"></td>
        </form>
    </tr>
    </tbody>
</table>
<table class="sortable">
    <thead>
    <tr>
        <th>{{translation.flight}}</th>
        <th>{{translation.from}} / {{translation.to}}</th>
        <th>{{translation.departureDate}} / {{translation.arriveDate}}</th>
        <th>{{translation.status}}</th>
        <th>{{translation.brigade}}</th>
        <th></th>
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
