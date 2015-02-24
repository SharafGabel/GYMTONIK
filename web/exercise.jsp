<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="index.jsp" %>
<html>
<head>
    <title>Exercice</title>
</head>
<body>
<div class="page-container" id="exerciseDiv">
    <form id="formEx" name="formEx" method="post" action="ExerciceServlet">
        <h1>Créer un Exercice</h1>

        <p><label class="lab">Nom de l'exercice</label></p>
        <input type="text" name="nomEx" id="nomEx" />

        <p><label class="lab">Durée (en min)</label></p>
        <input type="number" min="1" max="120" name="duree" id="duree" />

        <p><label class="lab">Description de l'exercice</label></p>
        <input type="text" name="descriptionEx" id="descriptionEx" />

        <p><button type="submit">Enregistrer l'exercice</button></p>
    </form>

</div>

</body>
</html>
