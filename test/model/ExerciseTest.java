package model;

import controller.ExerciseServlet;
import org.hibernate.Session;
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

    @BeforeMethod
    public void setUp() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        user = (User)session.get(User.class,2);
        sessionUser= (SessionUser)session.get(SessionUser.class,2);
        exercise = new Exercise(user,50,"travail abdomen","travail les abdominaux");
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
        assertTrue(ExerciseServlet.deleteExercise(user,exercise));
    }

    @Test
    public void testUpdateExercise(){

        exercise.setExplanation("travail les abdominaux et les pectoraux ");
        exercise.setLength(40);
        exercise.setName("exercise  abdominaux intensif");
        assertTrue(ExerciseServlet.updateExercise(user,exercise));  //a rectifier ,il ne prend pas en compte l'id
    }
}