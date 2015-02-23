
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link href="https://jasny.github.io/bootstrap/dist/css/jasny-bootstrap.min.css" rel="stylesheet">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- Custom styles for this template -->
    <link href="style.css" rel="stylesheet">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>

<body>

<div class="navmenu navmenu-default navmenu-fixed-left">
    <a class="navmenu-brand" href="#">Gym Tonik</a>
    <ul class="nav navmenu-nav">
        <li><a href="accueil.jsp">Home</a></li>
        <li><a href="seance.jsp">Séance</a></li>
        <li><a href="exercise.jsp">Exercice</a></li>
        <li><a href="performance.jsp">Performance</a></li>
        <li><a href="LogoutServlet">Se Déconnecter</a></li>
    </ul>
</div>

<div class="canvas">
    <div class="navbar navbar-default navbar-fixed-top">
        <center><a class="navmenu-brand" id="titleProject" href="#">Welcome to Gym Tonik ${sessionScope.username}</a></center>

    </div>
</div>

</body>
</html>
