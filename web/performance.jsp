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


    
</div>
<%@ include file="footer.jsp" %>
