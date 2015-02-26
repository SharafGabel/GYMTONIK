import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import service.GetList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        int weight = 200, height = 9000;
        Transaction tx = session.beginTransaction();
        /**Création d'un User**/
         User user = new User();
         user.setUsername("JB TTP1");
         user.setHeight(height);
         user.setWeight(weight);
         user.setEmail("TarteAuPion1@gmail.com");
         user.setPassword("TarteAuPion1");
         /**FIN création user**/

        /*Création d'une séance*/
        SessionUser sessionUser = new SessionUser(5);
        sessionUser.setUser(user);
        sessionUser.setDateProgram(new Date());

        /*Fin création d'une séance*/

        /**Création d'un muscle */
        AMuscle muscle = new Muscle();
        muscle.setName("Triceps");
        /** Fin de la création d'un muscle */

        /**Création d'un exercice */
        ATraining exercise = new Exercise();
        exercise.setName("Mollet de l'extreme");
        exercise.setExplanation("Travail les mollets de l'extreme");
        exercise.setLength(10);
        List<ATraining> exerciseList = new ArrayList<ATraining>();
        exerciseList.add(exercise);
        sessionUser.setTrainings(exerciseList);
        exercise.addBodyPart(muscle);
        /**Fin de la création d'un exercice*/

        /*Création d'une performance*/
        Performance performance = new Performance();
        performance.setName("Performance1");
        performance.setSession(sessionUser);
        /**Fin création performance**/

        session.saveOrUpdate(user);

        tx.commit();
    }
}
