package model;

import controller.LoginServlet;
import controller.RegisterServlet;
import org.hibernate.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.LoginService;
import service.RegisterService;
import util.HibernateUtil;

import static org.testng.Assert.*;

public class UserTest {
    User user;
    User userNoDb;

    @BeforeMethod
    public void setUp() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        user= (User)session.get(User.class,2);
        userNoDb = new User("jean","jean@gmail.com","jean");
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void testUser(){
        User user = new User("jean","jean@gmail.com","jean");
        assertEquals("jean",user.getUsername());
        testGetEmail();
        testValidatePassword();
    }

    @Test
    public void testGetUsername(){
        assertEquals("kuga",user.getUsername());
    }

    @Test
    public void testGetEmail(){
        assertEquals("kuga@gmail.com",user.getEmail());
    }

    @Test
    public void testValidatePassword(){
        assertFalse(user.validatePassword("wrongpass"));
        assertTrue(user.validatePassword("kuga"));
    }
    
    @Test 
    public void testRegisterUser(){
        //assertTrue(RegisterService.register(user));
        assertTrue(RegisterServlet.register(userNoDb.getUsername(),userNoDb.getEmail(),userNoDb.getPassword(),userNoDb.getEmail(),"175","50"));
    }
    
    @Test
    public void testLoggedUser(){

       assertTrue(LoginServlet.login(user.getUsername(),"kuga"));
        //RegisterService.register(user);
        //assertTrue(LoginService.authenticate(user.getUsername(),user.getPassword()));
    }

    @Test
    public void testUserExist(){
        assertFalse(RegisterServlet.register(user.getUsername(),user.getEmail(),user.getPassword(),user.getEmail(),"170","100"));
    }
    
   
    
}