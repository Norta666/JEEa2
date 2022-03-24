<%@ page import="com.nbcc.salarypredictor.model.Salary" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
    List<Salary> salaryList = null;
    ValidationUtil util = new ValidationUtil();

    if (request.getAttribute("salaryList") != null) {
        salaryList = (List<Salary>) request.getAttribute("salaryList");
%>
<div class="results">
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</td>
            <th>Current Salary</th>
            <th>Next Year Salary</th>
        </tr>
        <%
            for (Salary salary : salaryList) {
        %>
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
        <%
            }
        %>
    </table>
</div>
<% } else { %>
<div class="salary_list"><h1>No results found</h1></div>
<% } %>
</body>
</html>