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
        userNoDb = new User("sharaf","sharaf@gmail.com","sharaf");
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void testUser(){
        User user = new User("sharaf","sharaf@gmail.com","sharaf");
        assertEquals("sharaf",user.getUsername());
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
        //assertTrue(RegisterService.register(user));
        //Attention : avant chaque test modifier les champs de l'user (car il peut etre déja présent dans la base)
        assertTrue(RegisterServlet.register(userNoDb.getUsername(),userNoDb.getEmail(),userNoDb.getPassword(),userNoDb.getEmail(),"175","50"));
    }
    

    @Test
    public void testUserExist(){
        assertFalse(RegisterServlet.register(user.getUsername(),user.getEmail(),user.getPassword(),user.getEmail(),"170","100"));
    }
    
   
    
}