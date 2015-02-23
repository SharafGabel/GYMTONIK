package controller;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.LoginService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        PrintWriter out = response.getWriter();
        String username= request.getParameter("username");
        String password = request.getParameter("password");

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registration Success</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");

            if (login(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("User",LoginService.getUserByUsername(username));
                session.setAttribute("username",username);
                out.println("<h1>Login Successful</h1>");
                response.sendRedirect("accueil.jsp");
            }
            else {
                request.getRequestDispatcher("welcome.jsp").include(request, response);
                out.println("Mauvaise combinaison Username/Mot de passe");
            }

            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    public static boolean login(String username,String password){
        if( username.equals(null) || username.trim().isEmpty() ||  password.equals(null) || password.trim().isEmpty())
        {
            return false;
        }else {
            return LoginService.authenticate(username, password);
        }

    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        /*L'utilisateur n'est pas censé atteindre cette page via une requête GET,
        on le redirige vers vers index.jsp*/
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
