import model.*;
//import model.Session;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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
        Integer exerciseId = null;


        Exercise exercise = new Exercise();
        exercise.setName("Abdos");
        //exercise.setIdExercise(1);
        exercise.setExplanation("travail les abdominaux");
        exercise.setLength(10);
        exerciseId=(Integer)session.save(exercise);
        session.getTransaction().commit();



        /**Création d'une séance d'entrainement**/
        /*
        SessionUser session1 = new SessionUser(500);
        session1.setDateProgram(Date.from(Instant.now()));
        session1.setPerform(true);
        session1.setTrainings(new ArrayList<ITraining>(500));
        session.save(session1);
        */
        /**Fin création de la séance d'entrainement**/


    }
}
