package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.Exercise;
import model.SessionUser;
import org.json.JSONArray;
import org.json.JSONObject;
import service.ExerciseService;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by kuga on 06/03/2015.
 */
public class HistoriqueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration paramNames = request.getParameterNames();
        String paramName = (String) paramNames.nextElement();

        if (paramName.equalsIgnoreCase("sessionUser")) {
            String idSeance = request.getParameter("sessionUser");
            if(idSeance == "none")
            {
                List<Exercise> in;
                in = ExerciseService.getAllExercises();
                String json;
                json = new Gson().toJson(in);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
            else {
                SessionUser sessionUsers = SessionService.getSessionById(Integer.parseInt(idSeance));
                List<Exercise> in;
                in = ExerciseService.getExercises(sessionUsers);
                String json;
                json = new Gson().toJson(in);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                // System.out.println(json);//test
            }
        }
        if (paramName.equalsIgnoreCase("exerciseLevel")) {
            String levelId = request.getParameter("exerciseLevel");
            List<Exercise> in;
            in = ExerciseService.getExercisesByLevel(Integer.parseInt(levelId));
            String json;
            json = new Gson().toJson(in);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

}
