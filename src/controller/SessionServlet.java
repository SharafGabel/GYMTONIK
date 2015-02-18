package controller;

import model.IUser;
import model.User;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kukugath on 18/02/2015.
 */
public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
     
       
    public boolean addSession(User user){
        if(user == null){
            return false;
        }
        SessionService sessionService = new SessionService();
        sessionService.addSession(user);
        return true;
        
       
    }
    
}
