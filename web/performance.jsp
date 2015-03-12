<%@ page import="model.SessionUser" %>
<%@ page import="service.SessionService" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<% String title = "Performances"; %>

<%@ include file="header.jsp" %>
<div class="page-container" id="exerciseDiv">

Veuillez selectionner votre séance :
<select name="sessionUserPerf" id="sessionUserPerf">
    <option name="optionName" value="0">Aucune Séance</option>
    <option name="optionName" value="<%=session.getAttribute("User")%>"> <%=session.getAttribute("User")%></option>

</select>

    <div id="student-bar-chart"></div>






<table id="table_exercicesUser" class="table table-condensed">
    <tr>
        <thead>
        <th>Nom de exercice</th>
        <th>Description exercice</th>
        <th>Duree de l'exercice</th>
        <th>Niveau de l'exercice</th>
        <th>Nombre de répetition</th>
        <th>Muscles travaillée</th>
        <thead>

    </tr>
</table>


    <div id="pieChartId"></div>

    
</div>
<%@ include file="footer.jsp" %>
