<%--
  Created by IntelliJ IDEA.
  User: ilzira
  Date: 15.10.2020
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Settings</title>
</head>
<body>
<h1 style="color: ${cookie.get("color").value}">Hello!</h1>
<div><form action="/settings" method="post">
    <select size="3", name = "color">
        <option value="red">Red</option>
        <option value="green">Green</option>
        <option value="black">Black</option>
        <option value="blue">Blue</option>
    </select>
    <input type="submit" value="Save color">
</form>
</div>
</body>
</html>
