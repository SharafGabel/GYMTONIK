package console;

import model.SessionUser;
import model.User;
import service.SessionService;
import util.Util;

import java.util.List;
import java.util.Scanner;

/**
 * Created by axeleroy on 03/03/2015.
 */
public class SessionConsole {
    public static void menu(User user) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);
        List<SessionUser> seances = SessionService.getSessionList(user);

        System.out.println("-- Séances --\n");
        System.out.println("1 - Créer une session");
        listSession(seances);

        /* choix_seance = 0 dans le cas où l'utilisateur n'aurait aucun exercice
           choix_seance = nb de séance + 1 sinon
         */
        int choix_seance = (seances.size() > 0)? seances.size() + 2 : 1;

        int choix = sc.nextInt();
        if (choix == 1) {
            createSession(user);
            menu(user);
        } else if (choix > 1 && choix < choix_seance + 1) {
            editSession(seances.get(choix - 2));
            menu(user);
        } else if (choix > choix_seance) {
            return;
        } else {
            System.out.println("Veuillez saisir une valeur entre 0 et " + choix_seance );
            menu(user);
        }
    }

    private static void listSession(List<SessionUser> seances) {
        if (seances.size() > 0) {
            int i = 2;
            for (SessionUser s : seances) {
                System.out.println(i + " - " + s.getName() + " - " + s.getDateProgram());
                i++;
            }
        }
    }

    private static void editSession(SessionUser session) {

    }

    private static void createSession(User user) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);

        System.out.println("Création d'une Séance\n");
        System.out.println("Combien d'heures avez-vous dormi cette nuit ?");
        String sommeil = sc.next();

        SessionService.addSession(user, sommeil);
    }
}
