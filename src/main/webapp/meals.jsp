<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meals</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>

<h2>Meals</h2>
<h3><a href="meals_add.jsp">Add Meal</a></h3>


<table>
    <thead>
    <tr class="userMealsHeader">
        <th class="userMealsHeaderRow">Date</th>
        <th class="userMealsHeaderRow">Description</th>
        <th class="userMealsHeaderRow">Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <jsp:useBean id="mealsList" scope="request" type="java.util.List"/>
    <c:forEach var="meal" items="${mealsList}">
        <tr style="${meal.isExcess() ? 'color:red' : 'color:green'}">
            <td><c:out value="${meal.getDateTimeFormatted()}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>

            <form action="${pageContext.request.contextPath}/meals_update"></form>
            <td><a href='meals_update.jsp?userId=<c:out value="${meal.id}"/>'>Update</a></td>
            <td><a href="meals?action=delete&userId=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>

</body>

</html>