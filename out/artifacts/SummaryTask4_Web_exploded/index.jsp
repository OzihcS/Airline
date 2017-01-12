<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <link rel="stylesheet" type="text/css" href="style/style.css"/>
</head>
<body>
<form id="slick-login" action="login" method="post">
    <input type="text" name="login" class="placeholder" placeholder="Login" required>
    <input type="password" name="password" class="placeholder" placeholder="password" required>
    <input type="submit" value="Log in"><br>
</form>
<p id="errorMsg">${errorMsg}</p>
</body>
</html>