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

    <!-- CSS -->
    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/supersized.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="http://jasny.github.io/bootstrap/dist/js/jasny-bootstrap.min.js"></script>
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

<div class="canvas">
    <div class="navbar navbar-default navbar-fixed-top">
        <button type="button" class="navbar-toggle hidden-md hidden-lg" data-toggle="offcanvas" data-target="#navMenu" data-canvas="body">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navmenu-brand" id="titleProject" href="#"><% if (title != null) out.print(title); %></a>
    </div>
</div>