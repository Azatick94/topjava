<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Meal</title>
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>

<h2>Add meal</h2>

<%--action-servlet name where form will be processed--%>
<form action="meals_update" method="post" accept-charset="UTF-8" >
<input type="hidden" name="userId" value='${param["userId"]}'/>

<table style="with: 100%">
    <tr>
        <th>DateTime:</th>
        <td><label><input type="datetime-local" name="date" placeholder="date"></label></td>
    </tr>
    <tr>
        <th>Description:</th>
        <td><label><input type="text" name="description" placeholder="Завтрак/Обед/Ужин"></label></td>
    </tr>
    <tr>
        <th>Calories:</th>
        <td><label><input type="number" name="calories" placeholder="кДж"></label></td>
    </tr>
</table>

<br>
<input type="submit" value="Save" style="height: 30px; width: 100px"/><br><br>
</form>

<input type="submit" value="Cancel" onclick="window.location='meals'" style="height: 30px; width: 100px"/>

</body>
</html>
