<%@ page import="model.SessionUser" %>
<%@ page import="model.User" %>
<%@ page import="service.GetList" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Exercise" %>
<%@ page import="service.ExerciseService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Exercice"; %>
<%@ include file="header.jsp" %>

<%
    String idExercice = request.getParameter("idEx");
    Exercise exercise = ExerciseService.getExercise(idExercice);

    /*  Dans le cas où l'utilisateur s'amuserait à appeler cette page
        avec le mauvais identifiant ou sans identifiant */
    if (exercise == null) {
        response.sendRedirect("exercise.jsp");
    } else {
%>
<div class="page-container" id="exerciseDiv">

    <form id="formEx" name="formEx" method="post" action="ExerciceServlet">
        <h1>Modifier un exercice</h1>

        <!-- Champs cachés afin d'indiquer au servlet l'action qu'on souhaite réaliser,
        ici modifier l'exercice d'identifiant idExercice -->
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="idEx" value="<%= idExercice %>" />


        <p><label class="lab">Nom de l'exercice</label></p>
        <input type="text" name="nomEx" id="nomEx" value="<%= exercise.getName()%>"/>

        <p><label class="lab">Durée (en min)</label></p>
        <input type="number" min="1" max="120" name="duree" id="duree" value="<%= exercise.getLength() %>" />

        <p><label class="lab">Description de l'exercice</label></p>
        <input type="text" name="descriptionEx" id="descriptionEx" value="<%= exercise.getExplanation() %>" />

        <p><label class="lab">Choix de la séance</label></p>
        <select name="sessionUser">
            <%
                List<SessionUser> sessionUserList = GetList.getSessionList((User) session.getAttribute("User"));
                for(SessionUser s:sessionUserList)
                {
                    //s.getName();
            %>
            <option name="optionName" value="<%=s.getId()%>" <% if (s.getId().equals(exercise.getSessionUser().getId())) out.println("selected"); %>> <%=s.getName()+" ( Crée le "+s.getDateProgram()+" )"%></option>
            <%}%>
        </select>

        <p><button type="submit">Enregistrer la modification</button></p>
    </form>
</div>
<%
    } //else
%>