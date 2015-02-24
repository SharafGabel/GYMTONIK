<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Si aucune session n'est créée, rediriger vers page de login / inscription
    if (session.getAttribute("User") == null) {
        response.sendRedirect("welcome.jsp");
    }
%>
<% String title = "Accueil"; %>
<%@ include file="header.jsp" %>
<p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut a laoreet nisi. Duis id risus lacus. Vivamus molestie felis diam, eu pharetra tellus tempor ut. Fusce sit amet placerat sapien. Nulla porttitor neque et nulla finibus rhoncus. Duis mattis tempor ligula, a vulputate nisl luctus sit amet. Etiam maximus sed ligula eget suscipit.</p>
<%@ include file="footer.jsp" %>
