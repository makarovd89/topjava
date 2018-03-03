<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit meal</title>
    <style>
        .in{font-family:Arial, sans-serif;font-size:14px;width:100%;vertical-align:top}
        .lab{font-family:Arial, sans-serif;font-size:14px;vertical-align: top;}
        td {vertical-align: center; padding:2px 1px}
    </style>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create' : 'Edit'}</h2>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <table>
            <input type="hidden" name="id" value="${meal.id}">
            <tr>
                <td align="right"><label class="lab">Time</label></td>
                <td><input class="in" type="datetime-local" name="dateTime" cssClass="in" value='${meal.dateTime}'/></td>
            </tr>
            <tr>
                <td align="right"><label class="lab">Description</label></td>
                <td><input class="in" type="text" name="description" cssClass="in" rows="3"  value='${meal.description}'/></td>
            </tr>
            <tr>
                <td align="right"><label class="lab">Calories</label></td>
                <td><input class="in" type="number" name="calories" cssClass="in"  value='${meal.calories}'/></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="submit">Save</button></td>
            </tr>
        </table>
    </form>
</body>
</html>
