package model;

import controller.LoginServlet;
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


    @BeforeMethod
    public void setUp() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        user= (User)session.get(User.class,1);
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void testUser(){
        User user = new User("user","email","pass");
        assertEquals("Yoshi",user.getUsername());
        testGetEmail();
        testValidatePassword();
    }

    @Test
    public void testGetUsername(){
        assertEquals("Yoshi",user.getUsername());
    }

    @Test
    public void testGetEmail(){
        assertEquals("yoshi@gmail.com",user.getEmail());
    }

    @Test
    public void testValidatePassword(){
        assertFalse(user.validatePassword("wrongpass"));
        assertTrue(user.validatePassword("yoshi"));
    }
    
    @Test 
    public void testRegisterUser(){
        assertTrue(RegisterService.register(user));      
    }
    
    @Test
    public void testLoggedUser(){

       assertTrue(LoginServlet.login(user.getUsername(),"yoshi"));
        //RegisterService.register(user);
        //assertTrue(LoginService.authenticate(user.getUsername(),user.getPassword()));
    }
    
   
    
}