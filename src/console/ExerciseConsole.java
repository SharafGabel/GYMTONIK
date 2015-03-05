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
        CoreConsole.getConnectedHeader(user);
        Scanner sc = new Scanner(System.in);
        List<Exercise> exos = ExerciseService.getExercises(user);

        /* choix_exo = 0 dans le cas où l'utilisateur n'aurait aucun exercice
           choix_exo = nb de exercice + 1 sinon
         */
        int choix_exo = (exos.size() > 0)? exos.size() + 1 : 1;

        System.out.println("-- Exercises --\n");
        System.out.println("1 - Créer un Exercise");
        System.out.println("2 - Voir les exercise");
        if (exos.size() > 0)
            listExercise(exos);
        System.out.println((choix_exo + 2) + " - Retour");

        int choix = sc.nextInt();

        if (choix == 1) {
            createExercise(user);
            menu(user);
        }else if(choix ==2) {
            showExercise();
            menu(user);
        }else if (choix > 1 && choix < choix_exo + 1) {
            updateExercise(user, exos.get(choix - 2));
            menu(user);
        } else if (choix == choix_exo + 1) {
            return;
        } else {
            System.out.println("Veuillez saisir une valeur entre 0 et " + (choix + 3) );
            menu(user);
        }

    }

    private static void showExercise() {
        int i = 1;
        List<Exercise> exos = ExerciseService.getAllExercises();
        for (Exercise exo : exos) {
            System.out.println(i + " - "
                    + exo.getName() + " : "
                    + exo.getExplanation()
                    + " - Niveau : " + exo.getNiveau()
                    + " (" + exo.getDureeExo() + " min)"
                    + " (" + exo.getNbRepetition() + " repetitions)");
            i++;
        }
        System.out.println("Cliquez sur une touche pour continuer...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    private static void listExercise(List<Exercise> exos) {
        int i = 3;
        for (Exercise exo : exos) {
            System.out.println(i + " - "
                    + exo.getName() + " : "
                    + exo.getExplanation()
                    + " - Niveau : " + exo.getNiveau()
                    + " (" + exo.getDureeExo() + " min)"
                    + " (" + exo.getNbRepetition() + " repetitions)");
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
        String description = sc.nextLine();
        System.out.println("Durée (minutes)");
        int length = sc.nextInt();
        System.out.println("Nombre de répétition");
        int nbRep = sc.nextInt();
        System.out.println("Niveau [1..3]");
        int niveau = sc.nextInt();

        ExerciseService.createExercise(user, name, description, length,niveau,nbRep);

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
        System.out.println("Durée (minutes) [" + exo.getDureeExo() + "]");
        int length = 0;
        String lengthString = sc.nextLine();
        System.out.println("Nombre de répétition(s) [" + exo.getNbRepetition() + "]");
        int nbRepet = 0;
        String nbRepetString = sc.nextLine();
        System.out.println("Niveau [" + exo.getNiveau() + "]");
        int niveau = 0;
        String niveauString = sc.nextLine();

        try {
            length = Integer.parseInt(lengthString);
            niveau = Integer.parseInt(niveauString);
            nbRepet = Integer.parseInt(nbRepetString);
        } catch (Exception e) {}
        if (!name.trim().isEmpty())
            exo.setName(name);

        if (!description.trim().isEmpty())
            exo.setExplanation(description);

        if (length > 0 && length <= 20)
            exo.setDureeExo(length);

        if(nbRepet>5 && nbRepet<=500)
            exo.setNbRepetition(nbRepet);

        if (niveau > 0 && niveau < 4)
            exo.setNiveau(niveau);

        ExerciseService.updateExercise(user, exo);
    }
}
