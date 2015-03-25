package console;

import model.*;
import service.ExerciseService;
import service.PerformanceService;
import service.SessionService;
import util.Util;

import java.util.List;
import java.util.Scanner;

public class PerformanceConsole {

    public static void menu(AUser user) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);
        List<ATraining> exos = ExerciseService.getUserExercises(user);

        System.out.println("Performances\n");
        System.out.println("Sélectionnez un exercice :");

        int i = 1;
        if (exos.size() > 0) {
            for (ATraining exo : exos) {
                System.out.println(i + " - " + exo.getName()
                                + " : " + exo.getExplanation()
                                + " - Niveau " + exo.getNiveau()
                                + " (" + exo.getDureeExo() + "min) "
                                + " (" + exo.getNbRepetition() + "répétitions) "
                );
                i++;
            }
        }
        System.out.println(i + " - Retour");

        int choix = sc.nextInt();
        sc.nextLine();

        if (exos.size() > 0) {
            if (choix > 0 && choix <= exos.size()) {
                displayPerfs(user, exos.get(choix - 1));
                menu(user);
            }
        }
        if (choix < 1 && choix > exos.size() + 1) {
            System.out.println("Veuillez entrer un nombre entre 1 et " + exos.size() + 1);
        }
    }

    private static void displayPerfs(AUser user, ATraining exo) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);

        List<ExerciceSession> userPerfs = PerformanceService.getUserPerformanceFromExerciseId(user, exo.getId());
        Double[] avgPerfs = PerformanceService.getAveragePerfsFromExerciseId(exo.getId());

        System.out.println("Performances");
        System.out.println("Exercice : " + exo.getName()
                + " : " + exo.getExplanation()
                + " - Niveau " + exo.getNiveau()
                + " (" + exo.getDureeExo() + "min) "
                + " (" + exo.getNbRepetition() + "répétitions) "
        );

        System.out.println("\nVos performances :");

        if (userPerfs.size() > 0) {
            for (ExerciceSession es : userPerfs) {
                System.out.println("\tLe " + es.getDateProgEffectue() + " : "
                        + es.getNbRepetEffectue() + " répétitions (" + es.getRatioRepet() + "%)"
                        + " - Durée : " + es.getDureeEffectue() + "min (" + es.getRatioDuree() + "%)");
            }
        } else System.out.println("Vous n'avez pas encore réalisé cet exercice.");

        System.out.println("\nMoyenne des utilisateurs :");

        System.out.println("\t" + avgPerfs[0] + " répétitions (" + avgPerfs[1] + "%)"
                + " - Durée : " + avgPerfs[2] + "min (" + avgPerfs[3] + "%)");

        System.out.println("Appuyez sur la touche Entrée pour continuer...");
        sc.nextLine();

    }
}
