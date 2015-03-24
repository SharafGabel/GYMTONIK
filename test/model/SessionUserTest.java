package model;

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

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class SessionUserTest {

    AMuscle m1;
    AMuscle m2;
    AMuscle m3;
    List<AMuscle> select;
    List<ATraining> exerciseList;
    SessionUser sessionUser;
    User user;
    int niveau;
    int nbExo;

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
        m1 = (AMuscle)session.get(AMuscle.class,1);// on récupère le muscle trapeze
        m2 = (AMuscle)session.get(AMuscle.class,2);// on récupère le muscle deltoide
        m3 = (AMuscle)session.get(AMuscle.class,7);// on récupère le muscle deltoide

        user= (User)session.get(User.class,1);
        select = new ArrayList<AMuscle>();
        exerciseList = new ArrayList<ATraining>();
        select.add(m1);
        select.add(m2);
        select.add(m3);
        niveau = 2;
        nbExo = 3;

    }

   @Test
   public void testListMuscle(){
       assertFalse(select.isEmpty()); // on test si la liste des muscles est vide
   }
/*
    @Test
    public void testGetExercicesFromMuscle(){
        assertEquals(exerciseList,ExerciseService.getExercisesFromMuscles(select, niveau));
    }

    @Test
    public void testAjoutSeance(){
        assertEquals(sessionUser,SessionService.addSessionUser(user));
    }
*/
    @Test
    public void testGenerateSeance(){
            exerciseList = ExerciseService.getExercisesFromMuscles(select, niveau);
            sessionUser = SessionService.addSessionUser(user);

             if (nbExo > exerciseList.size())
                nbExo = exerciseList.size();
             for (int i = 0; i < nbExo; i++) {
               assertTrue(SessionService.addOrUpdateExToSession(sessionUser,exerciseList.get(i)));
             }
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }
}