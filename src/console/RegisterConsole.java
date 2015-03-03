package console;

import model.User;
import service.RegisterService;

import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class RegisterConsole {
    public static boolean register() {
        System.out.println("Inscription\n");

        Scanner sc = new Scanner(System.in);

        System.out.println("Veuillez saisir l'identifiant souhaité :");
        String login = sc.nextLine();

        System.out.println("Veuillez saisir votre adresse e-mail :");
        String email = sc.nextLine();

        System.out.println("Veuillez confirmer votre adresse e-mail :");
        String emailVerif = sc.nextLine();

        System.out.println("Veuillez saisir votre mot de passe :");
        String password = sc.nextLine();

        System.out.println("Veuillez saisir votre taille en cm :");
        int taille = sc.nextInt();

        System.out.println("Veuillez saisir votre poids en kg :");
        int poids = sc.nextInt();

        if (!login.trim().isEmpty()
                && !email.trim().isEmpty() && !emailVerif.trim().isEmpty()
                && email.equals(emailVerif)
                && !password.trim().isEmpty()
                && taille > 0 && poids > 0) {
            User user = new User(login, email, password);
            user.setHeight(taille);
            user.setWeight(poids);

            if (RegisterService.register(user)) {
                System.out.println("Inscription réussie");
                return true;
            } else {
                System.out.println("L'identifiant ou l'adresse email saisie existe déjà.");
                return false;
            }
        } else {
            System.out.println("Un des champs est vide et/ou les adresses ne correspondent pas.");
            return false;
        }
    }
}
