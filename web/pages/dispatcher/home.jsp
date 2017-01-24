<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<%@ include file="../../jspf/user.jspf" %>
<form action="dispatcher/home" method="post">
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
            <td>
                <c:if test="${flight.status == 'FINISHED'}">
                    ${flight.status}
                </c:if>
                <c:if test="${flight.status != 'FINISHED'}">
                    <details>
                        <summary>${flight.status} </summary>
                        <form action="dispatcher/changeStatus" method="post">
                            <input type="hidden" name="id" value="${flight.id}">
                            <select name="status">
                                <option value="IN_PROGRESS">In progress</option>
                                <option selected value="FINISHED">Finished</option>
                            </select>
                            <input type="submit" value="Change">
                        </form>
                    </details>
                </c:if>
            </td>
            <td>
                <c:if test="${flight.brigade == null}">
                    <form action="dispatcher/newBrigade" method="get">
                        <input type="text" name="id" value="${flight.id}" hidden="hidden">
                        <input type="submit" value="{{translation.create}}">
                    </form>
                </c:if>
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
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="dispatcher/home" method="post">
    <input type="submit" value="{{translation.update}}">
</form>
</div>
</body>
</html>