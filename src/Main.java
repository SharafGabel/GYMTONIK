import controller.LoginServlet;
import controller.SessionServlet;
import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import service.LoginService;
import service.RegisterService;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kukugath on 11/02/2015.
 */
public class Main {
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

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        System.out.println("Hibernate component mapping");

        final Session session = getSession();
        int exerciseId;
        int userId;
        int seanceId;
        int muscleId;
        int weight = 200, height = 9000;

        /*User didier = new User("Didier", "didier@gmail.com", "didier!");
        RegisterService rs = new RegisterService(getSession());
        System.out.println("Utilisateur créé : " + rs.register(didier));
        System.out.println(didier.getPassword());*/

        LoginService ls = new LoginService(getSession());
        System.out.println(ls.authenticate("Didier", "didier!"));

    }
}
