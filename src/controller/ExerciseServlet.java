package controller;

import model.Exercise;
import model.SessionUser;
import model.User;
import service.ExerciseService;
import service.GetList;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExerciseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String length= request.getParameter("duree");
        String nameExercise= request.getParameter("nomEx");
        String description = request.getParameter("descriptionEx");
        String sessionUserId = request.getParameter("sessionUser");
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");

            SessionUser sessionUsers = GetList. getSessionById(Integer.parseInt(sessionUserId));

             if (ExerciseService.addExercise(sessionUsers, length, nameExercise, description)) {
                out.println("<h1>Création de l'exercice réussi</h1>");
            }
            else {
                 out.println("<h1>Création de l'exercise  échouée<h1>");
            }
            request.getRequestDispatcher("exercise.jsp").include(request, response);
            out.println("<p>"+sessionUserId+"</p>");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    /*public boolean addExercise(SessionUser sessionUser){
        if(sessionUser==null || sessionUser.getUser() == null){
            return false;
        }
        ExerciseService.addExercise(sessionUser);
        return true;
    }*/

    public boolean deleteSession(Exercise exercise){
        if(exercise == null){
            return false;
        }
        ExerciseService.deleteExercise(exercise);
        return true;
    }

    public boolean updateSession(Exercise exercise){
        if(exercise == null){
            return false;
        }
        ExerciseService.updateExercise(exercise);
        return true;
    }
    
    
}
