package console;

import model.ATraining;
import model.SessionUser;
import model.User;
import service.ExerciseService;
import service.SessionService;
import util.Util;

import java.util.List;
import java.util.Scanner;

public class SessionConsole {
    public static void menu(User user) {
        CoreConsole.getConnectedHeader(user);
        Scanner sc = new Scanner(System.in);
        List<SessionUser> seances = SessionService.getSessionList(user);

        /* choix_seance = 0 dans le cas où l'utilisateur n'aurait aucune séance
           choix_seance = nb de séance + 1 sinon
         */
        int choix_seance = seances.size() + 1;

        System.out.println("-- Séances --\n");
        System.out.println("1 - Créer une séance");
        if (seances.size() > 0)
            listSession(seances);
        System.out.println((choix_seance + 1) + " - Retour");


        int choix = sc.nextInt();
        if (choix == 1) {
            createSession(user);
            menu(user);
        } else if (choix > 1 && choix < choix_seance + 1) {
            displaySession(seances.get(choix - 2));
            menu(user);
        } else if (choix == choix_seance + 1) {
            return;
        } else {
            System.out.println("Veuillez saisir une valeur entre 0 et " + choix_seance );
            menu(user);
        }
    }

    private static void listSession(List<SessionUser> seances) {
        int i = 2;
        for (SessionUser s : seances) {
            System.out.println(i + " - " + s.getName() + " - " + s.getDateProgram());
            i++;
        }
    }

    private static void createSession(User user) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);

        System.out.println("Séance créée\n");

        SessionService.addSession(user);
    }

    private static void displaySession(SessionUser session) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n");

        List<ATraining> exs = ExerciseService.getExercises(session);

        System.out.println("Séance " + session.getName() + " du " + session.getDateProgram());
        System.out.println("Exercices :\n");

        if (exs.size() > 0) {
            for (ATraining exo : exs) {
                System.out.println(exo.getName() + " : "
                        + exo.getExplanation()
                        + " - Niveau :" + exo.getNiveau()
                        + " (" + exo.getDureeExo() + " min)"
                        + " (" + exo.getNbRepetition() + " répétitions)");
            }
        } else System.out.println("Il n'y a aucun exercice pour cette séance");

        System.out.println("\nPour ajouter des exercices à cette séances, allez dans le menu Exercice\n");

        System.out.println("Appuyez sur la touche Entrée pour continuer...");
        sc.nextLine();


    }
}
