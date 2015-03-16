package controller;

import com.google.gson.Gson;
import model.Exercise;
import model.Historique;
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
        int numSeance = Integer.parseInt(request.getParameter("numSeance"));
        int nbRepetReussi = Integer.parseInt(request.getParameter("repetReussi"));
        int dureeEff = Integer.parseInt(request.getParameter("dureeEffectuee"));
        int result = dureeEff+nbRepetReussi/2;
        int niveau = Integer.parseInt(request.getParameter("niveau"));
        String nomExercice= request.getParameter("nomExo");
        Integer idExercise;
        Historique historique;
        if(result >70){
            idExercise = ExerciseService.getIdExerciseByExercise(nomExercice);
            /*Comment modifier le niveau ? */
            historique=HistoriqueService.getHistoriqueByIdExerciseIdSeance(idExercise,numSeance);
            HistoriqueService.updateHistorique(historique,idExercise);
        }
        else if(result <30){

        }
        else{

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            User user = (User) request.getSession().getAttribute("User");
            List<Historique> listOfHistorique = PerformanceService.recupcalculPerformance(user);
            Gson gson = new Gson();

            String jsonString = gson.toJson(listOfHistorique);

            response.setContentType("application/json");

            response.getWriter().write(jsonString);
            System.out.println(jsonString);
        
    }
}
