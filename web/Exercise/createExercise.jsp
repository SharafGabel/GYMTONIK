<%@ page import="java.util.List" %>
<%@ page import="model.AMuscle" %>
<%@ page import="service.MuscleService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<% String title = "Creation d'exercice"; %>
<%@ include file="../Core/header.jsp" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form id="formEx" class="form-horizontal" name="formEx" method="post" action="../ExerciceServlet">
                <h1>Créer un Exercice</h1>

                <!-- Champ caché afin d'indiquer au servlet l'action qu'on souhaite réaliser,
                ici ajouter un nouvel exercice -->
                <input type="hidden" name="action" value="add" />

                <p><label>Nom de l'exercice</label></p>
                <input class="form-control" type="text" name="nomEx" id="nomEx" />

                <p><label>Durée (en min)</label></p>
                <input class="form-control" type="number" min="1" max="120" name="duree" id="duree" />

                <p><label>Nombre de répétitions </label></p>
                <input class="form-control" type="number" min="5" max="500" name="nbRepet" id="nbRepet" />

                <p><label>Description de l'exercice</label></p>
                <input class="form-control" type="text" name="descriptionEx" id="descriptionEx" />

                <p><label>Niveau de l'exercice</label></p>
                <input class="form-control" type="number" min="1" max="3" name="niveau" id="niveau" />
                <label class="mt25">Muscles choisis</label><br/>
                <div class="list-group" id="selectMuscles">
                </div>
                <label class="mt25">Autres muscles</label><br/>
                <div class="list-group" id="othersMuscles">
                    <%
                        List<AMuscle> aMuscles = MuscleService.getAllMuscles();
                        for(AMuscle a:aMuscles)
                        {
                    %>
                    <div id="<%=a.getId()%>" class="list-group item" value="<%=a.getId()%>">
                        <%=a.getName()%>
                        <input id="input<%=a.getId()%>" type="hidden" name="" value="<%=a.getId()%>"/>
                        <a class="glyphicon glyphicon-plus floatRight" onclick="change(<%=a.getId()%>)"></a>
                    </div>
                    <%

                        }
                    %>
                </div>
                <p class="center"><button class="btn btn-small btn-success" type="submit">Enregistrer l'exercice</button></p>

            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
    
</div>
<script language="javaScript">
    function change(divId) {
        if($("#"+divId).parent().prop('id') == "selectMuscles"){
            $("#othersMuscles").prepend($("#"+divId));
            $("#"+divId+" > a").removeClass().addClass("glyphicon glyphicon-plus floatRight");
            $("#input"+divId).attr("name", "");
        }
        else if($("#"+divId).parent().prop('id') == "othersMuscles"){
            $("#selectMuscles").prepend($("#"+divId));
            $("#"+divId+" > a").removeClass().addClass("glyphicon glyphicon-minus floatRight");
            $("#input"+divId).attr("name", "inlineCheckboxMuscle");
        }
    }
</script>