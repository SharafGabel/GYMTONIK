package console;

import model.ATraining;
import model.Exercise;
import model.SessionUser;
import model.User;
import service.ExerciseService;
import service.HistoriqueService;
import service.SessionService;
import util.Util;

import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

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
        List<ATraining> exercises = ExerciseService.getAllExercises();

        System.out.println("Nom de la séance :");
        String name = sc.nextLine();

        System.out.println("Date de la séance (yyyy-mm-dd) :");
        String dateString = sc.nextLine();

        Date sessionProgram = new Date();
        if (!dateString.trim().isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            try {
                sessionProgram = formatter.parse(dateString);
            } catch (ParseException pe) {
                System.out.println("Format de date invalide (yyyy-mm-dd)");
                System.out.println("Appuyez sur Entrée pour continuer...");
                sc.nextLine();
            }
        }

        SessionUser sessionUser = null;
        if (!name.trim().isEmpty() && sessionProgram != null) {
            sessionUser = new SessionUser(name, sessionProgram);
            sessionUser = SessionService.createSession(user, sessionUser);
        }

        if (sessionUser == null) {
            System.out.println("Échec de la création de la séance.");
            System.out.println("Appuyez sur la touche Entrée pour continuer...");
            sc.nextLine();
        } else {
            if (exercises.size() > 0) {
                System.out.println("Exercices à ajouter à la séance :");
                int i = 1;

                for (ATraining exo : exercises) {
                    System.out.println(i + " - " + exo.getName()
                                    + " : " + exo.getExplanation()
                                    + " - Niveau " + exo.getNiveau()
                                    + " (" + exo.getDureeExo() + "min) "
                                    + " (" + exo.getNbRepetition() + "répétitions) "
                    );
                    i++;
                }
                System.out.println("(Séparez vos choix par un espace)");
                String exos = sc.nextLine();

                try {
                    StringTokenizer st = new StringTokenizer(exos);
                    while (st.hasMoreTokens()) {
                        SessionService.addOrUpdateExToSession(
                                sessionUser,
                                exercises.get(Integer.parseInt(st.nextToken()) - 1));
                    }
                } catch (IndexOutOfBoundsException ioobe) {
                    System.out.println("Veuillez entrer un nombre entre 1 et " + exercises.size());
                    System.out.println("Appuyez sur la touche Entrée pour continuer...");
                    sc.nextLine();
                } catch (NumberFormatException nfe) {
                    System.out.println("Veuillez entrer des nombres.");
                    System.out.println("Appuyez sur la touche Entrée pour continuer...");
                    sc.nextLine();
                }
            }
        }

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
        } else System.out.println("Il n'y a aucun exercice pour cette séance");

        System.out.println( exs.size()+1 + " - Modifier la séance");
        System.out.println( exs.size()+2 + " - Supprimer la séance");
        System.out.println( exs.size()+3 + " - Retour");

        System.out.println("\nPour ajouter des exercices à cette séances, allez dans le menu Exercice\n");
        System.out.println("Sélectionnez l'exercice ou l'action que vous souhaitez effectuer :");

        int choix = sc.nextInt();
        sc.nextLine();

        if (exs.size() > 0) {
            if (choix > 0 && choix < exs.size() + 1) {
                doExercise(session, exs.get(choix - 1));
            }
        }

        if (choix == exs.size() + 1) {
            updateSession(session);
        }
        else if (choix == exs.size() + 2) {
            deleteSession(session);
        } else if (choix > exs.size() + 3) {
            System.out.println("Veuillez entrer un chiffre entre 1 et " + exs.size() + 2);

            System.out.printf("Appuyez sur la touche Entrée pour continuer...");
            sc.nextLine();
            displaySession(session);
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

    private static void updateSession(SessionUser sessionUser) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);
        List<ATraining> exercises = ExerciseService.getAllExercises();

        System.out.println("Modification de la séance " + sessionUser.getName());
        System.out.println("(Laissez le champ vide pour ne pas modifier la valeur)\n");

        System.out.println("Nom de la séance [" + sessionUser.getName() + "]");
        String name = sc.nextLine();

        System.out.println("Date de la séance [" + sessionUser.getDateProgram() + "]");
        String dateString = sc.nextLine();

        if (exercises.size() > 0) {
            System.out.println("Exercices à ajouter à la séance");
            int i = 1;

            for (ATraining exo : exercises) {
                System.out.println(i + " - " + exo.getName()
                                + " : " + exo.getExplanation()
                                + " - Niveau " + exo.getNiveau()
                                + " (" + exo.getDureeExo() + "min) "
                                + " (" + exo.getNbRepetition() + "répétitions) "
                );
                i++;
            }
            System.out.println("(Séparez vos choix par un espace)");
            String exos = sc.nextLine();

            try {
                StringTokenizer st = new StringTokenizer(exos);
                while (st.hasMoreTokens()) {
                    SessionService.addOrUpdateExToSession(
                            sessionUser,
                            exercises.get(Integer.parseInt(st.nextToken()) - 1));
                }
            } catch (IndexOutOfBoundsException ioobe) {
                System.out.println("Veuillez entrer un nombre entre 1 et " + exercises.size());
                System.out.println("Appuyez sur la touche Entrée pour continuer...");
                sc.nextLine();
            } catch (NumberFormatException nfe) {
                System.out.println("Veuillez entrer des nombres.");
                System.out.println("Appuyez sur la touche Entrée pour continuer...");
                sc.nextLine();
            }
        }

        if (!name.trim().isEmpty())
            sessionUser.setName(name);

        if (!dateString.trim().isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            Date sessionProgram = new Date();
            try {
                sessionProgram = formatter.parse(dateString);
                sessionUser.setDateProgram(sessionProgram);
            } catch (ParseException pe) {
                System.out.println("Format de date invalide (yyyy-mm-dd)");
            }
        }

        SessionService.updateSession(sessionUser);
    }

    private static void deleteSession(SessionUser sessionUser) {
        Util.clearConsole();
        System.out.println("Êtes-vous sûr de vouloir supprimer la session " + sessionUser.getName() + " ? [Oui/Non]");

        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();

        if (choix.trim().toLowerCase().equals("o") || choix.trim().toLowerCase().equals("oui")) {
            SessionService.deleteSession(sessionUser);
        } else if (choix.trim().toLowerCase().equals("n") || choix.trim().toLowerCase().equals("non")) {
            return;
        } else {
            System.out.println("Veuillez entrer \"oui\", \"o\", \"non\" ou \"n\"");

            System.out.println("\nAppuyez sur la touche Entrée pour continuer...");
            sc.nextLine();
            deleteSession(sessionUser);
        }
    }
}
