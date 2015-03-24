<%@ page import="java.util.List" %>
<%@ page import="service.ExerciseService" %>
<%@ page import="model.AMuscle" %>
<%@ page import="service.MuscleService" %>
<%@ page import="model.ATraining" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Exercice"; %>
<%@ include file="header.jsp" %>

<%
    String idExercice = request.getParameter("idEx");
    ATraining exercise = ExerciseService.getExercise(idExercice);

    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
    /*  Dans le cas où l'utilisateur s'amuserait à appeler cette page
        avec le mauvais identifiant ou sans identifiant */
    if (exercise == null) {
        response.sendRedirect("exercise.jsp");
    }
%>
    <div class="container" id="exerciseDiv">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
    <form id="formEx" name="formEx" class="form-horizontal" method="post" action="ExerciceServlet">
        <h1>Modifier un exercice</h1>

        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="idEx" value="<%= exercise.getId()%>" />


        <p><label>Nom de l'exercice</label></p>
        <input class="form-control" type="text" name="nomEx" id="nomEx" value="<%= exercise.getName()%>"/>

        <p><label>Durée (en min)</label></p>
        <input class="form-control" type="number" min="1" max="120" name="duree" id="duree" value="<%= exercise.getDureeExo() %>" />

        <p><label>Nombre de répétitions </label></p>
        <input class="form-control" type="number" min="5" max="500" name="nbRepet" id="nbRepet" value="<%= exercise.getNbRepetition() %>" />

        <p><label>Niveau</label></p>
        <input class="form-control" type="number" min="1" max="3" name="niveau" id="niveau" readonly value="<%= exercise.getNiveau() %>" />

        <p><label>Description de l'exercice</label></p>
        <input class="form-control" type="text" name="descriptionEx" id="descriptionEx" value="<%= exercise.getExplanation() %>" />

        <p>
            <%
                List<AMuscle> aMuscles = MuscleService.getAllMuscles();
                for(AMuscle a:aMuscles)
                {
            %>
            <%=a.getName()%><input class="checkbox" type="checkbox" name="inlineCheckboxMuscle" value="<%=a.getId()%>">
            <%}%>
        </p>

        <p><button class="btn btn-small btn-success" type="submit">Enregistrer la modification</button></p>
        <p><a class="btn btn-small btn-warning" href="showMyExercise.jsp">Voir mes exercices</a></p>
    </form>
</div>
            <div class="col-md-3"></div>
        </div>
    </div>
<%@ include file="footer.jsp" %>