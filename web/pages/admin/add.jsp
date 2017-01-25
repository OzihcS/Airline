<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../jspf/head.jspf" %>
<%@ include file="../../jspf/header.jspf" %>
<form id="slick-login" action="admin/add" method="post">
    <input type="text" name="Name" class="placeholder" placeholder="{{translation.flightName}}" required>
    <input type="text" name="From" class="placeholder" placeholder="{{translation.from}}" required>
    <input type="text" name="To" class="placeholder" placeholder="{{translation.to}}" required>
    <input type="text" name="DepartureDate" class="placeholder" placeholder="{{translation.departureDate}}"
           required>
    <input type="text" name="ArriveDate" class="placeholder" placeholder="{{translation.arriveDate}}" required>
    <input type="submit" value="{{translation.addFlight}}">
</form>
</div>
</body>
</html>
