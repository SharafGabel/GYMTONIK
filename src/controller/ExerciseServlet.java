package controller;

import model.Exercise;
import model.SessionUser;
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
        String action = request.getParameter("action");

        String length= request.getParameter("duree");
        String nameExercise= request.getParameter("nomEx");
        String description = request.getParameter("descriptionEx");
        String sessionUserId = request.getParameter("sessionUser");

        String idExercice = request.getParameter("idEx");
        try {
            if (action.equals("add")) {
                SessionUser sessionUsers = GetList.getSessionById(Integer.parseInt(sessionUserId));
                if (length != null && !length.trim().isEmpty()
                        && nameExercise != null && !nameExercise.trim().isEmpty()
                        && description != null && !description.trim().isEmpty()
                        && sessionUserId != null && !sessionUserId.trim().isEmpty()
                        && ExerciseService.addExercise(sessionUsers, length, nameExercise, description)) {
                    out.println("<h1>Création de l'exercice réussie</h1>");
                }
                else {
                     out.println("<h1>Création de l'exercise  échouée<h1>");
                }
            } else if (action.equals("delete")) {
                System.out.println(idExercice);
                if(idExercice != null && !idExercice.trim().isEmpty()) {
                    deleteExercise(ExerciseService.getExercise(idExercice));
                    out.println("Exercice supprimé");
                }
            } else if (action.equals("update")) {
                if (idExercice != null && !idExercice.trim().isEmpty()
                        &&length != null && !length.trim().isEmpty()
                        && nameExercise != null && !nameExercise.trim().isEmpty()
                        && description != null && !description.trim().isEmpty()
                        && sessionUserId != null && !sessionUserId.trim().isEmpty())
                {
                    Exercise updatedExercice = new Exercise(
                            Integer.parseInt(length),
                            nameExercise,
                            description
                    );

                    updatedExercice.setSessionUser(SessionService.getSessionUserByidS(sessionUserId));

                    if (updateExercise(updatedExercice)) {
                        out.println("Exercice mis à jour");
                    } else out.println("Mise à jour échouée");
                } else out.println("Mise à jour échouée");
            }

            request.getRequestDispatcher("exercise.jsp").include(request, response);
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*L'utilisateur n'est pas censé atteindre cette page via une requête GET,
        on le redirige vers vers index.jsp*/
        getServletContext().getRequestDispatcher("/exercice.jsp").forward(request,response);
    }


    /*public boolean addExercise(SessionUser sessionUser){
        if(sessionUser==null || sessionUser.getUser() == null){
            return false;
        }
        ExerciseService.addExercise(sessionUser);
        return true;
    }*/

    public boolean deleteExercise(Exercise exercise){
        if(exercise == null){
            return false;
        }
        ExerciseService.deleteExercise(exercise);
        return true;
    }

    public boolean updateExercise(Exercise exercise){
        if(exercise == null){
            return false;
        }
        ExerciseService.updateExercise(exercise);
        return true;
    }
    
    
}
