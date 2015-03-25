<%@ page import="model.ATraining" %>
<%@ page import="model.SessionUser" %>
<%@ page import="model.ExerciceSession" %>
<%@ page import="service.ExerciseService" %>
<%@ page import="service.SessionService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Exercice"; %>
<%@ include file="../Core/header.jsp" %>

<%
    List<ATraining> trainingList = ExerciseService.getExercises();
    String sessionId = request.getParameter("sessionId");
    SessionUser sessionUser = SessionService.getSessionById(Integer.parseInt(sessionId));

    /*  Dans le cas où l'utilisateur s'amuserait à appeler cette page
        avec le mauvais identifiant ou sans identifiant */
    if (sessionUser == null) {
        response.sendRedirect("showSessions.jsp");
    } else {
%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form id="sessionForm" name="sessionForm" method="post" action="../SessionServlet">
                <input type="hidden" name="action" value="updateSessionAction" />
                <input type="hidden" name="sessionId" value="<%= sessionId %>" />
                <h1>Modifier une séance</h1>
                <label>Nom de la séance</label>
                <input type="text" class="form-control" id="sessionName" name="sessionName" value="<%=sessionUser.getName()%>"/><br/>
                <label>Date de planification</label>
                <script type="text/javascript">
                    $(function () {
                        $('#datepicker').datepicker({ dateFormat: 'dd-mm-yy' });
                    });
                </script>
                <input type='text' id='datepicker' name='datepicker' class="form-control" value="<%=sessionUser.getDateProgram()%>"/>
                <label class="mt25">Exercices dans la séance</label><br/>
                <div class="list-group" id="selectedExercices">
                    <% for(ATraining training:trainingList) {
                          for(ExerciceSession es:sessionUser.getExerciceSessions()){
                              if(es.getTraining().getId() == training.getId()){
                    %>
                                  <div id="<%=training.getId()%>" class="list-group item" value="<%=training.getId()%>"><%=training.getName()%><%=training.getNiveau()%><%=training.getNiveau()%>
                                      <input id="input<%=training.getId()%>" type="hidden" name="checkBoxTraining" value="<%=training.getId()%>"/>
                                      <a class="glyphicon glyphicon-minus floatRight" onclick="change(<%=training.getId()%>)"></a>
                                  </div>
                    <%
                              }
                          }
                      }
                    %>
                </div>
                <label class="mt25">Autres exercices</label><br/>
                <div class="list-group" id="exercices">
                    <% for(ATraining training:trainingList) {
                        boolean done = false;
                          for(ExerciceSession es:sessionUser.getExerciceSessions()){
                              if(es.getTraining().getId() == training.getId()){
                                  done = true;
                              }
                          }
                        if(!done){
                    %>
                            <div id="<%=training.getId()%>" class="list-group item" value="<%=training.getId()%>"><%=training.getName()%><%=training.getNiveau()%>
                                <input id="input<%=training.getId()%>" type="hidden" name="" value="<%=training.getId()%>"/>
                                <a class="glyphicon glyphicon-plus floatRight" onclick="change(<%=training.getId()%>)"></a>
                            </div>
                    <%
                        }
                    }
                    %>
                </div>
                <input type="submit" class="btn btn-small btn-warning" value="Modifier"/>
            </form>
        </div>
    </div>
</div>
<%
    } //else
%>
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
