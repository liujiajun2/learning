<%--
  Created by IntelliJ IDEA.
  User: 倾城一世
  Date: 2018/10/18
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    String userName = (String) request.getAttribute("userName");
    String userName1 = (String) session.getAttribute("userName");
    String a = request.getParameter("a");
    System.out.println(userName);
    System.out.println(userName1);
    System.out.println(a);

%>
<body>
 <form action="/testAttribute" method="post">
    <div>
        <label>姓名：</label>
        <input type="text" name="userName" value=<%= request.getAttribute("userName")%>/>
    </div>
    <div>
        <label>密码：</label>
        <input type="password" name="password" value=<%= request.getAttribute("password")%>%/>
    </div>
</form>
</body>
</html>
