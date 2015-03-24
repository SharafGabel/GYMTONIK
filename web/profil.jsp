<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Profil"; %>
<%@ include file="header.jsp" %>
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
<%@ include file="footer.jsp" %>