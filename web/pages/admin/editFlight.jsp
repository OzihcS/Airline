<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../jspf/head.jspf" %>
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