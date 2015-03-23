<%@ page import="model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: chrhiroz
  Date: 11/03/2015
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profil</title>
</head>
<body>
    <%
        User user = (User)session.getAttribute("User");
    %>
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <li>Nom d'utilisateur</li>
                <li>Adresse email</li>
                <li>Poid</li>
                <li>Taille</li>
            </div>
            <div class="col-md-3">
                <li><%=user.getUsername()%></li>
                <li><%=user.getEmail()%></li>
                <li><%=user.getWeight()%></li>
                <li><%=user.getHeight()%></li>
            </div>
            <div class="col-md-3">
                <li><button type="submit" class="btn btn-danger">Modifier</button></li>
                <li><button type="submit" class="btn btn-danger">Modifier</button></li>
                <li><button type="submit" class="btn btn-danger">Modifier</button></li>
                <li><button type="submit" class="btn btn-danger">Modifier</button></li>
            </div>
        </div>
    </div>
</body>
</html>
