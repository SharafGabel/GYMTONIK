import console.*;
import model.User;

import java.util.Scanner;

public class Main {
    private static final int NB_CHOIX = 2;

    public static void main(final String[] args) throws Exception {
        User user = frontPage();
        userPage(user);
    }

    private static User frontPage() {
        User user = new User();
        Scanner sc = new Scanner(System.in);
        boolean error = true;
        boolean connected = false;
        while (error && !connected) {
            CoreConsole.getAnonymousHeader();
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    user = LoginConsole.login();
                    error = user == null;
                    break;
                case 2:
                    user = RegisterConsole.register();
                    error = user == null;
                    break;
                default:
                    System.out.println("Une erreur est survenue durant votre saisie !");
            }
        }

        return user;
    }
    private static void userPage(User user){
        Scanner sc = new Scanner(System.in);

        CoreConsole.getConnectedHeader(user);

        System.out.println("1 - Séances");
        System.out.println("2 - Exercices");
        System.out.println("3 - Performances");
        System.out.println("4 - Profil");

        int choix = sc.nextInt();

        switch (choix) {
            case 1:
                SessionConsole.menu(user);
                break;
            case 2:
                ExerciseConsole.menu(user);
                break;
            case 3:
                PerformanceConsole.menu(user);
                break;
            case 4:
                ProfilConsole.showProfil(user);
                break;
            default:
                System.out.println("Veuilles entrer un nombre entre 1 et ...");
                break;
        }
        userPage(user);
    }
}
