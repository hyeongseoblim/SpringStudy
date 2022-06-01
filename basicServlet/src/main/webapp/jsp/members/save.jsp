<%@ page import="com.hello.basicservlet.domain.member.MemberRepository" %>
<%@ page import="com.hello.basicservlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: macbookpro13
  Date: 2022/01/18
  Time: 12:16 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);

    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <li>id <%=member.getId()%></li>
    <li>username <%=member.getUsername()%></li>
    <li>age <%=member.getAge()%></li>
</ul>
<a href="/index.html">main</a>
</body>
</html>
