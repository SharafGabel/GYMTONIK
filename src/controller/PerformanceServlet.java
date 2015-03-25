package controller;

import com.google.gson.Gson;
import model.*;
import service.ExerciseService;
import service.HistoriqueService;
import service.PerformanceService;
import util.GsonExclusionStrategy;
import util.ObjectForGson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PerformanceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numSeance = Integer.parseInt(request.getParameter("seanceId"));
        System.out.println("num Seance :"+numSeance);
        int nbRepetReussi = Integer.parseInt(request.getParameter("repetReussi"));
        int dureeEff = Integer.parseInt(request.getParameter("dureeEffectuee"));
        int result = dureeEff+nbRepetReussi/2;
        System.out.println("Result"+result);
        int niveau = Integer.parseInt(request.getParameter("niveau"));
        int idExercise = Integer.parseInt(request.getParameter("idExo"));

        ATraining exercise = ExerciseService.getExercise(idExercise);
        ATraining exerciseMAJ = null;
        if(result >70 && niveau!=3){
            exerciseMAJ = ExerciseService.getNextOrPreviousLevelOfThisExercises(exercise,niveau+1);
            HistoriqueService.updateHistorique(numSeance,exercise,exerciseMAJ);
        }
        else if(result <30 && niveau!=1){
            exerciseMAJ = ExerciseService.getNextOrPreviousLevelOfThisExercises(exercise,niveau-1);
            HistoriqueService.updateHistorique(numSeance,exercise,exerciseMAJ);
        }
        request.getRequestDispatcher("/Session/evaluateSession.jsp").include(request, response);
        //response.getWriter().println("<h1 class=\"center\">Evaluation effectuée</h1>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User user = (User) request.getSession().getAttribute("User");
            Integer idExercise = Integer.parseInt(request.getParameter("exerciseid"));

            String choiceFromPerformance = request.getParameter("submit_choice");

            if(choiceFromPerformance.equals("user_performance")){
                List<ExerciceSession> listofUserExercise = PerformanceService.getUserPerformanceFromExerciseId(user,idExercise);
                Gson gson = GsonExclusionStrategy.createGsonFromBuilder(new GsonExclusionStrategy(SessionUser.class),new GsonExclusionStrategy(ATraining.class));

                String jsonString = gson.toJson(listofUserExercise);

                response.setContentType("application/json");

                response.getWriter().write(jsonString);
                
                //System.out.println(jsonString);
            }
            else if(choiceFromPerformance.equals("compare_performance")){
                Integer seanceId = Integer.parseInt(request.getParameter("seanceId"));


                List<ExerciceSession> listofUserExercise = PerformanceService.getPerfFromExerciseId(idExercise,seanceId);
                
                Double avgPerf = PerformanceService.getAveragePerfFromExerciseId(idExercise);
                Gson gson = GsonExclusionStrategy.createGsonFromBuilder(new GsonExclusionStrategy(SessionUser.class),new GsonExclusionStrategy(ATraining.class));

                ObjectForGson jsonGenerator = new ObjectForGson(listofUserExercise,avgPerf);
                

                String jsonString = gson.toJson(jsonGenerator);
                System.out.println(jsonString);
                //String jsonString2 = gson.toJson(avgPerf);x

                response.setContentType("application/json");

                response.getWriter().write(jsonString);
                
                
                //System.out.println(jsonString);
            }
/*
            List<ExerciceSession> listOfExerciceSession = PerformanceService.recupcalculPerformance(user);
            Gson gson = GsonExclusionStrategy.createGsonFromBuilder(new GsonExclusionStrategy(SessionUser.class),new GsonExclusionStrategy(ATraining.class));

            String jsonString = gson.toJson(listOfExerciceSession);

            response.setContentType("application/json");

            response.getWriter().write(jsonString);
            System.out.println(jsonString);
*/
    }
}
