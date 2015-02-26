<%@ page import="model.SessionUser" %>
<%@ page import="java.util.List" %>
<%@ page import="service.GetList" %>
<%@ page import="model.User" %>
<%@ page import="model.Exercise" %>
<%@ page import="service.ExerciseService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Exercice"; %>
<%@ include file="header.jsp" %>
<div class="page-container" id="exerciseDiv">
    <form id="formEx" name="formEx" method="post" action="ExerciceServlet">
        <h1>Créer un Exercice</h1>

        <!-- Champ caché afin d'indiquer au servlet l'action qu'on souhaite réaliser,
        ici ajouter un nouvel exercice -->
        <input type="hidden" name="action" value="add" />

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
                <th>Action</th>
            </tr>
        </thead>

        <tbody>
            <%
                User user = (User)session.getAttribute("User");
                for(SessionUser u:sessionUserList) {
                    List<Exercise> trainingList = ExerciseService.getExercises(user);
                    for (Exercise t : trainingList) {
                        if (t.getUser().getId() == user.getId()) {
                            out.println("<tr>");
                            out.println("\t<td>" + t.getName() + "</td>");
                            out.println("\t<td>" + t.getLength() + "</td>");
                            out.println("\t<td>" + t.getExplanation() + "</td>");
                            out.println("\t<td>");
                            out.println("<form method=\"post\" action=\"ExerciceServlet\">");
                            out.println("<input type=\"hidden\" name=\"action\" value=\"delete\" />");
                            out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
                            out.println("<button type=\"submit\">Supprimer</button>");
                            out.println("</form>");

                            out.println("<form method=\"post\" action=\"update-exercise.jsp\">");
                            out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
                            out.println("<input type=\"hidden\" name=\"idS\" value=\"" + u.getId() + "\" />");
                            out.println("<button type=\"submit\">Modifier</button>");
                            out.println("</form>");
                            out.println("</td>");
                            out.println("</tr>");
                        }
                    }
                }%>
        </tbody>
    </table>
</div>
<%@ include file="footer.jsp" %>

