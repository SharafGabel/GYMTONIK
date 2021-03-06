package controller;

import model.*;
import service.ExerciseService;
import service.MuscleService;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ExerciseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("showMyExercises.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String length= request.getParameter("duree");
        String nameExercise= request.getParameter("nomEx");
        String description = request.getParameter("descriptionEx");
        String niveau = request.getParameter("niveau");
        String nbRepet = request.getParameter("nbRepet");
        User user = (User)(request.getSession()).getAttribute("User");

        String action = request.getParameter("action");
        List<AMuscle> select = new ArrayList<AMuscle>();

        if(action.equals("redirectUpdate")){
            request.getRequestDispatcher("/Exercise/updateExercise.jsp").forward(request, response);
        }
        if(request.getParameter("inlineCheckboxMuscle") != null) {
            String[] selectedMuscles = request.getParameterValues("inlineCheckboxMuscle");
            for (String selectedMuscle : selectedMuscles) {
                select.add(MuscleService.getMuscleById((Integer.parseInt(selectedMuscle))));
            }
        }
        try {

            if(select.size()==0)
            {
                request.getRequestDispatcher("/Exercise/showMyExercises.jsp").forward(request, response);
                //out.println("<h1><p><p>Sélectionner au moins 1 muscle</p></p><h1>");
            }
            else if (action.equals("add"))
            {
                String sessionUserId = request.getParameter("sessionUser");
                if(length != null && !length.trim().isEmpty()
                        && nameExercise != null && !nameExercise.trim().isEmpty()
                        && description != null && !description.trim().isEmpty() && select.size()!=0
                  )
                {
                    ATraining exercise = ExerciseService.createExercise(user,description,nameExercise, Integer.parseInt(length), Integer.parseInt(nbRepet),Integer.parseInt(niveau),select);
                }
                else if (length != null && !length.trim().isEmpty()
                        && nameExercise != null && !nameExercise.trim().isEmpty()
                        && description != null && !description.trim().isEmpty()
                         )
                {
                    SessionUser sessionUser = SessionService.getSessionById(Integer.parseInt(sessionUserId));
                    ATraining exercise = ExerciseService.createExercise(user,description,nameExercise, Integer.parseInt(length), Integer.parseInt(nbRepet),Integer.parseInt(niveau),select);
                    SessionService.addOrUpdateExToSession(sessionUser, exercise);
                }
                request.getRequestDispatcher("/Exercise/showMyExercises.jsp").forward(request, response);
                //out.println("<h1>Création de l'exercice réussie</h1>");
            }
            else {
                request.getRequestDispatcher("/Exercise/showMyExercises.jsp").forward(request, response);
                //out.println("<h1>Création de l'exercise  échouée<h1>");
            }
            if (action.equals("delete"))
            {
                String idExercice = request.getParameter("idEx");
                if(idExercice != null && !idExercice.trim().isEmpty()) {
                    deleteExercise(user,ExerciseService.getExercise(idExercice));
                    //out.println("<h1>Exercice supprimé</h1>");
                }
            }
            if(action.equals("addToEx"))
            {
                String sessionUserId = request.getParameter("sessionToAdd");
                String idExercice = request.getParameter("idEx");
                System.out.println("idS : "+sessionUserId + " idEx : "+idExercice);
                SessionService.addOrUpdateExToSession(Integer.parseInt(sessionUserId),Integer.parseInt(idExercice));
                request.getRequestDispatcher("/Exercise/showMyExercises.jsp").forward(request, response);
                //out.println("<h1>Ajout de l'exercice à la séance réussie</h1>");

            }
            if (action.equals("update")) {
                String idExercice = request.getParameter("idEx");
                if (idExercice != null && !idExercice.trim().isEmpty()
                        &&length != null && !length.trim().isEmpty()
                        && nameExercise != null && !nameExercise.trim().isEmpty()
                        && description != null && !description.trim().isEmpty()
                        && niveau != null && !niveau.trim().isEmpty()
                )
                {
                        if (updateExercise(Integer.parseInt(idExercice),nameExercise,Integer.parseInt(length),Integer.parseInt(nbRepet),description,select)) {
                            request.getRequestDispatcher("/Exercise/showMyExercises.jsp").forward(request, response);
                            //out.println("Exercice mis à jour");
                        }
                    else {
                            request.getRequestDispatcher("/Exercise/showMyExercises.jsp").forward(request, response);
                            //out.println("Mise à jour échouée");
                    }

                }
                else{
                    //out.println("Mise à jour échouée");
                }
            }

        } finally {
            out.close();
        }
    }

    public static boolean updateExercise(int idEx,String nameExo,int length,int nbRepet,String description,List<AMuscle> aMuscles){
        System.out.println(aMuscles.size());
        ExerciseService.updateExercise(idEx,nameExo,length,nbRepet,description,aMuscles);
        return true;
    }

    public static boolean deleteExercise(AUser aUser,ATraining exercise){
        if(exercise == null){
            return false;
        }
        ExerciseService.deleteExercise(aUser,exercise);
        return true;
    }

}
