<%@ page import="model.Exercise" %>
<%@ page import="service.ExerciseService" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ATraining" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Exercice"; %>
<%@ include file="../Core/header.jsp" %>
<%
    List<ATraining> trainingList = ExerciseService.getExercises();
%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form id="createSession" name="createSession" method="post" action="../SessionServlet">
                <input type="hidden" name="action" value="createSessionAction" />
                <h1>Création d'une séance d'entraînement</h1>
                <label>Nom de la séance</label>
                <input type="text" class="form-control" id="sessionName" name="sessionName" placeholder="Nom de la séance"/><br/>
                <label>Date de planification</label>
                <script type="text/javascript">
                    $(function () {
                        $('#datepicker').datepicker({ dateFormat: 'dd-mm-yy' });
                    });
                </script>
                <input type='text' id='datepicker' name='datepicker' class="form-control" placeholder="Date de la session"/>
                <label class="mt25">Exercices dans la séance</label><br/>
                <div class="list-group" id="selectedExercices">
                </div>
                <label class="mt25">Autres exercices</label><br/>
                <div class="list-group" id="exercices">
                    <%
                        for(ATraining training:trainingList)
                        {
                    %>
                    <div id="<%=training.getId()%>" class="list-group item" value="<%=training.getId()%>">
                        <%=training.getName()%><%=training.getNiveau()%>
                        <input id="input<%=training.getId()%>" type="hidden" name="" value="<%=training.getId()%>"/>
                        <a class="glyphicon glyphicon-plus floatRight" onclick="change(<%=training.getId()%>)"></a>
                    </div>
                    <%

                        }
                    %>
                </div>
                <input type="submit" class="btn btn-small btn-success" value="Créer"/>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
<script language="javaScript">
    function change(divId) {
        if($("#"+divId).parent().prop('id') == "selectedExercices"){
            $("#exercices").prepend($("#"+divId));
            $("#"+divId+" > a").removeClass().addClass("glyphicon glyphicon-plus floatRight");
            $("#input"+divId).attr("name", "");
        }
        else if($("#"+divId).parent().prop('id') == "exercices"){
            $("#selectedExercices").prepend($("#"+divId));
            $("#"+divId+" > a").removeClass().addClass("glyphicon glyphicon-minus floatRight");
            $("#input"+divId).attr("name", "checkBoxTraining");
        }
    }
</script>

