<%--
  Created by IntelliJ IDEA.
  User: 倾城一世
  Date: 2018/10/18
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.setAttribute("userName","李四");
    request.setAttribute("userName","李四");
    request.getRequestDispatcher("testAttribute.jsp").forward(request,response);
%>
<html>
<head>
    <title>测试</title>
</head>
<body>
    <form action="/testAttribute" method="post">
        <div>
            <label>姓名：</label>
            <input type="text" name="userName" value=""/>
        </div>
        <div>
            <label>密码：</label>
            <input type="password" name="password" value=""/>
        </div>
        <div>
            <input type="submit" value="提交" />
        </div>
    </form>
    <a href="testAttribute.jsp?a=123">testAttribute</a>
</body>
</html>
