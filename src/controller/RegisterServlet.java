package controller;

import model.User;
import service.RegisterService;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        PrintWriter out = response.getWriter();
        String username= request.getParameter("username");
        String email = request.getParameter("email");
        String emailVerif = request.getParameter("emailVerif");
        String password = request.getParameter("password");
        String poids = request.getParameter("weight");
        String taille = request.getParameter("height");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registration Status</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");

            if(register(username, email, password,emailVerif,poids,taille))
            {
                request.getRequestDispatcher("welcome.jsp").include(request, response);
                out.println("<h1>Registration Successful</h1>");
            } else
            {
                request.getRequestDispatcher("welcome.jsp").include(request, response);
                // TODO: Message et/ou tests à revoir, puisque l'échec de l'inscription peut également être dû à un username ou email déjà existant
                out.println("<h1>Registration Unsuccessful</h1><p>Un des champs obligatoire est vide</p>");
            }

            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
            out.close();
    }

    public static boolean register(String username, String email, String password,String emailVerif, String poids, String taille) {

        // BORDEL MAIS ARRÊTEZ AVEC STRING.EQUALS(NULL)!
        /*  String.equals() permet de comparer le *contenu* d'une chaîne de caractère
            or ici on souhaite vérifier que la variable ne pointe pas vers null (et éviter un bête NullPointerException)
            donc c'est **var == null**
         */
        if ( username == null || username.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty() || !emailVerif.equals(email) )
            return false;
         else
        {
            User user = new User(username, email, password);
            if(isInteger(poids))
            {
                user.setWeight(Integer.parseInt(poids));
            }
            if(isInteger(taille))
            {
                user.setHeight(Integer.parseInt(taille));
            }
            return RegisterService.register(user);
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        getServletContext().getRequestDispatcher("/welcome.jsp").forward(request,response);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
