package controller;

import model.SessionUser;
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
    SessionService sessionService = new SessionService();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
     
       
    public boolean addSession(User user){
        if(user == null){
            return false;
        }
        sessionService.addSession(user);
        return true;
    }


    public boolean deleteSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }
        sessionService.deleteSession(sessionUser);
        return true;
    }

    public boolean updateSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }
        sessionService.updateSession(sessionUser);
        return true;
    }
    
    
    
}
