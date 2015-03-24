<%@ page import="model.User" %>
<%@ page import="model.ATraining" %>
<%@ page import="java.util.List" %>
<%@ page import="service.ExerciseService" %>
<%@ page import="model.AMuscle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String title = "Exercises des utilisateurs"; %>
<%@ include file="../Core/header.jsp" %>
<%
    if (session.getAttribute("User") == null) {
        response.sendRedirect("../welcome.jsp");
    }

%>

<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <p><a class="btn btn-small btn-warning" href="createExercise.jsp">Créer un Exercice</a></p>
            <table id="affSeance" class="table table-condensed">
                <thead>
                <tr>
                    <th>Nom Exercice</th>
                    <th>Durée</th>
                    <th>Nombre de répétitions</th>
                    <th>Description</th>
                    <th>Niveau</th>
                    <th>Muscles Travaillés</th>
                </tr>
                </thead>
                <div class="input-group"> <span class="input-group-addon">Filtrer</span>

                    <input id="filter" type="text" class="form-control" placeholder="Type here...">
                </div>
                <tbody class="searchable">
                <%
                    User user = (User)session.getAttribute("User");
                    List<ATraining> trainingList = ExerciseService.getExercisesNotOfThis(user);
                    for (ATraining t : trainingList)
                    {
                        out.println("<tr>");
                        out.println("\t<td>" + t.getName() + "</td>");
                        out.println("\t<td>" + t.getDureeExo() + "</td>");
                        out.println("\t<td>" + t.getNbRepetition() + "</td>");
                        out.println("\t<td>" + t.getExplanation() + "</td>");
                        out.println("\t<td>" + t.getNiveau() + "</td>");
                        System.out.println(t.getBodyParts().size());
                        System.out.println(t.getBodyParts().toString());
                        out.println("<td>");
                        if(t.getBodyParts().size()!=0) {

                            int taille = 0;
                            for (AMuscle muscle : t.getBodyParts()) {
                                taille++;
                                if (t.getBodyParts().size() != taille)
                                    out.print(muscle.getName() + ",");
                                else
                                    out.print(muscle.getName());
                            }


                        }
                        out.println("</td>");
                        out.println("</tr>");


                    }

                %>
                </tbody>
            </table>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<script>
    $(document).ready(function () {

        (function ($) {

            $('#filter').keyup(function () {

                var rex = new RegExp($(this).val(), 'i');
                $('.searchable tr').hide();
                $('.searchable tr').filter(function () {
                    return rex.test($(this).text());
                }).show();

            })

        }(jQuery));

    });

</script>
<%@ include file="../footer.jsp" %>