package model;

import controller.ExerciseServlet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.ExerciseService;
import service.SessionService;
import util.HibernateUtil;

import static org.testng.Assert.*;

public class ExerciseTest {

    SessionUser sessionUser;
    Exercise exercise;
    User user;
    Exercise exerciseRecup;

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

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    @BeforeMethod
    public void setUp() throws Exception {

        Session session = getSession();
        user = (User)session.get(User.class,2);
        sessionUser= (SessionUser)session.get(SessionUser.class,2);
        exercise = new Exercise(user,50,7,"travail abdomen","travail les abdominaux",1);
        exerciseRecup = (Exercise)session.get(Exercise.class,4);
        session.close();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddExercise(){
        SessionUser sessionUsers = SessionService.getSessionById(sessionUser.getIdS());
        assertTrue(ExerciseService.addExercise(user, sessionUsers, "50","7", exercise.getName(), exercise.getExplanation(),exercise.getNiveau()));

    }

    @Test
    public void testDeleteExercise(){
        assertTrue(ExerciseService.deleteExercise(user,exerciseRecup));
    }

    @Test
    public void testUpdateExercise(){

        exerciseRecup.setExplanation("travail les abdominaux et les pectoraux ");
        exerciseRecup.setDureeExo(40);
        exerciseRecup.setNbRepetition(5);
        exerciseRecup.setName("exercise  abdominaux intensif");
        assertTrue(ExerciseService.updateExercise(user,exerciseRecup));
    }
}