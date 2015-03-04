import console.ExerciseConsole;
import console.LoginConsole;
import console.RegisterConsole;
import console.SessionConsole;
import model.Exercise;
import model.User;
import util.Util;

import java.util.Scanner;

public class Main {
    private static final int NB_CHOIX = 2;

    public static void main(final String[] args) throws Exception {
        System.out.println("Bienvenue sur Gym Tonik !\n");
        User user = frontPage();
        userPage(user);
    }

    private static User frontPage() {
        User user = new User();
        Scanner sc = new Scanner(System.in);
        System.out.print("Vous êtes sur la page d'accueil de notre site");
        boolean error = true;
        boolean connected = false;
        while (error && !connected) {
            System.out.println(" \n Tapez:");
            System.out.println("1 - Connection");
            System.out.println("2 - Inscription");
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    System.out.println("Redirection connection");
                    user = LoginConsole.login();
                    error = false;
                    break;
                case 2:
                    System.out.println("Redirection inscription");
                    user = RegisterConsole.register();
                    error = false;
                    break;
                default:
                    System.out.println("Une erreur est survenue durant votre saisie !");
            }
        }

        return user;
    }
    private static void userPage(User user){
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);

        System.out.println("Bonjour " + user.getUsername() + "\n");

        System.out.println("1 - Séances");
        System.out.println("2 - Exercices");

        int choix = sc.nextInt();

        switch (choix) {
            case 1:
                SessionConsole.menu(user);
                userPage(user);
                break;
            case 2:
                ExerciseConsole.menu(user);
                userPage(user);
                break;
            default:
                System.out.println("Veuilles entrer un nombre entre 1 et ...");
                break;
        }
    }
}
