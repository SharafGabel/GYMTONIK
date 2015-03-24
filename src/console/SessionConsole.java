package console;

import model.ATraining;
import model.SessionUser;
import model.User;
import service.ExerciseService;
import service.HistoriqueService;
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
            int i = 1;
            for (ATraining exo : exs) {
                System.out.println( i + " - "
                        + exo.getName() + " : "
                        + exo.getExplanation()
                        + " - Niveau :" + exo.getNiveau()
                        + " (" + exo.getDureeExo() + " min)"
                        + " (" + exo.getNbRepetition() + " répétitions)");
                i++;
            }
            System.out.println( i + " - Retour");
        } else System.out.println("Il n'y a aucun exercice pour cette séance");

        System.out.println("\nPour ajouter des exercices à cette séances, allez dans le menu Exercice\n");
        System.out.println("Sélectionnez l'exercice ou l'action que vous souhaitez effectuer :");

        if (exs.size() > 0) {
            int choix = sc.nextInt();
            sc.nextLine();

            if (choix > 0 && choix < exs.size() + 1) {
                doExercise(session, exs.get(choix - 1));
            } else if (choix > exs.size() + 1) {
                System.out.println("Veuillez entrer un chiffre entre 1 et " + exs.size() + 1);

                System.out.printf("Appuyez sur la touche Entrée pour continuer...");
                sc.nextLine();
                displaySession(session);
            }
        } else {
            System.out.printf("Appuyez sur la touche Entrée pour continuer...");
            sc.nextLine();
        }

    }

    private static void doExercise(SessionUser sessionUser, ATraining exercise) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);

        System.out.println("Exercice : " + exercise.getName() + " - Niveau " + exercise.getNiveau());
        System.out.println(exercise.getExplanation());
        System.out.println(exercise.getDureeExo() + " minutes - " + exercise.getNbRepetition() + " répétitions\n");

        System.out.println("Combien d'heure avez-vous dormi cette nuit ?");
        int sleep = sc.nextInt();

        System.out.println("Combien de répétions avez-vous effectué ?");
        int nbRep = sc.nextInt();

        System.out.println("En combien de minutes avez-vous effectué l'exercice ?");
        int duree = sc.nextInt();

        if (HistoriqueService.addExerciseDone(sessionUser, exercise, duree, nbRep, sleep)) {
            System.out.println("Exercice enregistré");
        }
    }
}
