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
import java.io.IOException;
import java.util.List;

/**
 * Created by kukugath on 11/03/2015.
 */
public class PerformanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        List<Historique> historiques = PerformanceService.recupcalculPerformance(((User)request.getSession().getAttribute("User")));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // Convert Java Object to Json
        String json = gson.toJson(historiques);

        response.getWriter().print(json);
    }
}
