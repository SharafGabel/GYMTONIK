<%--
  Created by IntelliJ IDEA.
  User: axel
  Date: 20/02/15
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form id="form" name="form" method="post" action="LoginServlet">
        <h1>Login</h1>
        <p>Please enter the following information</p>

        <label>First Name</label>
        <input type="text" name="username" id="username" />

        <label>Password</label>
        <input type="password" name="password" id="password" />

        <button type="submit">Login</button>
    </form>
</body>
</html>
