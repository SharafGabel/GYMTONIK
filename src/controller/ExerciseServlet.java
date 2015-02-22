package controller;

import model.Exercise;
import model.SessionUser;
import service.ExerciseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExerciseServlet extends HttpServlet {
    ExerciseService exerciseService = new ExerciseService();
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    public boolean addExercise(SessionUser sessionUser){
        if(sessionUser==null || sessionUser.getUser() == null){
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
