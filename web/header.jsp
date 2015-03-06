<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><% if (title != null) out.print(title); %></title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link href="https://jasny.github.io/bootstrap/dist/css/jasny-bootstrap.min.css" rel="stylesheet">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- CSS -->
    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/supersized.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <!-- Custom styles for this template -->
    <link href="style.css" rel="stylesheet">

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

                    if(response!=null){
                        $("#exerciseTable").find("tr:gt(0)").remove();
                        var table1 = $("#exerciseTable");
                        $.each(response, function(key,value) {
                            var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                            rowNew.children().eq(0).text(value['code']);
                            rowNew.children().eq(1).text(value['name']);
                            rowNew.children().eq(2).text(value['continent']);
                            rowNew.children().eq(3).text(value['region']);
                            rowNew.children().eq(4).text(value['population']);
                            rowNew.children().eq(5).text(value['capital']);
                            rowNew.appendTo(table1);
                        });
                    }
                });
                $("#tablediv").show();

            });
        });
    </script>
<!--
    <script type="text/javascript">
        $(document).ready(function() {
            $("#tablediv").hide();
            $("#showTable").click(function(event){
                $.get('HistoriqueServlet',function(responseJson) {
                    if(responseJson!=null){
                        $("#exerciseTable").find("tr:gt(0)").remove();
                        var table1 = $("#exerciseTable");
                        $.each(responseJson, function(key,value) {
                            var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                            rowNew.children().eq(0).text(value['code']);
                            rowNew.children().eq(1).text(value['name']);
                            rowNew.children().eq(2).text(value['continent']);
                            rowNew.children().eq(3).text(value['region']);
                            rowNew.children().eq(4).text(value['population']);
                            rowNew.children().eq(5).text(value['capital']);
                            rowNew.appendTo(table1);
                        });
                    }
                });
                $("#tablediv").show();
            });
        });
    </script>-->

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
