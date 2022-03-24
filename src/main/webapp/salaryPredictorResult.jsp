<%@ page import="com.nbcc.salarypredictor.model.Salary" %>
<%@ page import="com.nbcc.salarypredictor.util.ValidationUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="WEB-INF/jspf/header.jspf" %>
    <title>Salary Predictor</title>
</head>
<body>
<%@include file="WEB-INF/jspf/navigation.jspf" %>
<%
    Salary salary = null;
    ValidationUtil util = new ValidationUtil();

    if (request.getAttribute("createdSalary") != null) {
        salary = (Salary) request.getAttribute("createdSalary");
    }
%>

<div class="results">


    <table>
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Current Salary</th>
            <th>Next Year Salary</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><%= salary.getFirstName() %>
            </td>
            <td><%= salary.getLastName() %>
            </td>
            <td><%= util.currencyFormatter(salary.getCurrentSalary()) %>
            </td>
            <td><%= util.currencyFormatter(salary.getNextYearSalary()) %>
            </td>
        </tr>
        </tbody>

    </table>
</div>
</body>
</html>