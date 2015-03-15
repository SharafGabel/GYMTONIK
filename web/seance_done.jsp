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
</body>
</html>
<%@ include file="footer.jsp" %>
<script>

    /*$(document).ready(function() {

        $('#SessioNUSERR').change(function(event) {
            $('#affExo tbody').remove();
            var sessionuser = $("select#SessioNUSERR").val();
            $.get('HistoriqueServlet', {
                sessionUserH : sessionuser
            }, function(response) {
                $.each(response, function(key, value) {

                    var tdName=document.createElement("td");
                    tdName.text(value.name);

                    var tdniveau = document.createElement("td");
                    tdniveau.text(value.niveau);

                    var f = document.createElement("form");
                    f.setAttribute('method',"post");
                    f.setAttribute('action',"HistoriqueServlet");

                    var tr = document.createElement("tr");

                    var  hidden = document.createElement("input");
                    hidden.setAttribute('type',"hidden");
                    hidden.setAttribute('name',"idEx");
                    hidden.setAttribute('value',value.id);

                    var inputRepet = document.createElement("input");
                    inputRepet.setAttribute('type',"number");
                    inputRepet.setAttribute('min',"5");
                    inputRepet.setAttribute('max',"200");
                    inputRepet.setAttribute('name',"nbRepet");

                    var tdRepet = document.createElement("td");
                    tdRepet.appendChild(inputRepet);

                    var inputDuree = document.createElement("input");
                    inputDuree.setAttribute('type',"number");
                    inputDuree.setAttribute('min',"1");
                    inputDuree.setAttribute('max',"120");
                    inputDuree.setAttribute('name',"dureeEff");

                    var tdDuree = document.createElement("td");
                    tdDuree.appendChild(inputDuree);

                    var inputTimeS = document.createElement("input");
                    inputTimeS.setAttribute('type',"number");
                    inputTimeS.setAttribute('min',"1");
                    inputTimeS.setAttribute('max',"120");
                    inputTimeS.setAttribute('name',"timeSleep");

                    var tdTime = document.createElement("td");
                    tdTime.appendChild(inputTimeS);

                    var inputSubmit = document.createElement("input");
                    inputSubmit.setAttribute('type',"submit");

                    var tdSubmit = document.createElement("td");
                    tdSubmit.appendChild(inputSubmit);

                    tr.appendChild(tdName,tdniveau,hidden,tdRepet,tdDuree,tdRepet,tdTime,tdSubmit);
                    f.appendChild(tr);

                    var aff = document.getElementsByName('affExo');
                    aff.appendChild(f);
                    //f.appendTo('#affExo');

                    /*$('<tr>').append(
                            $('<td>').text(value.name),
                            $('<td>').text(value.niveau)
                            ).appendTo('#affExo');

                });

            });
        });
    });
*/
    $(document).ready(function() {

     $('#SessioNUSERR').change(function(event) {

         $('#affExo tbody').remove();
     var sessionuser = $("select#SessioNUSERR").val();

     $.get('HistoriqueServlet', {
     sessionUserH : sessionuser
     }, function(response) {
     $.each(response, function(key, value) {
     $('<form action=\"HistoriqueServlet\" method=\"post\">').append(
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
