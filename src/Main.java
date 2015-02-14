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
        Integer exerciseId = null;
        Integer userId = null;
        Integer seanceId = null;
        Integer muscleId = null;

        Transaction tx = session.beginTransaction();
        /***ETAPE 1 : Créer le .hbm pour User**/
        /**ETAPE 2 : IL FAUT CREER DEJA UN USER, pour pouvoir ensuite créer une séance ( sessionuser ), à cause des clés étrangère qui ne peut pas être nulle**/
        User user = new User();
        //user.setUser_id(1);
        user.setUsername("Jean Baptiste Tartaupion");
        user.setUsername_canonical("Jean Baptiste Tartaupion");
        user.setHeight(173);
        user.setWeight(120);
        user.setEmail("jbtartaupion@gmail.com");
        user.setEmail_canonical("jbtartaupion@gmail.com");
        user.setPassword("jbtartaupion");
        /**FIN création user**/
        Exercise exercise = new Exercise();
        exercise.setName("Abdos");
        exercise.setIdExercise(1);
        exercise.setExplanation("travail les abdominaux");
        exercise.setLength(10);
        List<IBodyPart> muscleL = new ArrayList<IBodyPart>();
        AMuscle muscle = new Muscle();
        muscle.setName("PECTORAUX");
        muscle.setDescription("PECTORAUX DU BAS");
        muscle.setIdMuscle(1);
        muscleL.add(muscle);
        exercise.setBodyPart(muscleL);
        SessionUser seance = new SessionUser();
        seance.setIdSession(1);
        seance.setPerform(false);
        seance.setTimeSleep(18);
        seance.addTraining(exercise);
        user.setSession(seance);

        userId=(Integer)session.save(user);
        seanceId=(Integer)session.save(seance);
        exerciseId = (Integer)session.save(exercise);/**idExercise doesn't have a default value**/
        muscleId = (Integer)session.save(muscle);/**idSession doesn't have a default value**/
        tx.commit();

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
