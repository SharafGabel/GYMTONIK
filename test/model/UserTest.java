package model;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.LoginService;
import service.ProfilService;
import service.RegisterService;

public class UserTest {
    private boolean testDelete;
    private User user;

    @DataProvider(name = "create")
    public static Object[][] baseUser() {
        return new Object[][] {{"Test", "test@gymtonik.fr", "test"}};
    }

    @BeforeMethod
    public void setUp() throws Exception {
        testDelete = false;
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (!testDelete)
            ProfilService.deleteUser(user);
    }

    @Test(dataProvider = "create")
    public void testCreateUser(String username, String email, String password) {
        user = new User(username, email, password);
        RegisterService.register(user);

        user = LoginService.getUserByUsername(username);

        assert (user != null);
    }

    @Test(dataProvider = "create")
    public void testDeleteUser(String username, String email, String password) {
        user = new User(username, email, password);
        RegisterService.register(user);

        user = LoginService.getUserByUsername(username);
        ProfilService.deleteUser(user);

        user = LoginService.getUserByUsername(username);
        assert(user == null);

        testDelete = true;
    }

    @Test(dataProvider = "create")
    public void testUpdateUsername(String username, String email, String password) {
        user = new User(username, email, password);
        RegisterService.register(user);

        user = LoginService.getUserByUsername(username);
        ProfilService.changeUsername(user, "Test!");

        user = LoginService.getUserByUsername(username);
        assert (user.getUsername().equals("Test!"));
    }

    @Test(dataProvider = "create")
    public void testUpdateEmail(String username, String email, String password) {
        user = new User(username, email, password);
        RegisterService.register(user);

        user = LoginService.getUserByUsername(username);
        ProfilService.changeEmail(user, "test1@gymtonik.fr");

        user = LoginService.getUserByUsername(username);
        assert (user.getEmail().equals("test1@gymtonik.fr"));
    }

    @Test(dataProvider = "create")
    public void testUpdatePassword(String username, String email, String password) {
        user = new User(username, email, password);
        RegisterService.register(user);

        user = LoginService.getUserByUsername(username);
        ProfilService.changePassword(user, "test1");

        user = LoginService.getUserByUsername(username);
        assert (user.validatePassword("test1"));
    }
}