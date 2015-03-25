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

<div class="container" id="exerciseDiv">
    <!--
    <form id="formSeance" class="form-horizontal" name="formSeance" method="get" action="PerformanceServlet">-->
    <div class="col-md-2"></div>
    <div class="col-md-8">
        Veuillez selectionner votre séance :
        <select class="form-control center" name="SessionUserForChart" id="SessionUserForChart">
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
        <button class="btn btn-small btn-warning center" name="submit_choice" id="compare_performance" value="compare_performance" type="submit">comparer mes performances avec les autres</button>
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
                },{
                    type: 'line',
                    name: 'performance moyennes',
                    pointStart:Date.UTC(2015,2,1),
                    pointInterval:24*36e5
                }]


            });
        });


        $('#user_performance').click(function() {
        function perfData (data) {
           //alert(1);
            //alert('porjet'+data[0]);
            browsers = [],

                    $.each(data,function(i,d){
                        alert(d.ratioRepet);
                        alert(data[0].ratioRepet);
                        browsers.push([d.dateProgEffectue,d.ratioRepet]);
                    });

            chart.series[0].setData(browsers);
            alert("browser"+browsers);
           /* var char;
            
            char=$('#container').highcharts({
                
                chart: {
                    type: 'line'
                },
                title: {
                    text: 'Average Visitors'
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
                yAxis: {
                    title: {
                        text: 'Ration'
                    }
                },
                series: [{
                    type: 'line',
                    name: 'test'
                }]
            });*/

           
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
                               //alert("perfdataCompare");
               //alert(data.length);
                //("test1 - data 0");
               // alert(data[0]);
               // alert("test1 - data 1");
                //alert(data[1]);
                alert("data00"+data[0][0]);
                var tab;
                for(var i=0;i<=data.length;i++){
                    //alert(data[i]);
                    
                }
               // alert(data);
                //alert(1);
                //alert('porjet'+data[0]);
                //alert('other'+data[1]);
                browsers = [],

                        $.each(data,function(i,d){
                            alert(data[0][0]);
                            alert("d");
                            alert(d);
                            alert(d.nbRepetEffectue);
                           // alert(d.ratioRepet);
                            //alert(data[0]);
                            //alert(data[2]);
                            browsers.push([d,d]);
                        });

              //  alert(browsers);

                var browsersOther = [];
/*
                        $.each(data,function(i,d){
                            alert(d.ratioRepet);
                            alert(data[0].ratioRepet);
                            browsersOther.push([d.dateProgEffectue,d.ratioRepet]);
                        });
  */
                alert("data longueur"+data.length);
                for (var i = data.length; i <= data.length; i++) {
                    browsersOther.push([data[i],data[i]]);
                }

                chart.series[0].setData(browsers);
                chart.series[1].setData(browsersOther);
                //alert("browser"+browsers);
                //alert("browser 2"+ browsers2);

                /* var char;

                 char=$('#container').highcharts({

                 chart: {
                 type: 'line'
                 },
                 title: {
                 text: 'Average Visitors'
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
                 yAxis: {
                 title: {
                 text: 'Ration'
                 }
                 },
                 series: [{
                 type: 'line',
                 name: 'test'
                 }]
                 });*/


            }
            $.ajax({
                type:"GET",
                url:'PerformanceServlet?submit_choice='+$(this).attr('value')+'&exerciseid='+$('#exerciseDynamic option:selected').attr('value')+'&seanceId='+$('#SessionUserForChart option:selected').attr('value'),//Servlet
                dataType : 'json',
                success:function(data){
                   // alert(data[1]);
                    //alert(data[data.length]);
                    alert(data.exerciceSessions[id]);
                    console.log(data.exerciceSessions[1]);
                    //console.log(data[exerciceSessions][0]);
                    var d, i;

                    for (i = 0; i < dictionary.data.length; i++) {
                        d = dictionary.data[i];
                        alert(d.id + ' ' + d.name);
                    }
                    
                    
                    console.log(data[exerciceSessions]);
                    alert(data[exerciceSessions][0]);
                    alert(data[moyenneGenerale]);
                    //alert(data[0].nbRepetEffectue);
                    perfDataCompare(data);
                    chart.series = data;
                    //alert("chart series"+chart.series);

                },
                error:function(e){
                    alert(e);
                }
            });
        });
    });

</script>