<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='css/table.css'/>">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<h4><a href="?action=create"><button>New Meal</button></a></h4>
<table class="tg">
    <tr>
        <th>Time</th>
        <th width="250">Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
    <tr class='${meal.exceed ? 'red':'green'}'>
        <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}" var="dateTime"/>
        <td>${dateTime}</td>
        <td style="word-break:break-all">${meal.description}</td>
        <td>${meal.calories}</td>
        <td align="center">
            <a href="<c:url value='/meals?action=update&id=${meal.id}'/>">
                <button>Update</button>
            </a>
        </td>
        <td align="center">
            <a href="<c:url value='/meals?action=delete&id=${meal.id}'/>">
                <button>Delete</button>
            </a>
        </td>
    </tr>
    </c:forEach>
</body>
</html>
