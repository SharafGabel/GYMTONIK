<%@ page import="model.SessionUser" %>
<%@ page import="java.util.List" %>
<%@ page import="service.GetList" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="index.jsp" %>
<html>
<head>
    <title>Exercice</title>
</head>
<body>
<div class="page-container" id="exerciseDiv">
    <form id="formEx" name="formEx" method="post" action="ExerciceServlet">
        <h1>Créer un Exercice</h1>

        <p><label class="lab">Nom de l'exercice</label></p>
        <input type="text" name="nomEx" id="nomEx" />

        <p><label class="lab">Durée (en min)</label></p>
        <input type="number" min="1" max="120" name="duree" id="duree" />

        <p><label class="lab">Description de l'exercice</label></p>
        <input type="text" name="descriptionEx" id="descriptionEx" />

        <p><label class="lab">Choix de la séance</label></p>
        <select name="sessionUser" class="selectpicker">
            <%
                List<SessionUser> sessionUserList = GetList.getSessionList((User) session.getAttribute("User"));
                for(SessionUser a:sessionUserList)
                {
                    a.getName();
            %>
            <option value="<%=a.getId()%>"> <%=a.getName()%></option>
            <%}%>
        </select>

        <p><button type="submit">Enregistrer l'exercice</button></p>
    </form>

</div>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h1>Jasny Bootstrap starter template</h1>

            <p class="lead">Use this fiddle to demonstrate an issue with Jasny Bootstrap or to show an example using Jasny Bootstrap.</p>
        </div>
    </div>
</div>

</body>
</html>
