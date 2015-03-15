<%@ page import="model.SessionUser" %>
<%@ page import="service.SessionService" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="service.HistoriqueService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Seance Effectuée ?"; %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-container" id="exerciseDiv">
<p><label class="lab">Vous avez effectué quelle séance ?</label></p>
<select name="SessioNUSERR" id="SessioNUSERR">
    <%
        List<SessionUser> sessionUserList = SessionService.getSessionList((User) session.getAttribute("User"));
        for(SessionUser a:sessionUserList)
        {
    %>
    <option name="optionName" value="<%=a.getIdS()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
    <%}%>
</select>
    
<table id="affExo" name="affExo" class="table table-condensed">
    <thead>
    <tr>
        <th>Id Exercice</th>
        <th>Nombre de répétitions</th>
        <th>Durée Effectuée</th>
        <th>Heures de Sommeil</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
    </table>
    </div>
</body>
</html>
<%@ include file="footer.jsp" %>
<script>
    $(document).ready(function() {

        $('#SessioNUSERR').change(function(event) {
            $('#affExo tbody').remove();
            var sessionuser = $("select#SessioNUSERR").val();
            $.get('HistoriqueServlet', {
                sessionUserH : sessionuser
            }, function(response) {
                $.each(response, function(key, value) {
                    $('<tr>').append(
                            $('<td>').text(value.name),
                            $('<td>').text(value.niveau)
                            /*$('<td>').text(value.idEx),
                            $('<td>').text(value.nbRepetEffectue),
                            $('<td>').text(value.dureeEffectue),
                            $('<td>').text(value.timeSleep)*/).appendTo('#affExo');

                });

            });
        });
    });

    /*$(document).ready(function() {

     $('#SessioNUSERR').change(function(event) {
     $('#affExo tbody').remove();
     var sessionuser = $("select#SessioNUSERR").val();
     $.get('HistoriqueServlet', {
     sessionUserH : sessionuser
     }, function(response) {
     $.each(response, function(key, value) {
     $('<form action=\"HistoriqueServlet\">').append(
     $('<tr>').append(
     $('<td>').text(value.name),
     $('<td>').text(value.niveau),
     $('<input name=\"htoAdd\" type=\"hidden\" value='+value.id+'>'+'</input>'),
     $('<td><input name=\"nbRepet\"/></td>'),
     $('<td><input name=\"dureeEff\"/></td>'),
     $('<td><input name=\"timeS\"/></td>'),
     $('<td><input type=\"submit\">'+'Enregistrer"+'</input></td>'))).appendTo('#affExo');

     });

     });
     });
     });*/
</script>