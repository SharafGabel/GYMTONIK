package controller;

import model.SessionUser;
import model.User;
import service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        if(action.equals("deleteSession")){
            SessionUser sessionUser = SessionService.getSessionById(Integer.parseInt(request.getParameter("sessionId")));
            this.deleteSession(sessionUser);
        }else {
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
