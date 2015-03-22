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

<div class="page-container" id="exerciseDiv">
    <form id="formSeance" class="form-horizontal" name="formSeance" method="get" action="PerformanceServlet">
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
        <select name="exerciseid" id="exerciseDynamic">
            <option>Selectionner un exercice</option>
        </select>
        <br>
        <button class="btn btn-small btn-warning" name="submit_choice" value="user_performance" type="submit" >Voir mon evolution</button>
        <button class="btn btn-small btn-warning" name="submit_choice" value="compare_performance" type="submit">comparer mes performances avec les autres</button>
    </form> <!-- todo:j'obtiens le fichier json , mais je n'arrive pas a le passer au script js juste au-dessus(extraire les resultats du json) -->

    <div  class="page-container" id="container">
        <button id="button">Set new data</button>
    </div>
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
        <tbody>
        <tr>
            <th>Nom exercice</th>
            <th>Niveau</th>
            <th>Pourcentage Duree Effectué</th>
            <th>Pourcentage de Répétitions Réussi</th>
            <th>Temps de sommeil</th>
            <th>Date exercice Effectué</th>
            <th>Action</th>
        </tr>
        </tbody>
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

    //Dynamic version - HighCharts Graph - performance.jsp
    $(function () {
        var chart;
        $(document).ready(function() {
            chart = new Highcharts.Chart({

                chart: {
                    renderTo: 'container',
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },

                title: {
                    text: 'Evolution de la performance '
                },
                yAxis: {
                    title: {
                        text: 'Nb de repetition '
                    }
                },
                xAxis: {
                    type: 'datetime',
                    labels: {
                        format:'{value:%Y-%m-%d}',
                        rotation:45,
                        align:'right'
                    },
                    title: {
                        text: 'Date'
                    }
                },
                tooltip: {
                    // pointFormat: '{series.name}: <b>{point.percentage}%</b>',
                    pointFormat: '{point.x:}: {point.y:.2f} %',
                    percentageDecimals: 1
                },
                plotOptions: {

                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000',
                            formatter: function() {
                                //Highcharts.numberFormat(this.percentage,2)
                                return '<b>'+ this.point.name +'</b>: '+Highcharts.numberFormat(this.percentage,2) +' %';
                            }
                        }
                    }
                },

                series: [{
                    type: 'line',
                    name: 'performance',
                    pointStart:Date.UTC(2015,2,1),
                    pointInterval:24*36e5
                }]
            });
        });


        $.ajax({
            type:"GET",
            // url:'PerformanceServlet?submit_choice='+document.getElementsByName("submit_choice").value,//Servlet
            url:'PerformanceServlet',
            dataType : 'json',
            //data: {submit_choice: 'idTest'},
            success:function(data){

                browsers = [],

                        $.each(data,function(i,d){
                            browsers.push([d.dateProgEffectue,d.ratioRepet]);
                        });

                chart.series[0].setData(browsers);
            },
            error:function(e){
                alert(e);
            }
        });
    });

</script>