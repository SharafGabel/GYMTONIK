package console;

import model.Exercise;
import model.AUser;
import model.Exercise;
import service.ExerciseService;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExerciseConsole {
    public static void menu(AUser user) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);
        List<Exercise> exos = ExerciseService.getExercises(user);

        /* choix_exo = 0 dans le cas où l'utilisateur n'aurait aucun exercice
           choix_exo = nb de exercice + 1 sinon
         */
        int choix_exo = (exos.size() > 0)? exos.size() + 1 : 1;

        System.out.println("-- Exercises --\n");
        System.out.println("1 - Créer un Exercise");
        if (exos.size() > 0)
            listExercise(exos);
        System.out.println((choix_exo + 1) + " - Retour");

        int choix = sc.nextInt();

        if (choix == 1) {
            createExercise(user);
            menu(user);
        }else if (choix > 1 && choix < choix_exo + 1) {
            // modification exo
            menu(user);
        } else if (choix == choix_exo + 1) {
            return;
        } else {
            System.out.println("Veuillez saisir une valeur entre 0 et " + 2 );
            menu(user);
        }

    }

    private static void listExercise(List<Exercise> exos) {
        int i = 2;
        for (Exercise exo : exos) {
            System.out.println(i + " - "
                    + exo.getName() + " : "
                    + exo.getExplanation()
                    + " (" + exo.getLength() + " min)");
            i++;
        }
    }

    private static void createExercise(AUser user) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);

        System.out.println("Création d'un Exercise\n");
        System.out.println("Nom de l'Exercise");
        String name = sc.nextLine();
        System.out.println("Description de l'Exercise");
        String description = sc.next();
        System.out.println("Durée de l'Exercise");
        int length = sc.nextInt();

        ExerciseService.createExercise(user, name, description, length);

        System.out.print("Ajout reussi");
    }
}
