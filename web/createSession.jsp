<%@ page import="model.Exercise" %>
<%@ page import="service.ExerciseService" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ATraining" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Exercice"; %>
<%@ include file="header.jsp" %>
<%
    List<ATraining> trainingList = ExerciseService.getExercises();
%>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form id="createSession" name="createSession" method="post" action="SessionServlet">
                <h1>Création d'une séance d'entraînement</h1>
                <label>Nom de la séance</label>
                <input type="text" id="sessionName"/><br/>
                <label>Date de planification</label>
                <input type="date" id="sessionProgram"/><br/>
                <%
                    for(ATraining training:trainingList)
                    {
                %>
                <input class="checkbox" type="checkbox" name="checkBoxTrainging<%=training.getId()%>" value="<%=training.getId()%>"><%=training.getName()%><%=training.getNiveau()%>
                <%  }%>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
