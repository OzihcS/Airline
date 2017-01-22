<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<form action="newFlight" method="post">
    <input type="text" name="Name" class="placeholder" placeholder="Name" required>
    <input type="text" name="From" class="placeholder" placeholder="From" required>
    <input type="text" name="To" class="placeholder" placeholder="To" required>
    <input type="text" name="DepartureDate" class="placeholder" placeholder="Departure date" required>
    <input type="text" name="ArriveDate" class="placeholder" placeholder="Arrive date" required>
    <input type="submit" value="Add">
</form>
</body>
</html>
