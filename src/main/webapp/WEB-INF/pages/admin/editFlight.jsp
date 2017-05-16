<%@ page pageEncoding="UTF-8" %>
<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@ include file="../../jspf/header.jspf" %>
<form id="slick-login" action="admin/editFlight" method="post">
    <input type="hidden" name="id" value="${flight.id}">
    <input type="text" name="name" value="${flight.name}">
    <input type="text" name="departureLocation" value="${flight.departureLocation}"></fieldset>
    <input type="text" name="arriveLocation" value="${flight.arriveLocation}"></fieldset>
    <input type="text" name="departureDate" value="${flight.departureDate}"></fieldset>
    <input type="text" name="arriveDate" value="${flight.arriveDate}"></fieldset>
    <input type="submit" value="{{translation.edit}}">
</form>
</body>
</html>