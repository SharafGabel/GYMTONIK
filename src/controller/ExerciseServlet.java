package controller;

import model.*;
import service.ExerciseService;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ExerciseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String length= request.getParameter("duree");
        String nameExercise= request.getParameter("nomEx");
        String description = request.getParameter("descriptionEx");
        String niveau = request.getParameter("niveau");
        User user = (User)(request.getSession()).getAttribute("User");

        String action = request.getParameter("action");

        try {

            if (action.equals("add"))
            {

                String sessionUserId = request.getParameter("sessionUser");
                String idExercice = request.getParameter("idEx");
                SessionUser sessionUsers = SessionService.getSessionById(Integer.parseInt(sessionUserId));
                if (length != null && !length.trim().isEmpty()
                        && nameExercise != null && !nameExercise.trim().isEmpty()
                        && description != null && !description.trim().isEmpty()
                        && sessionUserId != null && !sessionUserId.trim().isEmpty()
                        && ExerciseService.addExercise(user,sessionUsers, length, nameExercise, description,Integer.parseInt(niveau))) {
                    out.println("<h1>Création de l'exercice réussie</h1>");
                }
                else {
                     out.println("<h1>Création de l'exercise  échouée<h1>");
                }
            }

            if (action.equals("delete"))
            {
                String idExercice = request.getParameter("idEx");
                if(idExercice != null && !idExercice.trim().isEmpty()) {
                    deleteExercise(user,ExerciseService.getExercise(idExercice));
                    out.println("Exercice supprimé");
                }
            }
            
            if(action.equals("addToEx"))
            {
                String sessionUserId = request.getParameter("sessionToAdd");
                String idExercice = request.getParameter("idEx");
                SessionUser sessionUsers = SessionService.getSessionById(Integer.parseInt(sessionUserId));
                if(SessionService.addExToSession(sessionUsers, ExerciseService.getExercise(idExercice)))
                {
                    out.println("<h1>Ajout de l'exercice à la séance réussie</h1>");
                }
            }
            
            if (action.equals("update")) {
                String idExercice = request.getParameter("idEx");
                System.out.println("In action.equals(update)");
                if (idExercice != null && !idExercice.trim().isEmpty()
                        &&length != null && !length.trim().isEmpty()
                        && nameExercise != null && !nameExercise.trim().isEmpty()
                        && description != null && !description.trim().isEmpty()
                        && niveau != null && !niveau.trim().isEmpty()
                ) {
                    Exercise updatedExercice = ExerciseService.getExercise(idExercice);
                    if (updatedExercice != null ) {
                        updatedExercice.setName(nameExercise);
                        updatedExercice.setLength(Integer.parseInt(length));
                        updatedExercice.setExplanation(description);
                        updatedExercice.setNiveau(Integer.parseInt(niveau));
                        updatedExercice.setBodyParts(new ArrayList<AMuscle>());

                        if (updateExercise(user,updatedExercice)) {
                            out.println("Exercice mis à jour");
                        } else out.println("Mise à jour échouée");
                    }
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


    public static boolean addExercise(AUser user,SessionUser sessionUser,String length,String name,String explanation,int niveau){
        if(sessionUser==null || sessionUser.getUser() == null){
            return false;
        }
        ExerciseService.addExercise(user,sessionUser,length,name,explanation,niveau);
        return true;
    }

    public static boolean deleteExercise(AUser aUser,Exercise exercise){
        if(exercise == null){
            return false;
        }
        ExerciseService.deleteExercise(aUser,exercise);
        return true;
    }

    public static boolean updateExercise(AUser aUser,Exercise exercise){
        if(exercise == null){
            return false;
        }
        ExerciseService.updateExercise(aUser,exercise);
        return true;
    }
    
    
}
