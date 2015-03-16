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

Veuillez selectionner votre séance :
<select name="sessionUserPerf" id="sessionUserPerf">
    <option name="optionName" value="0">Aucune Séance</option>
    <option name="optionName" value="<%=session.getAttribute("User")%>"> <%=session.getAttribute("User")%></option>

</select>

    <div id="container">
    <button id="button">Set new data</button>
    </div>
<div class="page-container" id="exerciseDiv">
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
            <th>Id exercice</th>
            <th>Ratio Duree</th>
            <th>Ratio Repetition</th>
            <th>Temps de sommeil</th>
            <th>Date exercice Effectué</th>
            <thead>

        </tr>
    </table>



    
</div>
<%@ include file="footer.jsp" %>
<script>
    $(document).ready(function() {

        $('#SessionUserFromPerformancePage').change(function(event) {
            var sessionUser = $("select#SessionUserFromPerformancePage").val();
            $.get('PerformanceServlet', {
                sessionUserFromPerformance : sessionUser
            }, function(response) {
                $('#affExo tbody').remove();
                $.each(response, function(key, value) {
                    $('<tr>').append(
                            $('<td>').text(value.idEx),
                            $('<td>').text(value.ratioDuree),
                            $('<td>').text(value.ratioRepet),
                            $('<td>').text(value.timeSleep),
                            $('<td>').text(value.dateProgEffectue)).appendTo('#affExo');

                });

            });
        });
    });
</script>