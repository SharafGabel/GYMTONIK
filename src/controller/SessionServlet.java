package controller;

import model.*;
import service.ExerciseService;
import service.MuscleService;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
                    List<Exercise> exerciseList = ExerciseService.getExercisesFromMuscles(select, niveau);

                    //Création de la séance
                    SessionUser sessionUser = SessionService.addSessionUser((User) request.getSession().getAttribute("User"));

                    if (nbExo > exerciseList.size())
                        nbExo = exerciseList.size();

                    for (int i = 0; i < nbExo; i++) {
                        SessionService.addOrUpdateExToSession(sessionUser,exerciseList.get(i));
                    }
                    out.println("<h1>"+sessionUser.getName()+" générée avec "+nbExo+" exercices</h1>");
                    request.getRequestDispatcher("seance.jsp").forward(request, response);
                }
                else {
                    out.println("<h1>Sélectionnez au moins 1 Muscle<h1>");
                    request.getRequestDispatcher("generate_seance.jsp").forward(request, response);
                }
            }

        }
        else if(action.equals("deleteSession")){
            SessionUser sessionUser = SessionService.getSessionById(Integer.parseInt(request.getParameter("sessionId")));
            this.deleteSession(sessionUser);
            this.getServletContext().getRequestDispatcher("/seance.jsp").forward( request, response );//redirection
        }
        else if(action.equals("updateSession")){
            SessionUser sessionUser = SessionService.getSessionById(Integer.parseInt(request.getParameter("sessionId")));
            sessionUser.setName(request.getParameter("sessionName"));
            this.updateSession(sessionUser);
            this.getServletContext().getRequestDispatcher("/seance.jsp").forward( request, response );//redirection
        }
        else if(action.equals("createSession")){
            this.getServletContext().getRequestDispatcher("/createSession.jsp").forward( request, response );//redirection
        }
        else {
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<center>");

                HttpSession session = request.getSession();
                User user = (User)session.getAttribute("User");

                if (addSession(user)) {
                    response.sendRedirect("exercise.jsp");
                }
                else {
                    response.sendRedirect("seance.jsp");
                }

                out.println("</center>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
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
