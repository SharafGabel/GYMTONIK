<%@ page import="model.SessionUser" %>
<%@ page import="model.SessionUser" %>
<%@ page import="service.SessionService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Exercice"; %>
<%@ include file="header.jsp" %>

<%
    String sessionId = request.getParameter("sessionId");
    SessionUser sessionUser = SessionService.getSessionById(Integer.parseInt(sessionId));

    /*  Dans le cas où l'utilisateur s'amuserait à appeler cette page
        avec le mauvais identifiant ou sans identifiant */
    if (sessionUser == null) {
        response.sendRedirect("seance.jsp");
    } else {
%>
<div class="page-container" id="exerciseDiv">

    <form id="sessionForm" name="formEx" method="post" action="SessionServlet">
        <h1>Modifier une séance</h1>

        <!-- Champs cachés afin d'indiquer au servlet l'action qu'on souhaite réaliser,
        ici modifier la séance d'identifiant sessionUser -->
        <input type="hidden" name="action" value="updateSession" />
        <input type="hidden" name="sessionId" value="<%= sessionId %>" />
        <p><label class="lab">Nom de la séance</label></p>
        <input type="text" name="sessionName" id="sessionName" value="<%= sessionUser.getName()%>"/>

        <p><button type="submit">Modifer</button></p>
    </form>
</div>
<%
    } //else
%>
