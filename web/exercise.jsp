<%@ page import="java.util.List" %>
<%@ page import="service.ExerciseService" %>
<%@ page import="service.SessionService" %>

<%@ page import="service.HistoriqueService" %>
<%@ page import="model.*" %>
<%@ page import="service.MuscleService" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Exercice"; %>
<%@ include file="Core/header.jsp" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<div class="page-container" id="exerciseDiv">

    <form id="formEx" class="form-horizontal" name="formEx" method="post" action="ExerciceServlet">
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
            <option name="optionName" value="0">Aucune Séance</option>
            <%
                List<SessionUser> sessionUserList = SessionService.getSessionList((User) session.getAttribute("User"));
                for(SessionUser a:sessionUserList)
                {
            %>
            <option name="optionName" value="<%=a.getIdS()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
            <%}%>
        </select>
        <p>
        <%
            List<AMuscle> aMuscles = MuscleService.getAllMuscles();
            for(AMuscle a:aMuscles)
            {
        %>
            <%=a.getName()%><input class="checkbox" type="checkbox" name="inlineCheckboxMuscle" value="<%=a.getId()%>">
        <%}%>
        </p>

        <p><button type="submit">Enregistrer l'exercice</button></p>
    </form>

    </br>

    Affinez votre recherche :
    <select name="sessionUser" id="sessionUser">
        <option name="optionName" value="0">Aucune Séance</option>
        <%
            List<SessionUser> sessionUserListJquery = SessionService.getSessionList((User) session.getAttribute("User"));
            for(SessionUser a:sessionUserListJquery)
            {
        %>
        <option name="optionName" value="<%=a.getIdS()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
        <%}%>
    </select>
    <select name="exerciseLevel" id="exerciseLevel">
        <option name="optionName" value="1">Niveau 1</option>
        <option name="optionName" value="2">Niveau 2</option>
        <option name="optionName" value="3">Niveau 3</option>
    </select>


    <table id="affSeance" class="table table-condensed">
        <thead>
            <tr>
                <th>Nom Exercice</th>
                <th>Durée</th>
                <th>Nombre de répétitions</th>
                <th>Description</th>
                <th>Niveau</th>
                <th>Muscles Travaillés</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>
            <%
                    User user = (User)session.getAttribute("User");
                    List<ATraining> trainingList = ExerciseService.getExercises();
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
                            System.out.println(t.getBodyParts().size());
                            System.out.println(t.getBodyParts().toString());
                            out.println("<td>");
                            if(t.getBodyParts().size()!=0) {

                                int taille = 0;
                                for (AMuscle muscle : t.getBodyParts()) {
                                    taille++;
                                    if (t.getBodyParts().size() != taille)
                                        out.print(muscle.getName() + ",");
                                    else
                                        out.print(muscle.getName());
                                }


                            }
                            out.println("</td>");
                            out.println("\t<td>");
                            out.println("<form method=\"post\" action=\"ExerciceServlet\">");
                            out.println("<input type=\"hidden\" name=\"action\" value=\"delete\" />");
                            out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
                            out.println("<button type=\"submit\">Supprimer</button>");
                            out.println("</form>");

                            out.println("<form method=\"post\" action=\"updateExercise.jsp\">");
                            out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
                            out.println("<button type=\"submit\">Modifier</button>");
                            out.println("</form>");
                            List<SessionUser> seance = HistoriqueService.getSessionUserNotHaveThisExercise(t,user);
                            if(seance.size()!=0)
                            {
                                out.println("<form method=\"post\" action=\"ExerciceServlet\">");
                                out.println("<input type=\"hidden\" name=\"action\" value=\"addToEx\" />");
                                out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
                                out.println("<select name=\"sessionToAdd\">");
                                for(SessionUser a:seance)
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
                        else
                        {
                            out.println("<tr>");
                            out.println("\t<td>" + t.getName() + "</td>");
                            out.println("\t<td>" + t.getDureeExo() + "</td>");
                            out.println("\t<td>" + t.getNbRepetition() + "</td>");
                            out.println("\t<td>" + t.getExplanation() + "</td>");
                            out.println("\t<td>" + t.getNiveau() + "</td>");
                            out.println("<td>");
                            if(t.getBodyParts().size()!=0) {
                                int taille=0;
                                for (AMuscle muscle : t.getBodyParts()) {
                                    taille++;
                                    if (t.getBodyParts().size() != taille)
                                        out.print(muscle.getName() + ",");
                                    else
                                        out.print(muscle.getName());
                                }

                            }
                            out.println("</td>");
                            out.println("\t<td>");
                            out.println("<form method=\"post\" action=\"ExerciceServlet\">");
                            out.println("<input type=\"hidden\" name=\"action\" value=\"addToEx\" />");
                            out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
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

                    }}%>
        </tbody>
    </table>

    <table id="table_exercices" class="table table-condensed">
        <thead>
        <tr>
            <th>Nom de exercice</th>
            <th>Description exercice</th>
            <th>Duree de l'exercice</th>
            <th>Niveau de l'exercice</th>
            <th>Nombre de répetition</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

</div>
<%@ include file="footer.jsp" %>

