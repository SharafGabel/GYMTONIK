package console;

import com.sun.javafx.geom.AreaOp;
import model.AUser;
import model.Exercise;
import model.SessionUser;
import model.User;
import service.ExerciseService;
import service.SessionService;
import util.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by axeleroy on 03/03/2015.
 */
public class ExerciseConsole {
    public static void menu(AUser user) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);

        System.out.println("-- Exercice --\n");
        System.out.println("1 - Créer un exercice");
        System.out.println("2 - Voir vos exercices");

        int choix = sc.nextInt();
        switch(choix){
            case 1:
                createExercise(user);
                menu(user);
                break;
            case 2:
                listExercise(user);
                menu(user);
                break;
            default:
                System.out.println("Veuillez saisir une valeur entre 0 et " + 2 );
                menu(user);
                break;
        }

    }

    private static void listExercise(AUser user) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);
        ArrayList<Exercise> exos = (ArrayList<Exercise>)ExerciseService.getUserExercises(user);
        for(Exercise exo : exos){
            System.out.println("===========");
            System.out.println("Nom: "+exo.getName());
            System.out.println("Nom: "+exo.getExplanation());
            System.out.println("Nom: "+exo.getLength());
            System.out.println("===========");
        }

        System.out.println("Liste des exercices\n");
    }

    private static void createExercise(AUser user) {
        Util.clearConsole();
        Scanner sc = new Scanner(System.in);

        System.out.println("Création d'un exercice\n");
        System.out.println("Nom de l'exercice");
        String name = sc.nextLine();
        System.out.println("Description de l'exercice");
        String description = sc.next();
        System.out.println("Durée de l'exercice");
        int length = sc.nextInt();

        ExerciseService.createExercise(user, name, description, length);

        System.out.print("Ajout reussi");
    }
}
