<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Twitter Home Page</title>
</head>
<body>
<div>
    <form action="/follow" name="request" method="post">
        Name: <input type="text" name="fromUserID"/> <br/>
        Money: <input type="text" name="toUserID"/> <br/>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>