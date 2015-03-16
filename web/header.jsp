<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><% if (title != null) out.print(title); %></title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link href="https://jasny.github.io/bootstrap/dist/css/jasny-bootstrap.min.css" rel="stylesheet">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- Custom styles for this template -->
    <link href="style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/style.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="http://jasny.github.io/bootstrap/dist/js/jasny-bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <script src="http://yui.yahooapis.com/3.8.1/build/yui/yui-min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="./assets/js/visualisation-chart-script.js"></script>

    <script src="http://code.highcharts.com/highcharts.js"></script>


    <script>
        $(document).ready(function() {

            $('#sessionUser').change(function(event) {
                var sessionuser = $("select#sessionUser").val();
                $.get('HistoriqueServlet', {
                    sessionUser : sessionuser
                }, function(response) {
                    $('#affSeance').hide();
                    $('#table_exercices tbody').remove();
                    $.each(response, function(key, value) {
                        $('<tr>').append(
                                $('<td>').text(value.name),
                                $('<td>').text(value.explanation),
                                $('<td>').text(value.dureeExo),
                                $('<td>').text(value.niveau),
                                $('<td>').text(value.nbRepetition)).appendTo('#table_exercices');

                    });

            });
        });
        });
        $(document).ready(function() {

            $('#sessionUserPerf').change(function(event) {
                var sessionuser = $("select#sessionUserPerf").val();
                $.get('PerformanceServlet', {
                    sessionUserPerf : sessionuser
                }, function(response) {
                   // $('#affSeance').hide();
                    $('#table_exercicesUser tbody').remove();
                    $.each(response, function(key, value) {
                        $('<tr>').append(
                                $('<td>').text(value.name),
                                $('<td>').text(value.explanation),
                                $('<td>').text(value.dureeExo),
                                $('<td>').text(value.niveau),
                                $('<td>').text(value.nbRepetition)).appendTo('#table_exercicesUser');

                    });

                });
            });
        });
        $(document).ready(function() {

            $('#exerciseLevel').change(function(event) {
                var exerciselevel = $("select#exerciseLevel").val();
                $.get('HistoriqueServlet', {
                    exerciseLevel : exerciselevel
                }, function(response) {
                    $('#affSeance').hide();
                    $('#table_exercices tbody').remove();
                    $.each(response, function(key, value) {
                        $('<tr>').append(
                                $('<td>').text(value.name),
                                $('<td>').text(value.explanation),
                                $('<td>').text(value.dureeExo),
                                $('<td>').text(value.niveau),
                                $('<td>').text(value.nbRepetition)).appendTo('#table_exercices');

                    });

                });
            });
        });

        $(document).ready(function() {

            $('#SessioNUSERR').change(function(event) {
                var sessionUser = $("select#SessioNUSERR").val();
                $.get('HistoriqueServlet', {
                      sessionUserFromPerf : sessionUser
                }, function(response) {
                    $('#affExo tbody').remove();
                    $.each(response, function(key, value) {
                        $('<tr>').append(
                                $('<td>').text(value.name),
                                $('<td>').text(value.explanation),
                                $('<td>').text(value.dureeExo),
                                $('<td>').text(value.niveau),
                                $('<td>').text(value.nbRepetition)).appendTo('#affExo');

                    });

                });
            });
        });

        $(document).ready(function() {

            $('#sessionUserPerf').change(function(event) {
                var exerciselevel = $("select#sessionUserPerf").val();
                $.get('PerformanceServlet', {
                    exerciseLevel : exerciselevel
                }, function(response) {
                    $('#affSeance').hide();
                    $('#table_exercices tbody').remove();
                    $.each(response, function(key, value) {
                        $('<tr>').append(
                                $('<td>').text(value.name),
                                $('<td>').text(value.explanation),
                                $('<td>').text(value.dureeExo),
                                $('<td>').text(value.niveau),
                                $('<td>').text(value.nbRepetition)).appendTo('#table_exercices');

                    });

                });
            });
        });
    </script>

    <!--Dynamic version - HighCharts Graph - performance.jsp-->
    <script type="text/javascript">
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
                url:'PerformanceServlet',//Servlet
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




</head>

<body>

<div class="navmenu navmenu-default navmenu-fixed-left offcanvas-sm" id="navMenu">
    <a class="navmenu-brand" href="#">Gym Tonik</a>
    <hr />
    <ul class="nav navmenu-nav">
        <li><a href="index.jsp">Accueil</a></li>
        <li><a href="seance.jsp">Séance</a></li>
        <li><a href="seance_done.jsp">Séance effectuée ?</a></li>
        <li><a href="exercise.jsp">Exercice</a></li>
        <li><a href="performance.jsp">Performances</a></li>
        <li><a href="profil.jsp">Profil</a></li>
        <li><a href="LogoutServlet">Se Déconnecter</a></li>
    </ul>
    <hr />
    <a href="#" class="navmenu-brand"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${sessionScope.username}</a>
</div>

<div class="navbar navbar-default navbar-fixed-top">
    <button type="button" class="navbar-toggle hidden-md hidden-lg" data-toggle="offcanvas" data-target="#navMenu" data-canvas="body">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navmenu-brand" id="titleProject" href="#"><% if (title != null) out.print(title); %></a>
</div>
