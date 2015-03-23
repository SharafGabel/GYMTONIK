package console;

import model.*;
import org.hibernate.NonUniqueObjectException;
import service.ExerciseService;
import service.SessionService;
import util.Util;

import java.util.List;
import java.util.Scanner;

public class ExerciseConsole {
    public static void menu(AUser user) {
        CoreConsole.getConnectedHeader(user);
        Scanner sc = new Scanner(System.in);
        List<ATraining> exos = ExerciseService.getExercises();

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

    private static void listExercise(List<ATraining> exos) {
        int i = 2;
        for (ATraining exo : exos) {
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
            ExerciseService.createExercise(user, description, name, length, nbRep, niveau);
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

    private static void displayExercise(AUser user, ATraining exo) {
        Util.clearConsole();
        System.out.println("Exercice " + exo.getName());
        System.out.println(exo.getExplanation());
        System.out.println(exo.getDureeExo() + "min - Niveau " + exo.getNiveau()
                + " - " + exo.getNbRepetition() + " répétitions\n");

        if(user.equals(exo.getUser())) { // L'utilisateur est le propriétaire de l'exercice, il peut donc le modifier et le supprimer

            System.out.println("1 - Modifier");
            System.out.println("2 - Supprimer");
            System.out.println("3 - Ajouter à une séance");
            System.out.println("4 - Retour");

            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();

            switch (choix) {
                case 1:
                    updateExercise(exo);
                    break;
                case 2:
                    deleteExercise(user, exo);
                    break;
                case 3:
                    addToSession(user, exo);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Veuillez entrer un chiffre entre 1 et 4");
                    System.out.println("Appuyez sur la touche Entrée pour continuer");
                    sc.nextLine();
                    sc.nextLine();
                    displayExercise(user, exo);
                    break;
            }
        } else {
            System.out.println("1 - Ajouter à une séance");
            System.out.println("2 - Retour");

            Scanner sc = new Scanner(System.in);
            int choix = sc.nextInt();

            switch (choix) {
                case 1:
                    addToSession(user, exo);
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Veuillez entrer un chiffre entre 1 et 2");
                    System.out.println("Appuyez sur la touche Entrée pour continuer");
                    sc.nextLine();
                    sc.nextLine();
                    displayExercise(user, exo);
                    break;
            }
        }

    }

    private static void updateExercise(ATraining exo) {
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

        try {
            length = Integer.parseInt(lengthString);
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

        if(nbRepet >= 5 && nbRepet < 500)
            exo.setNbRepetition(nbRepet);

        if (length > 20)
            System.out.println("La durée de l'exercice ne peut pas excéder 20 minutes");
        if (nbRepet != 0 && (nbRepet < 5 || nbRepet > 500))
            System.out.println("Le nombre de répétitions de l'exercice doit être comprit entre 5 et 500");

        ExerciseService.updateExercise(exo);

        System.out.println("\nAppuyez sur la touche Entrée pour continuer...");
        sc.nextLine();
    }

    private static void deleteExercise(AUser user, ATraining exo) {
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

    private static void addToSession(AUser user, ATraining exo) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);

        List<SessionUser> sessions = SessionService.getSessionList((User)user);

        System.out.println("Ajout de l'exercice " + exo.getName() + " à une séance\n");

        if (sessions.size() == 0) {
            System.out.println("Vous n'avez aucune séance d'enregistrée");
        } else {
            int i = 1;
            for(SessionUser s : sessions) {
                System.out.println(i + " - " + s.getName() + " du " + s.getDateProgram());
                i++;
            }

            int choix = sc.nextInt();
            sc.nextLine();
            choix--;
            if (choix >= 0 && choix < sessions.size()) {
                try {
                    SessionService.addOrUpdateExToSession(sessions.get(choix).getIdS(), exo.getId());
                    System.out.println("L'exercice " + exo.getName() + " a été ajouté à la " + sessions.get(choix).getName());
                } catch (NonUniqueObjectException e) {
                    System.out.println("L'exercice est déjà présent dans la séance choisie.");
                }
            } else System.out.println("Veuillez saisir un nombre entre 1 et " + sessions.size());
        }
        System.out.println("Appuyez sur Entrée pour continuer...");
        sc.nextLine();
    }
}
