<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="WEB-INF/jspf/header.jspf" %>
    <title>Salary Predictor</title>
</head>
<body>
<%@include file="WEB-INF/jspf/navigation.jspf" %>
<div class="predictor_form">
    <form method="POST" action="search">
        <table>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lastName"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="search" name="action"/></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>