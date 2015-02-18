package controller;

import model.Exercise;
import model.SessionUser;
import model.User;
import service.ExerciseService;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kukugath on 18/02/2015.
 */
public class ExerciseServlet extends HttpServlet {
    ExerciseService exerciseService = new ExerciseService();
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    public boolean addExercise(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }
        exerciseService.addExercise(sessionUser);
        return true;
    }

    public boolean deleteSession(Exercise exercise){
        if(exercise == null){
            return false;
        }
        exerciseService.deleteExercise(exercise);
        return true;
    }

    public boolean updateSession(Exercise exercise){
        if(exercise == null){
            return false;
        }
        exerciseService.updateExercise(exercise);
        return true;
    }
    
    
}
