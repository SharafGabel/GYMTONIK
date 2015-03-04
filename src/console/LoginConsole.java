package console;

import model.User;
import service.LoginService;
import sun.rmi.log.LogInputStream;

import java.util.Scanner;

public class LoginConsole {

    public static User login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Connexion\n");
        boolean error = true;
        String login = "";
        while(error) {
            System.out.println("Veuillez saisir votre identifiant :");
            login = sc.nextLine();
            System.out.println("Veuillez saisir votre mot de passe :");
            String password = sc.nextLine();

            if (!login.trim().isEmpty() && !password.trim().isEmpty()
                    && LoginService.authenticate(login, password)) {
                System.out.println("Authentification réussie");
                error = false;
            } else {
                System.out.println("Authentification échoué");
            }
        }

        return LoginService.getUserByUsername(login);
    }
}
