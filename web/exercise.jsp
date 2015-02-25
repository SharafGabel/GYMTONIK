<%@ page import="model.SessionUser" %>
<%@ page import="java.util.List" %>
<%@ page import="service.GetList" %>
<%@ page import="model.User" %>
<%@ page import="model.Exercise" %>
<%@ page import="model.ATraining" %>
<%@ page import="service.ExerciseService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Exercice"; %>
<%@ include file="header.jsp" %>
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
        <select name="sessionUser">
            <%
                List<SessionUser> sessionUserList = GetList.getSessionList((User) session.getAttribute("User"));
                for(SessionUser a:sessionUserList)
                {
                    a.getName();
            %>
            <option name="optionName" value="<%=a.getId()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
            <%}%>
        </select>

        <p><button type="submit">Enregistrer l'exercice</button></p>
    </form>

    <hr/>

    <table id="affSeance" class="table table-condensed">
        <thead>
            <tr>
                <th>Nom Exercice</th>
                <th>Durée</th>
                <th>Description</th>
            </tr>
        </thead>

        <tbody>
            <%
                for(SessionUser u:sessionUserList)
                {
                    List<Exercise> trainingList = ExerciseService.getExercises(u);
                    for (Exercise t:trainingList) {
                        out.println("<tr>");
                            out.println("\t<td>" + t.getName() + "</td>" );
                            out.println("\t<td>" + t.getLength() + "</td>" );
                            out.println("\t<td>" + t.getExplanation() + "</td>" );
                        out.println("</tr>");
                    }
                }
            %>
        </tbody>
    </table>
</div>


</body>
</html>
