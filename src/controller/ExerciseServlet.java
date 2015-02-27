package controller;

import model.*;
import service.ExerciseService;
import service.GetList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ExerciseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        String length= request.getParameter("duree");
        String nameExercise= request.getParameter("nomEx");
        String description = request.getParameter("descriptionEx");
        String sessionUserId = request.getParameter("sessionUser");
        String idExercice = request.getParameter("idEx");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");

        try {
            if (action.equals("add")) {
                SessionUser sessionUsers = GetList.getSessionById(Integer.parseInt(sessionUserId));
                if (length != null && !length.trim().isEmpty()
                        && nameExercise != null && !nameExercise.trim().isEmpty()
                        && description != null && !description.trim().isEmpty()
                        && sessionUserId != null && !sessionUserId.trim().isEmpty()
                        && ExerciseService.addExercise(user,sessionUsers, length, nameExercise, description)) {
                    out.println("<h1>Création de l'exercice réussie</h1>");
                }
                else {
                     out.println("<h1>Création de l'exercise  échouée<h1>");
                }
            } else if (action.equals("delete")) {
                System.out.println(idExercice);
                if(idExercice != null && !idExercice.trim().isEmpty()) {
                    deleteExercise(user,ExerciseService.getExercise(idExercice));
                    out.println("Exercice supprimé");
                }
            } else if (action.equals("update")) {
                System.out.println("In action.equals(update)");
                if (idExercice != null && !idExercice.trim().isEmpty()
                        &&length != null && !length.trim().isEmpty()
                        && nameExercise != null && !nameExercise.trim().isEmpty()
                        && description != null && !description.trim().isEmpty()
                        && sessionUserId != null && !sessionUserId.trim().isEmpty())
                {
                    System.out.println("In if(inputs not null)");
                    System.out.println(idExercice + " - " + nameExercise + " - " + length + " - " +
                    description + " - " + sessionUserId);

                    Exercise updatedExercice = ExerciseService.getExercise(idExercice);
                    if (updatedExercice == null) { System.out.println("null :("); }
                    updatedExercice.setName(nameExercise);
                    updatedExercice.setLength(Integer.parseInt(length));
                    updatedExercice.setExplanation(description);
                    updatedExercice.setBodyParts(new ArrayList<AMuscle>());

                    if (updateExercise(user,updatedExercice)) {
                        out.println("Exercice mis à jour");
                    } else out.println("Mise à jour échouée");
                } else out.println("Mise à jour échouée");
            }
            request.getRequestDispatcher("exercise.jsp").forward(request, response);
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*L'utilisateur n'est pas censé atteindre cette page via une requête GET,
        on le redirige vers vers index.jsp*/
        getServletContext().getRequestDispatcher("/exercice.jsp").forward(request,response);
    }


    public boolean addExercise(AUser user,SessionUser sessionUser,String length,String name,String explanation){
        if(sessionUser==null || sessionUser.getUser() == null){
            return false;
        }
        ExerciseService.addExercise(user,sessionUser,length,name,explanation);
        return true;
    }

    public boolean deleteExercise(AUser aUser,Exercise exercise){
        if(exercise == null){
            return false;
        }
        ExerciseService.deleteExercise(aUser,exercise);
        return true;
    }

    public boolean updateExercise(AUser aUser,Exercise exercise){
        if(exercise == null){
            return false;
        }
        ExerciseService.updateExercise(aUser,exercise);
        return true;
    }
    
    
}
