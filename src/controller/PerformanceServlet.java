package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Historique;
import model.User;
import service.HistoriqueService;
import service.PerformanceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by kukugath on 11/03/2015.
 */
public class PerformanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user= (User)session.getAttribute("User");
        List<Historique> listOfHistorique = PerformanceService.recupcalculPerformance(user);

        Gson gson = new Gson();

        String jsonString = gson.toJson(listOfHistorique);

        response.setContentType("application/json");

        response.getWriter().write(jsonString);

    }
}
