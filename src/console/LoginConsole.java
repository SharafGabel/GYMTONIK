package console;

import service.LoginService;

import java.util.Scanner;

public class LoginConsole {

    public static boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Connexion\n");

        System.out.println("Veuillez saisir votre identifiant :");
        String login = sc.nextLine();

        System.out.println("Veuillez saisir votre mot de passe :");
        String password = sc.nextLine();

        if(!login.trim().isEmpty() && password.trim().isEmpty()
                && LoginService.authenticate(login, password)) {
            System.out.println("Authentification réussie");
            return true;
        } else {
            System.out.println("Authentification échoué");
            return false;
        }
    }
}
