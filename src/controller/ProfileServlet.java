package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        HttpSession session=request.getSession(false);        /* Récupération de la session depuis la requête */
        if(session!=null){
            String name=(String)session.getAttribute("username");//Récupération depuis la variable session
            this.getServletContext().getRequestDispatcher("index.jsp").forward( request, response );//redirection

        }
        else{
            out.print("Please login ");
            request.getRequestDispatcher("welcome.jsp").include(request, response);
        }
        out.close();
    }

}
