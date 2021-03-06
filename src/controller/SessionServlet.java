package controller;

import model.AMuscle;
import model.ATraining;
import model.SessionUser;
import model.ExerciceSession;
import model.User;
import service.ExerciseService;
import service.HistoriqueService;
import service.MuscleService;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        if(action.equals("generateSession")){
            if(request.getParameter("inlineCheckboxMuscle") != null) {
                List<AMuscle> select = new ArrayList<AMuscle>();
                String[] selecttype = request.getParameterValues("inlineCheckboxMuscle");
                for (int i = 0; i < selecttype.length; i++) {
                    select.add(MuscleService.getMuscleById((Integer.parseInt(selecttype[i]))));
                }
                if(select.size()!=0) {
                    int niveau = Integer.parseInt(request.getParameter("niveau"));
                    int nbExo = Integer.parseInt(request.getParameter("nbExo"));
                    List<ATraining> exerciseList = ExerciseService.getExercisesFromMuscles(select, niveau);

                    //Création de la séance
                    SessionUser sessionUser = SessionService.addSessionUser((User) request.getSession().getAttribute("User"));

                    if (nbExo > exerciseList.size())
                        nbExo = exerciseList.size();

                    for (int i = 0; i < nbExo; i++) {
                        SessionService.addOrUpdateExToSession(sessionUser,exerciseList.get(i));
                    }
                    out.println("<h1>"+sessionUser.getName()+" générée avec "+nbExo+" exercices</h1>");
                    request.getRequestDispatcher("/Session/showSessions.jsp").forward(request, response);
                }
                else {
                    out.println("<h1>Sélectionnez au moins 1 Muscle<h1>");
                    request.getRequestDispatcher("/Session/generate_seance.jsp").forward(request, response);
                }
            }

        }
        else if(action.equals("deleteSession")){
            SessionUser sessionUser = SessionService.getSessionById(Integer.parseInt(request.getParameter("sessionId")));
            this.deleteSession(sessionUser);
            this.getServletContext().getRequestDispatcher("/Session/showSessions.jsp").forward( request, response );//redirection
        }
        else if(action.equals("updateSessionAction")){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-mm");
            SessionUser sessionUser = SessionService.getSessionById(Integer.parseInt(request.getParameter("sessionId")));
            sessionUser.setName(request.getParameter("sessionName"));
            Date sessionProgram = new Date();
            System.out.println("sessionUser" + sessionUser.getIdS());
            try {
                sessionProgram = formatter.parse(request.getParameter("datepicker"));
            } catch(ParseException pe){
                this.getServletContext().getRequestDispatcher("/updateSession.jsp").forward( request, response );//redirection
            }
            sessionUser.setDateProgram(sessionProgram);
            String[] selectedTrainings = request.getParameterValues("checkBoxTraining");
            SessionService.cleanSession(sessionUser);
            if(selectedTrainings != null) {
                for (String selectedTraining : selectedTrainings) {
                    SessionService.addOrUpdateExToSession(sessionUser.getIdS(), Integer.parseInt(selectedTraining));
                }
            }


           // this.updateSession(sessionUser);
            this.getServletContext().getRequestDispatcher("/Session/showSessions.jsp").forward( request, response );//redirection
        }
        else if(action.equals("createSession")){
            this.getServletContext().getRequestDispatcher("/Session/createSession.jsp").forward( request, response );//redirection
        }
        else if(action.equals("createSessionAction")){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-mm");
            String sessionName = request.getParameter("sessionName");
            Date sessionProgram = new Date();
            try {
                sessionProgram = formatter.parse(request.getParameter("datepicker"));
            } catch(ParseException pe){
                this.getServletContext().getRequestDispatcher("/Session/createSession.jsp").forward( request, response );//redirection
            }
            SessionUser sessionUser = new SessionUser(sessionName, sessionProgram);
           sessionUser = SessionService.createSession((User) request.getSession().getAttribute("User"), sessionUser);

            if(request.getParameter("checkBoxTraining") != null) {
                String[] selectedTrainings = request.getParameterValues("checkBoxTraining");
                for(String selectedTraining : selectedTrainings) {
                    SessionService.addOrUpdateExToSession(sessionUser.getIdS(),Integer.parseInt(selectedTraining));
                }
            }
            this.getServletContext().getRequestDispatcher("/Session/showSessions.jsp").forward( request, response );//redirection
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


    public boolean addSession(User user){
        if(user == null){
            return false;
        }

        SessionService.addSession(user);
        return true;
    }

    public boolean deleteSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }
       SessionService.deleteSession(sessionUser);
        return true;
    }

    public boolean updateSession(SessionUser sessionUser){
        if(sessionUser == null){
            return false;
        }
        SessionService.updateSession(sessionUser);
        return true;
    }

}
