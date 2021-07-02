<%--
  Created by IntelliJ IDEA.
  User: EUNSUK
  Date: 2021-07-02
  Time: 오후 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" import="java.util.*" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h4><c:out value="${exception.getMessage()}"></c:out></h4>

<u1>
    <c:forEach items="${exception.getStackTrace() }" var="stack">
        <li>
            <c:out value="${stack}"></c:out>
        </li>
    </c:forEach>
</u1>
</body>
</html>
