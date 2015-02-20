package controller;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import service.RegisterService;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kuga  on 15/02/2015.
 */
public class RegisterServlet extends HttpServlet {

    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        PrintWriter out = response.getWriter();
        String username= request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password1");

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
            RegisterService registerService = new RegisterService(ourSessionFactory.openSession());
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
