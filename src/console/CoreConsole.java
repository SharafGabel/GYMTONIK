package console;

import model.AUser;
import model.Exercise;
import service.ExerciseService;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by axeleroy on 03/03/2015.
 */
public class CoreConsole {
    public static void getConnectedHeader(AUser user){
        Util.clearConsole();
        System.out.println("#################### \n");
        System.out.println("Bonjour " + user.getUsername() + ", bienvenue sur GymTonik ! \n");
        System.out.println("#################### \n");
    }

    public static void getAnonymousHeader(){
        Util.clearConsole();
        System.out.println("#################### \n");
        System.out.println("Bonjour, bienvenue sur GymTonik. 1 - Connection 2 - Inscription\n");
        System.out.println("#################### \n");
    }
}
