<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/header.jspf" %>
<form id="slick-login" action="admin/add" method="post">
    <input type="text" name="name" class="placeholder" placeholder="{{translation.flightName}}" required>
    <input type="text" name="departureLocation" class="placeholder" placeholder="{{translation.from}}" required>
    <input type="text" name="arriveLocation" class="placeholder" placeholder="{{translation.to}}" required>
    <input type="text" name="departureDate" class="placeholder" placeholder="{{translation.departureDate}}"
           required>
    <input type="text" name="arriveDate" class="placeholder" placeholder="{{translation.arriveDate}}" required>
    <input type="submit" value="{{translation.addFlight}}">
</form>
</div>
</body>
</html>
