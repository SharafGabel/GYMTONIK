<%@ page import="model.User" %>
<%@ page import="model.ATraining" %>
<%@ page import="service.ExerciseService" %>
<%@ page import="java.util.List" %>
<%@ page import="model.AMuscle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<% String title = "Mes Exercices"; %>
<%@ include file="../Core/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
            <div class="col-md-8">
                <p class="center"><a class="btn btn-small btn-success" href="createExercise.jsp">Créer un Exercice</a></p>
                <table id="affSeance" class="table table-condensed">
                    <thead>
                        <tr>
                            <th>Nom Exercice</th>
                            <th>Durée</th>
                            <th>Nombre de répétitions</th>
                            <th>Description</th>
                            <th>Niveau</th>
                            <th>Muscles Travaillés</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <div class="input-group mb25"> <span class="input-group-addon">Filtre</span>
                        <input id="filter" type="text" class="form-control" placeholder="Entrez un mot clé">
                    </div>
                    <tbody class="searchable">
                    <%
                        User user = (User)session.getAttribute("User");
                        List<ATraining> trainingList = ExerciseService.getUserExercises(user);
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
                                out.println("\t<td>");
                                out.println("<form method=\"post\" action=\"../ExerciceServlet\">");
                                out.println("<input type=\"hidden\" name=\"action\" value=\"delete\" />");
                                out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
                                out.println("<button class=\"btn btn-small btn-danger\" type=\"submit\">Supprimer</button>");
                                out.println("</form>");

                                out.println("<form method=\"post\" action=\"../ExerciceServlet\">");
                            out.println("<input type=\"hidden\" name=\"action\" value=\"redirectUpdate\" />");
                                out.println("<input type=\"hidden\" name=\"idEx\" value=\"" + t.getId() + "\" />");
                                out.println("<button class=\"btn btn-small btn-primary\" type=\"submit\">Modifier</button>");
                                out.println("</form>");
                                
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

