<%@ page import="model.SessionUser" %>
<%@ page import="service.GetList" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="service.SessionService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<% String title = "Séance"; %>
<%@ include file="header.jsp" %>
<div class="form-group" id="seanceDiv">
    <form id="formSeance" class="form-horizontal" name="formSeance" method="post" action="SessionServlet">
        <h1>Créer une séance</h1>

        <label class="lab">Temps de sommeil</label>
        <input type="number" min="0" max="20" name="sommeil" id="sommeil" />

        <button type="submit">Création d'exercices</button>
    </form>
</div>

<table id="affSeance" class="table table-condensed">
    <tr>
        <td>Nom Séance</td>
        <td>Date création séance</td>
        <td>Nombre d'exercices</td>
    </tr>
    <%
        List<SessionUser> sessionUserList = SessionService.getSessionList((User) session.getAttribute("User"));
        for(SessionUser a:sessionUserList) {
            a.getName();
            if (a.isPerform()) {
                out.println("<tr id='" + a.getIdS() + "'class='success'>");

            } else {
                out.println("<tr id='" + a.getIdS() + "'class='warning'>");
            }
            out.println("<td>" + a.getName() + "</td>");
            out.println("<td>" + a.getDateProgram() + "</td>");
            out.println("<td>" + a.getTrainings().size() + "</td>");
        }
     %>
</table>
<%@ include file="footer.jsp" %>
