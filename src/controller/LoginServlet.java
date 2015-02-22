package controller;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import service.LoginService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet{

    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;
    static LoginService loginService;
    HttpSession httpSession;
    public List list = new ArrayList();

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
            loginService = new LoginService(ourSessionFactory.openSession());
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

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

                httpSession.setAttribute(username, list ); // Donnée de session
                out.println("<h1>Login Successful</h1>");
                response.sendRedirect("accueil.jsp");
            }
            else {
                response.sendRedirect("welcome.jsp");
                //out.println("Mauvaise combinaison Username/Mot de passe");
                //out.println("<h1 style='color:red;text-size:14px;text-align:center'>Registration Unsuccessful</h1>");
                //out.println("<META HTTP-EQUIV='Refresh' CONTENT='0;URL=welcome.jsp'>");
            }

            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    public static boolean login(String username,String password){
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
        on le redirige vers vers index.jsp*/
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
