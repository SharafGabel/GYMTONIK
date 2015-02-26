package model;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserTest {
    User user;


    @BeforeMethod
    public void setUp() throws Exception {
        user = new User("user","email","pass");
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }

    @Test
    public void testUser(){
        User user = new User("user","email","pass");
        assertEquals("user",user.getUsername());
        assertEquals("email",user.getEmail());
        assertTrue(user.validatePassword("pass"));
    }

    @Test
    public void testGetUsername(){
        assertEquals("user",user.getUsername());
    }

    @Test
    public void testGetEmail(){
        assertEquals("email",user.getEmail());
    }

    @Test
    public void testValidatePassword(){
        assertFalse(user.validatePassword("wrongpass"));
        assertTrue(user.validatePassword("pass"));
    }
}