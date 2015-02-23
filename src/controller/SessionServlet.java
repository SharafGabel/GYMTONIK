package controller;

import model.SessionUser;
import model.User;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
       
    public boolean addSession(User user){
        if(user.equals(null)){
            return false;
        }
        SessionService.addSession(user);
        return true;
    }

    public boolean deleteSession(SessionUser sessionUser){
        if(sessionUser.equals(null)){
            return false;
        }
       SessionService.deleteSession(sessionUser);
        return true;
    }

    public boolean updateSession(SessionUser sessionUser){
        if(sessionUser.equals(null)){
            return false;
        }
        SessionService.updateSession(sessionUser);
        return true;
    }

}
