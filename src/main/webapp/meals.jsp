<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;overflow:hidden;word-break:normal;
            border: solid white;
        }
        .tg th{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;overflow:hidden;word-break:normal;
            border: solid white;
            color:#333;background-color:#f0f0f0;font-weight:bold;}
        .tg tr:nth-child(even){background-color: white;}
        .tg tr:nth-child(odd) {background-color: #f0f0f0;}
        .green {color: green;}
        .red {color: red;}
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<h4><a href="?action=create">New Meal</a></h4>
<table class="tg">
    <tr>
        <th width="200">Time</th>
        <th width="250">Description</th>
        <th width="100">Calories</th>
        <th></th>
        <th></th>
    </tr>
<c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
    <tr class='${meal.exceed ? 'red':'green'}'>
        <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}" var="dateTime"/>
        <td>${dateTime}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td><a href="<c:url value='/meals?action=update&id=${meal.id}'/>">Update</a></td>
        <td><a href="<c:url value='/meals?action=delete&id=${meal.id}'/>">Delete</a></td>
    </tr>
</c:forEach>
</body>
</html>
