<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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


<table class="userMeals">
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
        <c:if test="${meal.isExcess() == true}">
            <tr>
                <th><span style="color: red; "><c:out value="${meal.getDateTimeFormatted()}"/></span></th>
                <th><span style="color: red; "><c:out value="${meal.getDescription()}"/></span></th>
                <th><span style="color: red; "><c:out value="${meal.getCalories()}"/></span></th>

                <form action="${pageContext.request.contextPath}/meals_update"></form>
                <th>
                    <span style="color: red; "><a
                            href='meals_update.jsp?userId=<c:out value="${meal.getId()}"/>'>Update</a></span>
                </th>
                <th>
                    <span style="color: red; "><a href="meals?action=delete&userId=<c:out value="${meal.getId()}"/>">Delete</a></span>
                </th>
            </tr>
        </c:if>
        <c:if test="${meal.isExcess() == false}">
            <tr style="color: green">
                <th><span style="color: green; "><c:out value="${meal.getDateTimeFormatted()}"/></span></th>
                <th><span style="color: green; "><c:out value="${meal.getDescription()}"/></span></th>
                <th><span style="color: green; "><c:out value="${meal.getCalories()}"/></span></th>
                <th>
                    <span style="color: green; "><a href='meals_update.jsp?userId=<c:out value="${meal.getId()}"/>'>Update</a></span>
                </th>
                <th>
                    <span style="color: green; "><a href="meals?action=delete&userId=<c:out value="${meal.getId()}"/>">Delete</a></span>
                </th>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>

</table>


<%--  Table visualization with Simple JSP  --%>
<%--<table class="userMeals">--%>
<%--    <thead>--%>
<%--    <tr class="userMealsHeader">--%>
<%--        <th class="userMealsHeaderRow">Date</th>--%>
<%--        <th class="userMealsHeaderRow">Description</th>--%>
<%--        <th class="userMealsHeaderRow">Calories</th>--%>
<%--        <th></th>--%>
<%--        <th></th>--%>
<%--    </tr>--%>
<%--    </thead>--%>

<%--    <tbody>--%>
<%--    <%--%>
<%--        List<MealTo> meals = (List<MealTo>) request.getAttribute("mealsList");--%>
<%--        for (MealTo meal : meals) {--%>
<%--    %>--%>
<%--    <tr>--%>
<%--        <th><%= meal.getDateTime().toLocalDate() + " " + meal.getDateTime().toLocalTime() %>--%>
<%--        </th>--%>
<%--        <th><%= meal.getDescription() %>--%>
<%--        </th>--%>
<%--        <th><%= meal.getCalories() %>--%>
<%--        </th>--%>
<%--        <th>Update</th>--%>
<%--        <th>Delete</th>--%>
<%--    </tr>--%>

<%--    <% } %>--%>
<%--    </tbody>--%>
<%--</table>--%>

<%

%>

</body>

</html>