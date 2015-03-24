<%@ page import="model.ATraining" %>
<%@ page import="model.SessionUser" %>
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
<div class="page-container" id="exerciseDiv">

    <form id="sessionForm" name="formEx" method="post" action="SessionServlet">
        <h1>Modifier une séance</h1>
        <h1>Création d'une séance d'entraînement</h1>
        <label>Nom de la séance</label>
        <input type="text" class="form-control" id="sessionName" name="sessionName"/><br/>
        <label>Date de planification</label>
        <script type="text/javascript">
            $(function () {
                $('#datepicker').datepicker({ dateFormat: 'dd-mm-yy' });
            });
        </script>
        <input type='text' id='datepicker' name='datepicker' class="form-control" />
        <%
            for(ATraining training:trainingList)
            {
        %>
        <input class="checkbox" type="checkbox" name="checkBoxTraining" value="<%=training.getId()%>"><%=training.getName()%><%=training.getNiveau()%>
        <%  }%>
        <input type="submit" class="btn btn-small btn-warning" value="Modifier"/>
    </form>
</div>
<%
    } //else
%>
