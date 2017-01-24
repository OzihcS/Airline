<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../jspf/head.jspf" %>
<%@ include file="../../jspf/user.jspf" %>

<form action="admin/editFlight" method="post">
    <input type="hidden" name="id" value="${flight.id}">
    <input type="text" name="name" value="${flight.name}">
    <input type="text" name="departureLocation" value="${flight.departureLocation}">
    <input type="text" name="arriveLocation" value="${flight.arriveLocation}">
    <input type="text" name="departureDate" value="${flight.departureDate}">
    <input type="text" name="arriveDate" value="${flight.arriveDate}">
    <input type="submit" value="{{translation.edit}}">
</form>
</head>
<body>
</body>
</html>
