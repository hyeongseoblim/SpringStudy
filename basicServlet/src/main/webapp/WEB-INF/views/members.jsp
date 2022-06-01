<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.hello.basicservlet.domain.member.MemberRepository" %>
<%@ page import="com.hello.basicservlet.domain.member.Member" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: macbookpro13
  Date: 2022/01/18
  Time: 12:24 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
<c:forEach var="item" items="${members}">
    <li>${item.id}</li>
    <li>${item.username}</li>
    <li>${item.age}</li>
</c:forEach>
    </tbody>
</table>

</body>
</html>
