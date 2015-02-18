package controller;

import model.User;
import service.RegisterService;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kuga  on 15/02/2015.
 */
public class RegisterServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        PrintWriter out = response.getWriter();
        String username= request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registration Success</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");

            if (register(username, email, password)) {
                out.println("<h1>Registration Successful</h1>");
                out.println("To login with your username and Password<a href=\"login.jsp\">Click here</a>");
            } else {
                out.println("<h1>Registration Unsuccessful</h1>");
                out.println("To try again <a href=\"register.jsp\">Click here</a>");
            }

            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    public boolean register(String username, String email, String password) {
        if ( username == null || username.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty())
        {
            return false;
        } else {
            User user = new User(username, email, password);
            RegisterService registerService = new RegisterService();
            return registerService.register(user);
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        /*L'utilisateur n'est pas censé atteindre cette page via une requête GET,
        on le redirige vers vers register.jsp*/
        getServletContext().getRequestDispatcher("/register.jsp").forward(request,response);
    }
}
