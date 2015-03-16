package controller;

import com.google.gson.Gson;
import model.Historique;
import model.User;
import service.PerformanceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PerformanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
