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
    </script>

    <script>
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
    </script>

</head>

<body>

<div class="navmenu navmenu-default navmenu-fixed-left offcanvas-sm" id="navMenu">
    <a class="navmenu-brand" href="#">Gym Tonik</a>
    <hr />
    <ul class="nav navmenu-nav">
        <li><a href="index.jsp">Accueil</a></li>
        <li><a href="seance.jsp">Séance</a></li>
        <li><a href="exercise.jsp">Exercice</a></li>
        <li><a href="performance.jsp">Performances</a></li>
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
