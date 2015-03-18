package controller;

import com.google.gson.Gson;
import model.ExerciceSession;
import model.Exercise;
import model.User;
import service.ExerciseService;
import service.HistoriqueService;
import service.PerformanceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PerformanceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numSeance = Integer.parseInt(request.getParameter("seanceId"));
        int nbRepetReussi = Integer.parseInt(request.getParameter("repetReussi"));
        int dureeEff = Integer.parseInt(request.getParameter("dureeEffectuee"));
        int result = dureeEff+nbRepetReussi/2;
        int niveau = Integer.parseInt(request.getParameter("niveau"));
        int idExercise = Integer.parseInt(request.getParameter("idExo"));
        Exercise exercise;
        if(result >70 && niveau!=3){
            exercise = ExerciseService.getExercise(idExercise,niveau+1);
            HistoriqueService.updateHistorique(numSeance,exercise,(User)request.getSession().getAttribute("User"));
        }
        else if(result <30 && niveau!=1){
            exercise = ExerciseService.getExercise(idExercise,niveau-1);
            HistoriqueService.updateHistorique(numSeance,exercise,(User)request.getSession().getAttribute("User"));
        }
        request.getRequestDispatcher("performance.jsp").include(request, response);
        response.getWriter().println("Evaluation effectuée");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            User user = (User) request.getSession().getAttribute("User");
            List<ExerciceSession> listOfExerciceSession = PerformanceService.recupcalculPerformance(user);
            Gson gson = new Gson();

            String jsonString = gson.toJson(listOfExerciceSession);

            response.setContentType("application/json");

            response.getWriter().write(jsonString);
            System.out.println(jsonString);
        
    }
}
