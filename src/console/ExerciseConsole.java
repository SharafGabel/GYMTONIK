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
            updateExercise(user, exos.get(choix - 2));
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
        System.out.println("Nom de l'exercice");
        String name = sc.nextLine();
        System.out.println("Description");
        String description = sc.next();
        System.out.println("Durée (minutes)");
        int length = sc.nextInt();
        System.out.println("Niveau");
        int niveau = sc.nextInt();

        ExerciseService.createExercise(user, name, description, length,niveau);

        System.out.print("Ajout reussi");
    }

    private static void updateExercise(AUser user, Exercise exo) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);
        System.out.println("Modification de l'exercice " + exo.getName());
        System.out.println("(Laissez le champ vide pour ne pas modifier la valeur)\n");

        System.out.println("Nom de l'exercice [" + exo.getName() + "]");
        String name = sc.nextLine();
        System.out.println("Description [" + exo.getExplanation() + "]");
        String description = sc.nextLine();
        System.out.println("Durée (minutes) [" + exo.getLength() + "]");
        int length = 0;
        String lengthString = sc.nextLine();

        try {
            length = Integer.parseInt(lengthString);
        } catch (Exception e) {}

        if (!name.trim().isEmpty())
            exo.setName(name);

        if (!description.trim().isEmpty())
            exo.setExplanation(description);

        if (length != 0)
            exo.setLength(length);

        ExerciseService.updateExercise(user, exo);
    }
}
