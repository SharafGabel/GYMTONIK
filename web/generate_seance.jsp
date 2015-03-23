<%@ page import="model.AMuscle" %>
<%@ page import="service.MuscleService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<% String title = "Générer une séance"; %>
<%@ include file="header.jsp" %>

<div class="page-container">
    <form id="generateSession" class="form-horizontal" name="generateSession" method="post" action="SessionServlet">
        <input type="hidden" name="action" value="generateSession" />
    <p>
        Sélectionner les muscles pour générer une séance :
    </p>
    <p>
        <%
            List<AMuscle> aMuscles = MuscleService.getAllMuscles();
            for(AMuscle a:aMuscles)
            {
        %>
        <%=a.getName()%><input class="checkbox" type="checkbox" name="inlineCheckboxMuscle" value="<%=a.getId()%>">
        <%}%>
    </p>
        <p><label class="lab">Sélectionner le niveau de l'exercice souhaité</label></p>
        <input type="number" min="1" max="3" name="niveau" id="niveau" />

        <p><label class="lab">Nombre d'exercices souhaité</label></p>
        <input type="number" min="1" max="15" name="nbExo" id="nbExo" />

        <p><button type="submit">Générer la séance</button></p>
    </form>
</div>

<%@ include file="footer.jsp" %>