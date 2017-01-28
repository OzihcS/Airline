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
        <th>Action</th>
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
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${flights}" var="flight">
        <tr>
            <td>${flight.id}</td>
            <td>${flight.departureLocation} / ${flight.arriveLocation}</td>
            <td>${flight.departureDate} / ${flight.arriveDate}</td>
            <td>
                <c:if test="${f:isFinished(flight)}">
                    ${flight.status}
                </c:if>
                <c:if test="${!f:isFinished(flight) && flight != null}">
                    <details>
                        <summary>${flight.status} </summary>
                        <form action="dispatcher/changeStatus" method="post">
                            <input type="hidden" name="id" value="${flight.id}">
                            <select id="status" name="status" onchange="form.submit()">
                                <option selected disabled>{{translation.status}}</option>
                                <option value="IN_PROGRESS">In progress</option>
                                <option value="FINISHED">Finished</option>
                            </select><br>
                        </form>
                    </details>
                </c:if>
            </td>
            <td>
                <c:if test="${flight.brigade == null && flight != null}">
                    <form action="dispatcher/newBrigade" method="get">
                        <input type="text" name="id" value="${flight.id}" hidden="hidden">
                        <input class="btn" type="submit" value="{{translation.create}}">
                    </form>
                </c:if>
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
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td>
        <form action="dispatcher/home" method="post">
            <input class="btn" type="submit" value="{{translation.update}}">
        </form>
    </td>
</table>
</div>
</body>
</html>