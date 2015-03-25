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
    <link href="../style.css" rel="stylesheet">
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
                        $('<tr class=\"success\">').append(
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
</head>
<body>
<%
    if(request.getSession().getAttribute("User")==null)
    {
    %>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="myLoginModal">
    <div class="modal-dialog modal-lg" style="width: 450px;">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title label label-success modalFS" id="mySmallModalLabel">Se connecter</h2>
            </div>
            <div class="modal-body center">
                <form id="formLogin" class="form-horizontal" name="formLogin" method="post" action="../LoginServlet">
                    <label>Nom d'utilisateur</label>
                    <input class="form-control" type="text" name="username" id="username" placeholder="Nom d'utilisateur" />

                    <label>Mot de Passe</label>
                    <input class="form-control" type="password" name="password" id="password" placeholder="Mot de Passe" />

                    <button class="btn btn-small btn-success" style="margin-top: 4px;" type="submit">Log in</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%}%>
    <header>
        <div class="center"><img src="/assets/img/logo.png" alt="Logo" id="header-logo"/></div>
        <div class="floatRight">
            <%
                if(request.getSession().getAttribute("User")==null){
            %>
            <button type="button" class="btn btn-success" style="margin-top: -49px;margin-right: 30px;" data-toggle="modal" data-target="#myLoginModal">Se connecter</button>
            <%}
                else{
            %>
            <a href="../LogoutServlet" style="margin-top: -49px;margin-right: 30px;" class="btn btn-success" >Se Déconnecter</a>
            <%}%>
        </div>
    </header>
    <nav>
        <div class="centered-pills menuBar" role="navigation">
            <ul class="nav nav-pills">
      <%
          if(request.getSession().getAttribute("User")!=null){
      %>
                <li class="divider-vertical"></li>
                <li>
                    <a class="menuButton center" id="seance" data-target="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Séances
                        <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu" role="menu" aria-labelledby="seance">
                        <li>
                            <a class="menuButton center" href="../Session/showSessions.jsp">Liste des séances</a>
                        </li>
                        <li>
                            <a class="menuButton center" href="../Session/createSession.jsp">Créer une séance</a>
                        </li>
                        <li>
                            <a class="menuButton center" href="../Session/seance_done.jsp">Valider une séance</a>
                        </li>
                        <li>
                            <a class="menuButton center" href="../Session/generate_seance.jsp">Générer une séance</a>
                        </li>
                        <li>
                            <a class="menuButton center" href="../Session/evaluateSession.jsp">Evaluer une séance</a>
                        </li>
                    </ul>
                </li>
                <li class="divider-vertical"></li>
                <li>
                        <a class="menuButton center" id="exercice" data-target="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Exercices
                            <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu" role="menu" aria-labelledby="exercice">
                            <li>
                                <a class="menuButton center" href="../Exercise/showMyExercises.jsp">Liste de mes exercices</a>
                            </li>
                            <li>
                                <a class="menuButton center" href="../Exercise/showExercises.jsp">Liste des exercices</a>
                            </li>
                            <li>
                                <a class="menuButton center" href="../Exercise/createExercise.jsp">Créer un exercice</a>
                            </li>
                        </ul>
                </li>
                <li class="divider-vertical"></li>
                <li>
                    <a class="menuButton center" href="../performance.jsp">Performances</a>
                </li>
                <li class="divider-vertical"></li>
                <%}%>
            </ul>
        </div>
    </nav>
