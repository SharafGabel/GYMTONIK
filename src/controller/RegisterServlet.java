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
        String emailVerif = request.getParameter("emailVerif");
        String password = request.getParameter("password");
        String poids = request.getParameter("weight");
        String taille = request.getParameter("height");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registration Status</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");

            if(register(username, email, password,emailVerif,poids,taille))
            {
                out.println("<h1>Registration Successful</h1>");
                response.sendRedirect("welcome.jsp");
            } else
            {
                out.println("<h1>Registration Unsuccessful</h1>");
                out.println("To try again <a href=\"welcome.jsp\">Click here</a>");
            }

            out.println("</center>");
            out.println("</body>");
            out.println("</html>");

            out.close();

    }

    public static boolean register(String username, String email, String password,String emailVerif, String poids, String taille) {

        System.out.println("YALALA");
        if ( username == null || username.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty() || emailVerif!=email ) {
            System.out.println("PB ICI SINON MARCHE ( mettre en commentaire ce if pour tester l'insertion d'un User");
            return false;
        }
         else
        {
            System.out.println("YOO");
            User user = new User(username, email, password);
            if(poids!="" && isInteger(poids))
            {
                user.setWeight(Integer.parseInt(poids));
            }
            if(taille!="" && isInteger(taille))
            {
                user.setHeight(Integer.parseInt(taille));
            }
            RegisterService registerService = new RegisterService(ourSessionFactory.openSession());
            return registerService.register(user);
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        /*L'utilisateur n'est pas censé atteindre cette page via une requête GET,
        on le redirige vers vers welcome.jsp*/
        getServletContext().getRequestDispatcher("/welcome.jsp").forward(request,response);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
