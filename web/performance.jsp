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



    <div id="container" style="height: 400px"></div>
    <button id="button">Set new data</button>

    <p><label class="lab">TEST Niveau ?</label></p>
    <select name="SessionUserFromPerformancePage" id="SessionUserFromPerformancePage">
        <%
            List<SessionUser> sessionUserList = SessionService.getSessionList((User) session.getAttribute("User"));
            for(SessionUser a:sessionUserList)
            {
        %>
        <option name="optionName" value="<%=a.getIdS()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
        <%}%>
    </select>

    <table id="affExo" class="table table-condensed">
        <tr>
            <thead>
            <th>Nom de exercice</th>
            <th>Description exercice</th>
            <th>Duree de l'exercice</th>
            <th>Niveau de l'exercice</th>
            <th>Nombre de répetition</th>
            <thead>

        </tr>
    </table>



    
</div>
<%@ include file="footer.jsp" %>
