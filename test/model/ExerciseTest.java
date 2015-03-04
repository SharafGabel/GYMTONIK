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
        exercise = new Exercise(user,50,"travail abdomen","travail les abdominaux");
        exerciseRecup = (Exercise)session.get(Exercise.class,2);
        session.close();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddExercise(){
        SessionUser sessionUsers = SessionService.getSessionById(sessionUser.getIdS());
        assertTrue(ExerciseServlet.addExercise(user, sessionUsers, "50", exercise.getName(), exercise.getExplanation()));

    }

    @Test
    public void testDeleteExercise(){
        assertTrue(ExerciseService.deleteExercise(user,exerciseRecup));
    }

    @Test
    public void testUpdateExercise(){

        exercise.setExplanation("travail les abdominaux et les pectoraux ");
        exercise.setLength(40);
        exercise.setName("exercise  abdominaux intensif");
        assertTrue(ExerciseServlet.updateExercise(user,exercise));  //a rectifier ,il ne prend pas en compte l'id
    }
}