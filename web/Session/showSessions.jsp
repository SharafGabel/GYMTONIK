<%@ page import="model.SessionUser" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="service.SessionService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<% String title = "Séance"; %>
<%@ include file="../Core/header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8 center">
            <div class="form-group" id="seanceDiv">
                <form id="formSeance" class="form-horizontal" name="formSeance" method="post" action="../SessionServlet">
                    <input type='hidden' name='action' value='createSession'/>
                    <input class="btn btn-small btn-success" type="submit" value="Créer une nouvelle séance">
                </form>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <table id="affSeance" class="table table-condensed">
                <thead>
                <tr>
                    <th>Nom Séance</th>
                    <th>Date création séance</th>
                    <th>Modifier</th>
                    <th>Supprimer</th>
                </tr>
                </thead>
                <div class="input-group mb25"> <span class="input-group-addon">Filtre</span>
                    <input id="filter" type="text" class="form-control" placeholder="Entrez un mot clé">
                </div>
                <tbody class="searchable">
                <%
                    List<SessionUser> sessionUserList = SessionService.getSessionList((User) session.getAttribute("User"));
                    for(SessionUser a:sessionUserList) {
                        out.println("<tr>");
                        out.println("\t<td>" + a.getName() + "</td>");
                        out.println("\t<td>" + a.getDateProgram() + "</td>");
                        out.println("<form id=\"updateSession\" name=\"updateSession\" method=\"post\" action=\"../Session/updateSession.jsp\">");
                        out.println("<input type='hidden' name='sessionId' value='" + a.getIdS() + "'/>");
                        out.println("<input type='hidden' name='action' value='updateSession'/>");
                        out.println("<td><button class='btn btn-small btn-primary' type='submit'>Modifier</button></td>");
                        out.println("</form>");
                        out.println("<form id=\"deleteSession\" name=\"deleteSession\" method=\"post\" action=\"../SessionServlet\">");
                        out.println("<input type='hidden' name='sessionId' value='" + a.getIdS() + "'/>");
                        out.println("<input type='hidden' name='action' value='deleteSession'/>");
                        out.println("<td><button class='btn btn-small btn-danger' type='submit'>Supprimer</button></td>");
                        out.println("</form>");
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
