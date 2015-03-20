package controller;

import com.google.gson.Gson;
import model.AUser;
import model.Exercise;
import service.ExerciseService;
import util.GsonExclusionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int numSeance = Integer.parseInt(request.getParameter("seanceid"));

        List<Exercise> exercises;
        exercises = ExerciseService.getExercises(numSeance);
        Gson gson = GsonExclusionStrategy.createGsonFromBuilder(new GsonExclusionStrategy(AUser.class));
        String json = gson.toJson(exercises);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);


    }
}
