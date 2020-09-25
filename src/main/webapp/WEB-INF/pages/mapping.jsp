<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lamy
  Date: 9/23/20
  Time: 2:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>End Point Mapping</title>
</head>
<body>

<ul>
    <c:forEach items="${urlMapping}" var="mapping">
        <li>
            <a href="${mapping.value}">${mapping.key}</a>
        </li>
    </c:forEach>
</ul>

</body>
</html>
