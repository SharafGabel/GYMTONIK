<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 15/02/2015
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<center>
    <div id="mystyle" class="myform">
        <form id="form" name="form" method="post" action="RegisterServlet">
            <h1>Registration</h1>
            <p>Please enter the following information</p>

            <label>First Name
                <span class="small">Enter your username</span>
            </label>
            <input type="text" name="username" id="username" />


            <label>Email
                <span class="small">Ex: dupont@gmail.com</span>
            </label>
            <input type="text" name="email" id="email" />


            <label>Password
                <span class="small">Password min. 6 chars</span>
            </label>
            <input type="text" name="password" id="password" />

            <button type="submit">Register</button>
            <div class="spacer"></div>

        </form>
    </div>
</center>
</body>
</html>
