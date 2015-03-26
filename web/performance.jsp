<%@ page import="model.SessionUser" %>
<%@ page import="service.SessionService" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.SessionServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<% String title = "Performances"; %>
<%@ include file="Core/header.jsp" %>

<script type="text/javascript">
    $(document).ready(
            function()
            {
                $.getJSON('localhost:8080/PerformanceServlet?SessionUserForChart=1&exerciseid=6&submit_choice=user_performance', function(data)
                {
                    Alert(Data);
                });
            });
</script>

<div class="container" id="container" id="exerciseDiv">
    </div>
    <!--
    <form id="formSeance" class="form-horizontal" name="formSeance" method="get" action="PerformanceServlet">-->
    <div class="col-md-2"></div>
    <div class="col-md-8">
        Veuillez selectionner votre séance :
        <select class="form-control center" name="SessionUserForChart" id="SessionUserForChart">
            <option>Selectionner une seance</option>

            <%
                List<SessionUser> sessionUserListForChart = SessionService.getSessionList((User) session.getAttribute("User"));
                for(SessionUser a:sessionUserListForChart)
                {
            %>
            <option name="optionName" value="<%=a.getIdS()%>"> <%=a.getName()+" ( Crée le "+a.getDateProgram()+" )"%></option>
            <%}%>
        </select>
        <select class="form-control center" name="exerciseid" id="exerciseDynamic">
            <option>Selectionner un exercice</option>
        </select>
        <br>
        <button class="btn btn-small btn-warning center" name="submit_choice" id="user_performance" value="user_performance" type="submit" >Voir mon evolution</button>
        <button class="btn btn-small btn-warning center" name="submit_choice" id="compare_performance" value="compare_performance" type="submit">Comparer mes performances avec les autres</button>
        <button class="btn btn-small btn-warning center" name="resetChart" id="resetChart" value="resetChart">Reset</button>

    </div>
    <div class="col-md-2"></div>
</div>
<%@ include file="footer.jsp" %>
<script>

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
                        text: 'Ratio Nb de repetitions '
                    }
                },
                xAxis: {
                    //type: 'datetime',
                    title: {
                        text: 'Date'
                    },
                    labels:
                    {
                        enabled: false
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

                },{
                    type: 'line',
                    name: 'performance moyennes',

                }]


            });
        });


        $('#resetChart').click(function() {
            location.reload();
        });

        $('#user_performance').click(function() {
        function perfData (data) {

            browsers = [],

                    $.each(data,function(i,d){
                        browsers.push([d.dateProgEffectue,d.ratioRepet]);
                    });
   
            chart.series[0].setData(browsers);

        }
        $.ajax({
            type:"GET",
            url:'PerformanceServlet?submit_choice='+$(this).attr('value')+'&exerciseid='+$('#exerciseDynamic option:selected').attr('value'),//Servlet
            dataType : 'json',
            success:function(data){
                console.log(data[0]);
                //alert(data[0].nbRepetEffectue);
                perfData(data);
               
            },
            error:function(e){
                alert(e);
            }
        });
        });

        $('#compare_performance').click(function() {

            function perfDataCompare (data) {

                var browsers = [];
                for(var i=0;i<data.exerciceSessions.length;i++) {
                    browsers.push(data.exerciceSessions[i].ratioRepet);
                }


                var browsersOther = [];

                for (var j = 0; j < data.exerciceSessions.length; j++) {
                    browsersOther.push(data.moyenneGenerale);
                }

                chart.series[0].setData(browsers);
                chart.series[1].setData(browsersOther);

            }
            $.ajax({
                type:"GET",
                url:'PerformanceServlet?submit_choice='+$(this).attr('value')+'&exerciseid='+$('#exerciseDynamic option:selected').attr('value')+'&seanceId='+$('#SessionUserForChart option:selected').attr('value'),//Servlet
                dataType : 'json',
                success:function(data){
                    perfDataCompare(data);
                    chart.series = data;

                },
                error:function(e){
                    alert(e);
                }
            });
        });
    });

</script>