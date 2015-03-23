package controller;

import com.google.gson.Gson;
import model.*;
import service.ExerciseService;
import service.HistoriqueService;
import service.SessionService;
import util.GsonExclusionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class HistoriqueServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEx = Integer.parseInt(request.getParameter("htoAdd"));
        int idS = Integer.parseInt(request.getParameter("sessionS"));
        AUser user = (User)(request.getSession()).getAttribute("User");
        int nbRepet = Integer.parseInt(request.getParameter("nbRepet"));
        int dureeEff = Integer.parseInt(request.getParameter("dureeEff"));
        int timeS = Integer.parseInt(request.getParameter("timeS"));
        int dureeARealiser = Integer.parseInt(request.getParameter("dureeARealiser"));
        int nbRepetARealiser= Integer.parseInt(request.getParameter("nbRepetARealiser"));

        if(HistoriqueService.addExerciseDone(idS,idEx,user,dureeEff,nbRepet,timeS,nbRepetARealiser,dureeARealiser))
            request.getRequestDispatcher("performance.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration paramNames = request.getParameterNames();
        String paramName = (String) paramNames.nextElement();

        if (paramName.equalsIgnoreCase("sessionUser")) {
            String idSeance = request.getParameter("sessionUser");
            if(idSeance.equals("none"))
            {
                List<ATraining> in;
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
                List<ATraining> in;
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
            List<ATraining> objectList= ExerciseService.getExercises(Integer.parseInt(idSess));
            System.out.println("Here : "+objectList.toString());
            Gson gson = GsonExclusionStrategy.createGsonFromBuilder(new GsonExclusionStrategy(AUser.class));
            String json = gson.toJson(objectList);
            System.out.println("json : "+json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        else if(paramName.equalsIgnoreCase("sessionUserFromPerformance"))
        {
            String idSess = request.getParameter("sessionUserFromPerformance");
            List<Object> objectList= HistoriqueService.getExercisesAndHistoriqueObject(Integer.parseInt(idSess));
            System.out.println("Here : "+objectList.toString());
            Gson gson = GsonExclusionStrategy.createGsonFromBuilder(new GsonExclusionStrategy(AUser.class));
            String json = gson.toJson(objectList);
            System.out.println("json : "+json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        else if (paramName.equalsIgnoreCase("exerciseLevel")) {
            String levelId = request.getParameter("exerciseLevel");
            List<ATraining> in;
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
            List<ATraining> in;
            in = ExerciseService.getExercises(sessionUsers);
            System.out.println(in.toString());
            String json;
            json = new Gson().toJson(in);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            System.out.println(json);//test
        }
        else if(paramName.equalsIgnoreCase("sessionUserFromPerf")){
            String sessionUser = request.getParameter("sessionUserDone");
            List<ExerciceSession> in;
            in = HistoriqueService.getExercisesAndHistorique(Integer.parseInt(sessionUser));
            System.out.println(in.toString());
            String json;
            json = new Gson().toJson(in);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            System.out.println(json);//test
        }
        else if(paramName.equalsIgnoreCase("sessionUserFromPerformance")){
            String sessionUser = request.getParameter("sessionUserFromPerformance");
            List<ExerciceSession> in;
            in = HistoriqueService.getExercisesAndHistorique(Integer.parseInt(sessionUser));
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
