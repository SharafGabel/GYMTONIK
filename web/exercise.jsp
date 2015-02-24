<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<% String title = "Exercice"; %>
<%@ include file="header.jsp" %>
<div class="page-container" id="exerciseDiv">
    <form id="formEx" name="formEx" method="post" action="ExerciceServlet">
        <h1>Créer un Exercice</h1>

        <p><label class="lab">Nom de l'exercice</label></p>
        <input type="text" name="nomEx" id="nomEx" />

        <p><label class="lab">Durée (en min)</label></p>
        <input type="number" min="1" max="120" name="duree" id="duree" />

        <p><label class="lab">Description de l'exercice</label></p>
        <input type="text" name="descriptionEx" id="descriptionEx" />

        <p><button type="submit">Enregistrer l'exercice</button></p>
    </form>

</div>
<%@ include file="footer.jsp" %>
