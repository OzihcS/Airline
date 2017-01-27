<%@include file="../../jspf/directive.jspf" %>
<html>
<%@ include file="../../jspf/header.jspf" %>
<form id="slick-login" action="admin/newUser" method="post">
    <input type="text" name="name" class="placeholder" placeholder="{{translation.userName}}" required>
    <input type="text" name="login" class="placeholder" placeholder="{{translation.userLogin}}" required>
    <input type="password" name="password" class="placeholder" placeholder="{{translation.password}}" required>
    <input type="password" name="confirmPassword" class="placeholder" placeholder="{{translation.confirmPassword}}"
           required>
    <section>
        <select id="role" name="role">
            <option disabled>{{translation.role}}</option>
            <option value="ADMINISTRATOR">{{translation.admin}}</option>
            <option selected value="DISPATCHER">{{translation.dispatcher}}</option>
        </select>
    </section>
    <input type="submit" value="{{translation.add}}">
</form>
</div>
</body>
</html>
