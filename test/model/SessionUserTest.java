package model;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.ExerciseService;
import service.LoginService;
import service.SessionService;

import java.util.Date;
import java.util.List;

public class SessionUserTest {

    private boolean testDelete;
    private AUser user;
    private List<ATraining> exercises;
    private SessionUser sessionUser;

    @DataProvider(name = "create")
    public static Object[][] baseSession() {
        return new Object[][] {{"Séance de test", 2015, 03, 25}};
    }

    @BeforeMethod
    public void setUp() throws Exception {
        testDelete = false;
        user = LoginService.getUserByUsername("axel");
        exercises = ExerciseService.getExercises();
    }

    @Test(dataProvider = "create")
    public void testCreateSession(String name, int year, int month, int day) {
        sessionUser = new SessionUser(name, new Date(year, month, day));
        sessionUser = SessionService.createSession((User)user, sessionUser);
        assert (sessionUser.getIdS() != null);
    }

    @Test(dataProvider = "create")
    public void testDeleteSession(String name, int year, int month, int day) {
        sessionUser = new SessionUser(name, new Date(year, month, day));
        sessionUser = SessionService.createSession((User)user, sessionUser);

        SessionService.deleteSession(sessionUser);
        sessionUser = SessionService.getSessionById(sessionUser.getIdS());
        assert (sessionUser == null);
        testDelete = true;
    }

    @Test(dataProvider = "create")
    public void testUpdateSession(String name, int year, int month, int day) {
        sessionUser = new SessionUser(name, new Date(year, month, day));
        sessionUser = SessionService.createSession((User)user, sessionUser);

        sessionUser.setName("Titre modifié");
        SessionService.updateSession(sessionUser);

        sessionUser = SessionService.getSessionById(sessionUser.getIdS());
        assert (sessionUser.getName().equals("Titre modifié"));
    }

    @Test(dataProvider = "create")
    public void testAddExercises(String name, int year, int month, int day) {
        sessionUser = new SessionUser(name, new Date(year, month, day));
        sessionUser = SessionService.createSession((User)user, sessionUser);

        SessionService.addOrUpdateExToSession(sessionUser, exercises.get(0));
        sessionUser = SessionService.getSessionById(sessionUser.getIdS());

        assert (sessionUser.getExerciceSessions().size() > 0);
    }

   @AfterMethod
    public void tearDown() throws Exception {
        if (!testDelete)
            SessionService.deleteSession(sessionUser);
    }
}