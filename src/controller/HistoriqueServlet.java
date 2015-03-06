package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import model.Exercise;
import model.SessionUser;
import service.ExerciseService;
import service.HistoriqueService;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuga on 06/03/2015.
 */
public class HistoriqueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String idSeance = request.getParameter("sessionUser");
        SessionUser sessionUsers = SessionService.getSessionById(Integer.parseInt(idSeance));
        List<Exercise> in = new ArrayList<Exercise>();
        in=ExerciseService.getExercises(sessionUsers);
        Gson gson = new Gson();
        JsonElement element =gson.toJsonTree(in,new TypeToken<List<Exercise>>(){}.getType());
        response.setContentType("application/json; charset=UTF-8");
        JsonArray jsonArray = element.getAsJsonArray();
        //out.print(jsonArray);
        //out.flush();
        //jsonArray.toString();
        response.getWriter().print(jsonArray);
    }
}
