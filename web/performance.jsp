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

    <div  class="page-container" id="container">
    <button id="button">Set new data</button>
    </div>
<div class="page-container" id="exerciseDiv">

    Veuillez selectionner votre séance :
    <select name="SessionUserForChart" id="SessionUserForChart">
        <%
            List<SessionUser> sessionUserListForChart = SessionService.getSessionList((User) session.getAttribute("User"));
            for(SessionUser a:sessionUserListForChart)
            {
        %>
        <option name="optionName" value="<%=a.getIdS()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
        <%}%>
    </select>
    <select id="exerciseDynamic">
        <option>Selectionner un exercice</option>
    </select>

    <form>
    <input type="hidden" name="choose" value="listH"/>
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
            <th>Nom exercice</th>
            <th>Niveau</th>
            <th>Pourcentage Duree Effectué</th>
            <th>Pourcentage de Répétitions Réussi</th>
            <th>Temps de sommeil</th>
            <th>Date exercice Effectué</th>
            <th>Action</th>
            <thead>

        </tr>
    </table>


    </form>
    
</div>
<%@ include file="footer.jsp" %>
<script>
    $(document).ready(function() {

        $('#SessionUserFromPerformancePage').change(function(event) {
            var sessionUser = $("select#SessionUserFromPerformancePage").val();
            $.get('HistoriqueServlet', {
                sessionUserFromPerformance : sessionUser
            }, function(response) {
                $('#affExo').children().remove();
                $.each(response, function(key, value) {
                        for(j=0;j<6;j++)
                        {
                            $('<form action=\"PerformanceServlet\" method=\"post\">').append(
                                    $('<tr>').append(
                                        $('<td>').text(response[j][1]),
                                        $('<td>').text(response[j][2]),
                                        $('<td>').text(response[j][3]),
                                        $('<td>').text(response[j][4]),
                                        $('<td>').text(response[j][5]),
                                        $('<td>').text(response[j][6]),
                                        $('<input name=\"seanceId\" type=\"hidden\" value='+sessionUser+'>'),
                                        $('<input name=\"idExo\" type=\"hidden\" value='+response[j][0]+'>'),
                                        $('<input name=\"niveau\" type=\"hidden\" value='+response[j][2]+'>'),
                                        $('<input name=\"dureeEffectuee\" type=\"hidden\" value='+response[j][3]+'>'),
                                        $('<input name=\"repetReussi\" type=\"hidden\" value='+response[j][4]+'>'),
                                        $('</td>	<td>').append('<button type=\"submit\" value=\"Enregistrer\">Evaluer</button></td>')
                                    )).appendTo('#affExo');
                        }

                });

            });
        });
    });

    $(document).ready(function() {

        $('#SessionUserForChart').change(function(event) {
            var seance = $("select#SessionUserForChart").val();
            $.get('ChartServlet', {
                seanceid : seance
            }, function(response) {
                $('#exerciseDynamic').children().remove();
                $.each(response, function(index, value) {
                    $('<option>').val(value.id).text(value.name).appendTo('#exerciseDynamic');
                });
            });
        });
    });

</script>