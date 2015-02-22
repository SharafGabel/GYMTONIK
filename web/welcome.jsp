
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Login Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/supersized.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

</head>
    <title></title>
</head>
<body id="loginPage" name="loginPage">
<div class="page-container">

    <form id="formLogin" name="formLogin" method="post" action="LoginServlet">
        <h1>Sign in</h1>
        <label>Username</label>
        <input type="text" name="username" id="username" />

        <label>Password</label>
        <input type="password" name="password" id="password" />

        <button type="submit">Log in</button>
    </form>

    <form id="formRegister" name="formRegister" method="post" action="RegisterServlet">
        <h1>S'inscrire</h1>

        <label>Username*</label>
        <input type="text" name="username" id="user" />

        <label>E-Mail*</label>
        <input type="text" name="email" id="email" />

        <label>Confirmation E-Mail*</label>
        <input type="text" name="emailVerif" id="emailVerif" />

        <label>Password*</label>
        <input type="password" name="password" id="pass" />

        <label>Hauteur</label>
        <input type="text" name="height" id="height" />

        <label>Poids</label>
        <input type="text" name="weight" id="weight" />

        <button type="submit">Register</button>

        <label>* (Obligatoire)</label>

    </form>
</div>
<!-- Javascript -->
<script src="assets/js/jquery-1.8.2.min.js"></script>
<script src="assets/js/supersized.3.2.7.min.js"></script>
<script src="assets/js/supersized-init.js"></script>
<script src="assets/js/scripts.js"></script>


</body>
</html>
