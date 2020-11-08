<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>
<div id="container">
    <h3>Save Customer</h3>
    <form:form method="post" action="save-citizen" modelAttribute="citizen">
        <form:hidden path="id" />
        <table>
            <tr>
                <td><label>First name</label></td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td><label>Second name</label></td>
                <td><form:input path="surname" /></td>
            </tr>
            <tr>
                <td><label>Passport</label></td>
                <td><form:input path="passport" /></td>
            </tr>
            <tr>
                <td><label>Citizenship</label></td>
                <td><form:input path="citizenship" /></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="save" class="save" /></td>
            </tr>
        </table>
    </form:form>
    <div style="clear: both;"></div>
    <p><a href="${pageContext.request.contextPath}/main-table">Back to List</a></p>
</div>
</body>
</html>
