<%@ page import="model.SessionUser" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="model.Exercise" %>
<%@ page import="service.ExerciseService" %>
<%@ page import="model.ATraining" %>
<%@ page import="service.SessionService" %>
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

        <p><label class="lab">Nombre de répétitions </label></p>
        <input type="number" min="5" max="500" name="nbRepet" id="nbRepet" />

        <p><label class="lab">Description de l'exercice</label></p>
        <input type="text" name="descriptionEx" id="descriptionEx" />

        <p><label class="lab">Niveau de l'exercice</label></p>
        <input type="number" min="1" max="3" name="niveau" id="niveau" />

        <p><label class="lab">Choix de la séance</label></p>
        <select name="sessionUser">
            <%
                List<SessionUser> sessionUserList = SessionService.getSessionList((User) session.getAttribute("User"));
                for(SessionUser a:sessionUserList)
                {
            %>
            <option name="optionName" value="<%=a.getIdS()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
            <%}%>
        </select>

        <p><button type="submit">Enregistrer l'exercice</button></p>
    </form>

    <hr/>

    <select name="sessionUser" id="sessionUser">
        <%
            List<SessionUser> sessionUserListId = SessionService.getSessionList((User) session.getAttribute("User"));
            for(SessionUser a:sessionUserListId)
            {
        %>
        <option name="optionName" value="<%=a.getIdS()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
        <%}%>
    </select>


    <table id="affSeance" class="table table-condensed">
        <thead>
            <tr>
                <th>Nom Exercice</th>
                <th>Durée</th>
                <th>Nombre de répétitions</th>
                <th>Description</th>
                <th>Niveau</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>
            <%
                User user = (User)session.getAttribute("User");
                for(SessionUser u:sessionUserList) {
                    List<Exercise> trainingList = ExerciseService.getExercises(user);
                    for (ATraining t : trainingList)
                    {
                        if (t.getUser().getId() == user.getId())
                        {
                            out.println("<tr>");
                            out.println("\t<td>" + t.getName() + "</td>");
                            out.println("\t<td>" + t.getDureeExo() + "</td>");
                            out.println("\t<td>" + t.getNbRepetition() + "</td>");
                            out.println("\t<td>" + t.getExplanation() + "</td>");
                            out.println("\t<td>" + t.getNiveau() + "</td>");
                            out.println("\t<td>");
                            out.println("<form method=\"post\" action=\"ExerciceServlet\">");
                            out.println("<input type=\"hidden\" name=\"action\" value=\"delete\" />");
                            out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
                            out.println("<button type=\"submit\">Supprimer</button>");
                            out.println("</form>");

                            out.println("<form method=\"post\" action=\"update-exercise.jsp\">");
                            out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
                            out.println("<input type=\"hidden\" name=\"idS\" value=\"" + u.getIdS() + "\" />");
                            out.println("<button type=\"submit\">Modifier</button>");
                            out.println("</form>");
                            out.println("</td>");
                            out.println("</tr>");
                        }
                        else
                        {
                            out.println("<tr>");
                            out.println("\t<td>" + t.getName() + "</td>");
                            out.println("\t<td>" + t.getDureeExo() + "</td>");
                            out.println("\t<td>" + t.getNbRepetition() + "</td>");
                            out.println("\t<td>" + t.getExplanation() + "</td>");
                            out.println("\t<td>" + t.getNiveau() + "</td>");
                            out.println("\t<td>");
                            out.println("<form method=\"post\" action=\"ExerciceServlet\">");
                            out.println("<input type=\"hidden\" name=\"action\" value=\"addToEx\" />");
                            out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
                            out.println("<input type=\"hidden\" name=\"idS\" value=\"" + u.getIdS() + "\" />");
                            out.println("<select name=\"sessionToAdd\">");
                for(SessionUser a:sessionUserList)
                {
            %>
            <option name="optionName" value="<%=a.getIdS()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
            <%}%>
            </select>
            <%
                            out.println("<button type=\"submit\">Ajouter à cette séance</button>");
                            out.println("</form>");
                            out.println("</td>");
                            out.println("</tr>");
                        }
                    }
                }%>
        </tbody>
    </table>
    <input type="button" value="Show Table" id="showTable"/>
    <div id="tablediv">

    <table cellpadding="0" id="exerciseTable">
        <tr>
            <th scope="col">Nom Exercice</th>
            <th scope="col">Durée</th>
            <th scope="col">Nombre de répétitions</th>
            <th scope="col">Description</th>
            <th scope="col">Niveau</th>
            <th scope="col">Action</th>
        </tr>
    </table>
    </div>

</div>
<%@ include file="footer.jsp" %>

