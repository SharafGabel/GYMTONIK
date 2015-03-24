<%@ page import="model.SessionUser" %>
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
<%@ include file="../Core/header.jsp" %>
<div class="form-group" id="seanceDiv">
    <form id="formSeance" class="form-horizontal" name="formSeance" method="post" action="../SessionServlet">
    <input type='hidden' name='action' value='createSession'/>
        <h1>Créer une séance</h1>

        <input class="btn btn-small btn-warning" type="submit" value="Créer une nouvelle séance">
    </form>
</div>

<table id="affSeance" class="table table-condensed">
    <tr>
        <td>Nom Séance</td>
        <td>Date création séance</td>
        <td>Modifier</td>
        <td>Supprimer</td>
    </tr>

    <%
        List<SessionUser> sessionUserList = SessionService.getSessionList((User) session.getAttribute("User"));
        for(SessionUser a:sessionUserList) {
            out.println("<tr id='" + a.getIdS() + "'class='success'>");
            out.println("<td>" + a.getName() + "</td>");
            out.println("<td>" + a.getDateProgram() + "</td>");
            out.println("<form id=\"updateSession\" name=\"updateSession\" method=\"post\" action=\"updateSession.jsp\">");
            out.println("<input type='hidden' name='sessionId' value='" + a.getIdS() + "'/>");
            out.println("<input type='hidden' name='action' value='updateSession'/>");
            out.println("<td><button class='btn btn-small btn-warning' type='submit'>Modifier</button></td>");
            out.println("</form>");
            out.println("<form id=\"deleteSession\" name=\"deleteSession\" method=\"post\" action=\"../SessionServlet\">");
            out.println("<input type='hidden' name='sessionId' value='" + a.getIdS() + "'/>");
            out.println("<input type='hidden' name='action' value='deleteSession'/>");
            out.println("<td><button class='btn btn-small btn-warning' type='submit'>Supprimer</button></td>");
            out.println("</form>");
        }
     %>
</table>
<%@ include file="../footer.jsp" %>
