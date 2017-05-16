<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../jspf/directive.jspf" %>
<html>
<%@ include file="../jspf/head.jspf" %>
<body>
<%@ include file="../jspf/header.jspf" %>
<div ng-app="myApp" ng-controller="myController">
    <label id="error-panel">{{translation.permission}}</label>
</div>
</body>
</html>
