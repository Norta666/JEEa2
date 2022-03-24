<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="WEB-INF/jspf/header.jspf" %>
    <title>Salary Predictor</title>
</head>
<body style="background-color: grey">
<%@include file="WEB-INF/jspf/navigation.jspf" %>

<%
    if (request.getAttribute("errors") != null) {%>
<div class="predictor_form">
    <p><%=request.getAttribute("errors") %>
    </p>
</div>

<% } %>
<div class="predictor_form">
    <h1>Next Year Salary Predictor</h1>
    <form method="POST" action="create">
        <table>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="firstName"></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lastName"></td>
            </tr>
            <tr>
                <td>Current Salary:</td>
                <td><input type="text" name="currentSalary"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="create" name="action"/></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>