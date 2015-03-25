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
<%@ include file="/Core/header.jsp" %>
<div  class="container" id="container">
    <form class="form-inline">
        <input type="hidden" name="choose" value="listH"/>


        <div class="col-md-2"></div>
        <div class="col-md-8">
            <p><label>Evaluation des exerices d'un séance</label></p>
            <select class="form-control center" name="SessionUserFromPerformancePage" id="SessionUserFromPerformancePage">
                <%
                    List<SessionUser> sessionUserList = SessionService.getSessionList((User) session.getAttribute("User"));
                    for(SessionUser a:sessionUserList)
                    {
                %>
                <option name="optionName" value="<%=a.getIdS()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
                <%}%>
            </select>
            <table id="affExo" class="table table-condensed">
                <thead>
                <tr>
                    <th>Nom exercice</th>
                    <th>Niveau</th>
                    <th>Pourcentage Duree Effectué</th>
                    <th>Pourcentage de Répétitions Réussi</th>
                    <th>Temps de sommeil</th>
                    <th>Date exercice Effectué</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="affEx">
                </tbody>
            </table>
        </div>
        <div class="col-md-2"></div>
    </form>
</div>
<%@ include file="../footer.jsp" %>
<script>
    $(document).ready(function() {

        $('#SessionUserFromPerformancePage').change(function(event) {
            var sessionUser = $("select#SessionUserFromPerformancePage").val();
            $.get('../HistoriqueServlet', {
                sessionUserFromPerformance : sessionUser
            }, function(response) {
                $('#affExo tbody').remove();
                $.each(response, function(key, value) {
                    for(j=0;j<6;j++)
                    {
                        $('<tr>').append(
                                $('<form action=\"../PerformanceServlet\" method=\"post\">').append(
                                        $('<td>').text(response[j][1]),
                                        $('<td>').text(response[j][2]),
                                        $('<td>').text(response[j][3]),
                                        $('<td>').text(response[j][4]),
                                        $('<td>').text(response[j][5]),
                                        $('<td>').text(response[j][6]),
                                        $('<input class=\"form-control\" name=\"seanceId\" type=\"hidden\" value='+sessionUser+'>'),
                                        $('<input class=\"form-control\" name=\"idExo\" type=\"hidden\" value='+response[j][0]+'>'),
                                        $('<input class=\"form-control\" name=\"niveau\" type=\"hidden\" value='+response[j][2]+'>'),
                                        $('<input class=\"form-control\" name=\"dureeEffectuee\" type=\"hidden\" value='+response[j][3]+'>'),
                                        $('<input name=\"repetReussi\" type=\"hidden\" value='+response[j][4]+'>'),
                                        $('</td>	<td>').append('<button type=\"submit\" value=\"Enregistrer\">Evaluer</button></td>')
                                )).appendTo('#affExo');
                    }

                });

            });
        });
    });
</script>