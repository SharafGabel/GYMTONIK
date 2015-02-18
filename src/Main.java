import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Date;
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
        Transaction tx = session.beginTransaction();
        /**Création d'un User
         User user = new User();
         user.setUsername("JB TTP");
         user.setHeight(height);
         user.setWeight(weight);
         user.setEmail("TarteAuPion@gmail.com");
         user.setPassword("TarteAuPion");
         userId=(Integer)session.save(user);
         FIN création user**/


        /*Création d'une séance*/
        SessionUser sessionUser = new SessionUser(5);
        sessionUser.setDateProgram(new Date());

        /*Fin création d'une séance*/

        /**Création d'un muscle */
        AMuscle muscle = new Muscle();
        muscle.setName("biceps");
        muscle.setDescription("travail partie haute des biceps");

        /** Fin de la création d'un muscle */

        /**Création d'un exercice */
        Exercise exercise = new Exercise();
        exercise.setName("abdos");
        exercise.setExplanation("travaille les abdominaux");
        exercise.setLength(10);
        List<AMuscle> muscles = new ArrayList<AMuscle>();
        List<ATraining> exercises = new ArrayList<ATraining>();
        exercises.add(exercise);
        muscles.add(muscle);
        exercise.setBodyPart(muscles);
        sessionUser.setTrainings(exercises);
        /**Fin de la création d'un exercice*/

        /*Création d'une performance*/
        Performance performance = new Performance();
        performance.setName("Performance1");
        sessionUser.setPerformance(performance);
        AUser user =  (User) session.get(User.class, 1);
        user.addSession(sessionUser);
        /**Fin création performance**/
        session.save(user);

        tx.commit();
    }
}
