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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ExerciseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String nameExercise= request.getParameter("nomEx");
        String length= request.getParameter("duree");
        String description = request.getParameter("descriptionEx");
        String sessionUser = request.getParameter("sessionUser");
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");

            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("User");
            SessionUser sessionUserObj = SessionService.getSessionUserBySession(sessionUser);

            if (addExercise(sessionUserObj)) {
                out.println("<h1>Création de l'exercice réussi</h1>");
                request.getRequestDispatcher("exercise.jsp").include(request, response);
            }
            else {
                request.getRequestDispatcher("seance.jsp").include(request, response);
                out.println("<h1>Création de l'exercise  échouée<h1>");
            }

            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }





        request.getRequestDispatcher("exercise.jsp").include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    public boolean addExercise(SessionUser sessionUser){
        if(sessionUser==null || sessionUser.getUser() == null){
            return false;
        }
        ExerciseService.addExercise(sessionUser);
        return true;
    }

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
