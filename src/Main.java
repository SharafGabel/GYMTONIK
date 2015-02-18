import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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
        int weight = 120, height = 150;
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setUsername("Jean Baptiste Tartaupion");
        user.setUsername_canonical("Jean Baptiste Tartaupion");
        user.setHeight(height);
        user.setWeight(weight);
        user.setEmail("jbtartaupion@gmail.com");
        user.setEmail_canonical("jbtartaupion@gmail.com");
        user.setPassword("jbtartaupion");
        userId=(Integer)session.save(user);
        /**FIN création user**/
        tx.commit();
        user =  (User) session.get(User.class, 12);
        System.out.println(user.getUsername());

    }
}
