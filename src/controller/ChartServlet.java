package controller;

import com.google.gson.Gson;
import model.Exercise;
import service.ExerciseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 20/03/2015.
 */
public class ChartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numSeance = Integer.parseInt(request.getParameter("seanceid"));

        List<Exercise> exercises;
        exercises = ExerciseService.getExercises(numSeance);
        System.out.println(exercises.toString());
        String json;
        json = new Gson().toJson(exercises); //Todo :Bug --> impossible de récupérer le fichier json 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        System.out.println(json);//test




    }
}
