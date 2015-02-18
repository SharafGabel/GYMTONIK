package controller;

import service.LoginService;
import service.SessionService;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by axel on 18/02/15.
 */
public class LoginServlet extends HttpServlet{

    LoginService loginService = new LoginService();

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
                out.println("<h1>Login Successful</h1>");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
            } else {
                out.println("<h1>Registration Unsuccessful</h1>");
                out.println("To try again <a href=\"index.jsp\">Click here</a>");
            }

            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    public boolean login(String username,String password){
        if( username == null || username.trim().isEmpty() ||  password == null || password.trim().isEmpty())
        {
            return false;
        }else {
            return loginService.authenticate(username, password);
        }

    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        /*L'utilisateur n'est pas censé atteindre cette page via une requête GET,
        on le redirige vers vers register.jsp*/
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
