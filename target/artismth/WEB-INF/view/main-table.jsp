<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>


    <div id="container">
        <div id="content">
            <input type="button" value="Add Customer"
                   onclick="window.location.href='add-citizen';
                   return false;"
                   class="add-button"
            />
            <table>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Last name</th>
                    <th>Passport</th>
                    <th>Citizenship</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="citizenss" items="${citizens}" >

                    <c:url var="updateCitizen" value="/updateCitizen">
                        <c:param name="citizenId" value="${citizenss.id}"/>
                    </c:url>

                    <c:url var="deleteCitizen" value="/deleteCitizen">
                        <c:param name="citizenId" value="${citizenss.id}"/>
                    </c:url>
                    <tr>
                        <td>${citizenss.id}</td>
                        <td>${citizenss.name}</td>
                        <td>${citizenss.surname}</td>
                        <td>${citizenss.passport}</td>
                        <td>${citizenss.citizenship}</td>
                        <td><a href="${updateCitizen}">Update</a> | <a href="${deleteCitizen}">Delete</a></td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>

</body>
</html>
