package console;

import model.AUser;
import model.Exercise;
import service.ExerciseService;
import util.Util;

import java.util.List;
import java.util.Scanner;

public class ExerciseConsole {
    public static void menu(AUser user) {
        CoreConsole.getConnectedHeader(user);
        Scanner sc = new Scanner(System.in);
        List<Exercise> exos = ExerciseService.getExercises();

        /* choix_exo = 1 dans le cas où l'utilisateur n'aurait aucun exercice
           choix_exo = nb de exercice + 1 sinon
         */
        int choix_exo = exos.size() + 1;

        System.out.println("-- Exercises --\n1 - Créer un Exercise");
        if (exos.size() > 0)
            listExercise(exos);
        System.out.println((choix_exo + 1) + " - Retour");

        int choix = sc.nextInt();

        if (choix == 1) {
            createExercise(user);
            menu(user);
        }else if (choix > 1 && choix < choix_exo + 1) {
            displayExercise(user, exos.get(choix - 2));
            menu(user);
        } else if (choix == choix_exo + 1) {
            return;
        } else {
            System.out.println("Veuillez saisir une valeur entre 0 et " + (choix + 3) );
            menu(user);
        }

    }

    private static void listExercise(List<Exercise> exos) {
        int i = 2;
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

        System.out.println("Création d'un Exercise\nNom de l'exercice");
        String name = sc.nextLine();
        System.out.println("Description");
        String description = sc.nextLine();
        System.out.println("Durée (minutes) (< 20)");
        int length = sc.nextInt();
        System.out.println("Nombre de répétitions (>=5)");
        int nbRep = sc.nextInt();
        System.out.println("Niveau [1..3]");
        int niveau = sc.nextInt();

        if (nbRep >= 5 && niveau > 0 && niveau < 4 && length > 0 && length <= 20) {
            //ExerciseService.createExercise(user, name, description, length, niveau, nbRep);
            System.out.print("Ajout reussi");
        } else {
            if (nbRep < 5) {
                System.out.println("Le nombre de répétitions doit être suppérieur ou égal à 5");
            }
            if (niveau < 1) {
                System.out.println("Le niveau doit être compris entre 1 et 3 inclus");
            }
            if (niveau > 3) {
                System.out.println("Le niveau doit être compris entre 1 et 3 inclus");
            }
            if (length > 20) {
                System.out.println("La durée de l'exercice ne doit pas dépasser 20 minutes");
            }
            if (length < 0) {
                System.out.println("La durée de l'exercice doit être suppérieure à 0.");
            }

            System.out.println("\nAppuyez sur la touche Entrée pour continuer...");
            sc.nextLine();
            sc.nextLine();
        }
    }

    private static void displayExercise(AUser user, Exercise exo) {
        Util.clearConsole();
        System.out.println("Exercice " + exo.getName());
        System.out.println(exo.getExplanation());
        System.out.println(exo.getDureeExo() + "min - Niveau " + exo.getNiveau()
                + " - " + exo.getNbRepetition() + " répétitions\n");

        System.out.println("1 - Modifier");
        System.out.println("2 - Supprimer");

        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();

        switch (choix) {
            case 1:
                updateExercise(user, exo);
                break;
            case 2:
                deleteExercise(user, exo);
                break;
            default:
                System.out.println("Veuillez entrer 1 ou 2");
                displayExercise(user, exo);
                break;
        }

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

        System.out.println("Nombre de répétitions [" + exo.getNbRepetition() + "]");
        int nbRepet = 0;
        String nbRepetString = sc.nextLine();

        System.out.println("Niveau [" + exo.getNiveau() + "]");
        int niveau = 0;
        String niveauString = sc.nextLine();

        /*  Les parseInt sont chacun dans leur propre bloc try/catch
            car dans le cas où ils sont tous dans le même bloc,
            si l'un d'eux échoue, on sort du bloc et les autres
            ne seront pas effectué. Ce serait dommage dans le cas où
             les autres champs ont été modifié. */
        try {
            length = Integer.parseInt(lengthString);
        } catch (Exception e) {}
        try {
            niveau = Integer.parseInt(niveauString);
        } catch (Exception e) {}
        try {
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

        if (length > 20)
            System.out.println("La durée de l'exercice ne peut pas excéder 20 minutes");
        if (nbRepet > 500)
            System.out.println("Le nombre de répétitions de l'exercice ne peut pas excéder 500");
        if (niveau > 3)
            System.out.println("Le niveau de l'exercice doit être compris entre 1 et 3");


        System.out.println("\nAppuyez sur la touche Entrée pour continuer...");
        sc.nextLine();

        ExerciseService.updateExercise(user, exo);
    }

    private static void deleteExercise(AUser user, Exercise exo) {
        Util.clearConsole();
        System.out.println("Êtes-vous sûr de vouloir supprimer l'exercice " + exo.getName() + " ? [Oui/Non]");

        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();

        if (choix.trim().toLowerCase().equals("o") || choix.trim().toLowerCase().equals("oui")) {
            ExerciseService.deleteExercise(user, exo);
        } else if (choix.trim().toLowerCase().equals("n") || choix.trim().toLowerCase().equals("non")) {
            return;
        } else {
            System.out.println("Veuillez entrer \"oui\", \"o\", \"non\" ou \"n\"");

            System.out.println("\nAppuyez sur la touche Entrée pour continuer...");
            sc.nextLine();
            deleteExercise(user, exo);
        }
    }
}
