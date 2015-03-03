import console.LoginConsole;
import console.RegisterConsole;
import model.User;

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
        int i = 0;
        System.out.print("Vous êtes sur la page d'accueil de notre site");
        boolean error = true;
        boolean connected = false;
        while (error && !connected) {
            System.out.println(" \n Tapez:");
            System.out.println("1 - Connection");
            System.out.println("2 - Inscription");
            String rep = sc.nextLine();
            try {
                i = Integer.parseInt(rep);
            }
            catch(NumberFormatException e){
            }
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
        System.out.println("Bonjour " + user.getUsername());
    }
}
