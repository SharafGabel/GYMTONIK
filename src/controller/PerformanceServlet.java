package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.AUser;
import model.Historique;
import model.User;
import service.HistoriqueService;
import service.PerformanceService;
import util.GsonExclusionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by kukugath on 11/03/2015.
 */
public class PerformanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idS = Integer.parseInt(request.getParameter("sessionUserFromPerformance"));
        List<Historique> listOfHistorique = HistoriqueService.getExercises(idS);
        Gson gson = GsonExclusionStrategy.createGsonFromBuilder(new GsonExclusionStrategy(null));
        String json = gson.toJson(listOfHistorique);
        System.out.println("json : " + json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
