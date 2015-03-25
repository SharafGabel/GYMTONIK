<%@ page import="model.SessionUser" %>
<%@ page import="service.SessionService" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="service.HistoriqueService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Seance Effectuée ?"; %>
<%@ include file="../Core/header.jsp" %>

<div class="container" id="exerciseDiv">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
<p><label >Quelle séance avez-vous effectué ?</label></p>
<select class="form-control center" name="SessioNUSERR" id="SessioNUSERR">
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
        <th>Nom de l'exercice</th>
        <th>Niveau</th>
        <th>Nombre de Répétitions effectuées</th>
        <th>Duree Effectué</th>
        <th>Temps de sommeil</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
    </table>
    </div>
    <div class="col-md-3"></div>
</div>
<%@ include file="../footer.jsp" %>
<script>
    $(document).ready(function() {

     $('#SessioNUSERR').change(function(event) {


     var sessionuser = $("select#SessioNUSERR").val();

     $.get('../HistoriqueServlet', {
     sessionUserH : sessionuser
     }, function(response) {
     $('#affExo tbody').remove();
     $.each(response, function(key, value) {
     $('<form action=\"../HistoriqueServlet\" method=\"post\">').append(
     $('<tr>').append(
     $('<td>').text(value.name),
     $('</td>	<td>').text(value.niveau),
     $('</td>	<td>').append('<input type=\"number\" min=\"5\" max=\"500\" name=\"nbRepet\"/>'),
     $('</td>	<td>').append('<input type=\"number\" min=\"1\" max=\"120\" name=\"dureeEff\"/>'),
     $('</td>	<td>').append('<input type=\"number\" min=\"1\" max=\"22\" name=\"timeS\"/>'),
     $('<input name=\"dureeARealiser\" type=\"hidden\" value='+value.dureeExo+'>'),
     $('<input name=\"nbRepetARealiser\" type=\"hidden\" value='+value.nbRepetition+'>'),
     $('<input name=\"sessionS\" type=\"hidden\" value='+sessionuser+'>'),
     $('<input name=\"htoAdd\" type=\"hidden\" value='+value.id+'>'),
     $('</td>	<td>').append('<button type=\"submit\" value=\"Enregistrer\"/></td>')
     )).appendTo('#affExo');

     });

     });
     });
     });
</script>
