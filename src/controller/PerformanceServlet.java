package controller;

import com.google.gson.Gson;
import model.*;
import service.ExerciseService;
import service.HistoriqueService;
import service.PerformanceService;
import util.GsonExclusionStrategy;

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
        int nbRepetReussi = Integer.parseInt(request.getParameter("repetReussi"));
        int dureeEff = Integer.parseInt(request.getParameter("dureeEffectuee"));
        int result = dureeEff+nbRepetReussi/2;
        int niveau = Integer.parseInt(request.getParameter("niveau"));
        int idExercise = Integer.parseInt(request.getParameter("idExo"));

        ATraining exercise;
        if(result >70 && niveau!=3){
            exercise = ExerciseService.getExercise(idExercise,niveau+1);
            HistoriqueService.updateHistorique(numSeance,exercise);
        }
        else if(result <30 && niveau!=1){
            exercise = ExerciseService.getExercise(idExercise,niveau-1);
            HistoriqueService.updateHistorique(numSeance,exercise);
        }
        request.getRequestDispatcher("performance.jsp").include(request, response);
        response.getWriter().println("Evaluation effectuée");
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

                List<ExerciceSession> listofUserExercise = PerformanceService.getPerfFromExerciseId(idExercise);
                List<ExerciceSession> avgPerf = PerformanceService.getAveragePerfFromExerciseId(idExercise);
                List<ExerciceSession> listPerformance = new ArrayList<ExerciceSession>();
                Gson gson = GsonExclusionStrategy.createGsonFromBuilder(new GsonExclusionStrategy(SessionUser.class),new GsonExclusionStrategy(ATraining.class));

                //listofUserExercise.addAll(avgPerf);
                //listPerformance.addAll(listofUserExercise);
                //listPerformance.addAll(avgPerf);
                String jsonString = gson.toJson(listofUserExercise);
                //String jsonString2 = gson.toJson(avgPerf);

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
