<%@ page import="model.SessionUser" %>
<%@ page import="service.GetList" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
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

    <select class="selectpicker">
        <%
            List<SessionUser> sessionUserList = GetList.getSessionList((User) session.getAttribute("User"));
            for(SessionUser a:sessionUserList)
            {
                a.getName();
        %>
        <option value="<%=a.getId()%>"> <%=a.getName()%></option>
        <%}%>
    </select>

</div>
<%@ include file="footer.jsp" %>
