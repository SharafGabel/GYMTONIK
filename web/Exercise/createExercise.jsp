<%@ page import="java.util.List" %>
<%@ page import="model.AMuscle" %>
<%@ page import="service.MuscleService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<% String title = "Creation d'exercice"; %>
<%@ include file="../Core/header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form id="formEx" class="form-horizontal" name="formEx" method="post" action="ExerciceServlet">
                <h1>Créer un Exercice</h1>

                <!-- Champ caché afin d'indiquer au servlet l'action qu'on souhaite réaliser,
                ici ajouter un nouvel exercice -->
                <input type="hidden" name="action" value="add" />

                <p><label>Nom de l'exercice</label></p>
                <input class="form-control" type="text" name="nomEx" id="nomEx" />

                <p><label>Durée (en min)</label></p>
                <input class="form-control" type="number" min="1" max="120" name="duree" id="duree" />

                <p><label>Nombre de répétitions </label></p>
                <input class="form-control" type="number" min="5" max="500" name="nbRepet" id="nbRepet" />

                <p><label>Description de l'exercice</label></p>
                <input class="form-control" type="text" name="descriptionEx" id="descriptionEx" />

                <p><label>Niveau de l'exercice</label></p>
                <input class="form-control" type="number" min="1" max="3" name="niveau" id="niveau" />

                    <%
                        List<AMuscle> aMuscles = MuscleService.getAllMuscles();
                        for(AMuscle a:aMuscles)
                        {
                    %>
                    <%=a.getName()%><input class="checkbox" type="checkbox" name="inlineCheckboxMuscle" value="<%=a.getId()%>">
                    <%}%>
                </p>

                <p><button class="btn btn-small btn-success" type="submit">Enregistrer l'exercice</button></p>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
    
</div>
<%@ include file="../footer.jsp" %>