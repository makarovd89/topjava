<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit meal</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='css/form.css'/>">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>${param.action == 'create' ? 'Create' : 'Edit'}</h2>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    <table>
        <tr>
            <td align="right"><label class="lab" for="dateTime">Time</label></td>
            <td>
                <input class="in" type="datetime-local" name="dateTime" id="dateTime" class="in"
                       value='${meal.dateTime}'/>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="lab" for="description">Description</label></td>
            <td>
                <input class="in" type="text" name="description" id="description" class="in"
                       value='${meal.description}'/>
            </td>
        </tr>
        <tr>
            <td align="right"><label class="lab" for="calories">Calories</label></td>
            <td><input class="in" type="number" name="calories" id="calories" class="in"
                       value='${meal.calories}'/></td>
        </tr>
        <tr>
            <td></td>
            <td align=right>
                <button type="reset">Reset</button>
                <button type="submit">Save</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
