<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <form method="get" action="meals">
        <input type="hidden" name="action" value="filter">
        <table>
            <tr>
                <td align="right"><label for="startTime">From time:</label></td>
                <td><input type="time" name="startTime" id="startTime" class="in" value="${param.startTime}"/></td>
                <td align="right"><label class="lab" for="startDate">From date:</label></td>
                <td><input type="date" name="startDate" id="startDate" class="in" value="${param.startDate}"/></td>
            </tr>
            <tr>
                <td align="right"><label for="endTime">To time:</label></td>
                <td><input type="time" name="endTime" id="endTime" class="in" value="${param.endTime}"/></td>
                <td align="right"><label for="endDate">To date:</label></td>
                <td><input type="date" name="endDate" id="endDate" class="in" value="${param.endDate}"/></td>
            </tr>
            <tr>
                <td></td><td></td><td></td>
                <td align=right>
                    <button type="submit">Filter</button>
                </td>
            </tr>
        </table>
    </form>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>