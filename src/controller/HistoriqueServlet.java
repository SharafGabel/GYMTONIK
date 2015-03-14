package controller;

import com.google.gson.Gson;
import model.Exercise;
import model.Historique;
import model.SessionUser;
import service.ExerciseService;
import service.HistoriqueService;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class HistoriqueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration paramNames = request.getParameterNames();
        String paramName = (String) paramNames.nextElement();
        System.out.println("YEP");
        if (paramName.equalsIgnoreCase("sessionUser")) {
            String idSeance = request.getParameter("sessionUser");
            if(idSeance.equals("none"))
            {
                List<Exercise> in;
                in = ExerciseService.getAllExercises();
                System.out.println(in.toString());
                String json;
                json = new Gson().toJson(in);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
            else
            {
                SessionUser sessionUsers = SessionService.getSessionById(Integer.parseInt(idSeance));
                List<Exercise> in;
                in = ExerciseService.getExercises(sessionUsers);
                String json;
                    json = new Gson().toJson(in);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                System.out.println(json);//test
            }
        }
        else if(paramName.equalsIgnoreCase("sessionUserH"))
        {
            String idSess = request.getParameter("sessionUserH");
            List<Historique> objectList= HistoriqueService.getExercises(Integer.parseInt(idSess));
            System.out.println("Here : "+objectList.toString());
            /*GsonBuilder gsonBuilder = new GsonBuilder();
            new GraphAdapterBuilder()
                    .addType(Exercise.class)
                    .registerOn(gsonBuilder);
            Gson gson = gsonBuilder.create();*/
            String json;
            json = new Gson().toJson(objectList);
            System.out.println("json : "+json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        else if (paramName.equalsIgnoreCase("exerciseLevel")) {
            String levelId = request.getParameter("exerciseLevel");
            List<Exercise> in;
            in = ExerciseService.getExercisesByLevel(Integer.parseInt(levelId));
            String json;
            json = new Gson().toJson(in);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        else if(paramName.equalsIgnoreCase("sessionUserPerf")){
            String sessionUser = request.getParameter("sessionUserPerf");
            SessionUser sessionUsers = SessionService.getSessionById(Integer.parseInt(sessionUser));
            List<Exercise> in;
            in = ExerciseService.getExercises(sessionUsers);
            System.out.println(in.toString());
            String json;
            json = new Gson().toJson(in);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            System.out.println(json);//test
        }
    }

}
