package console;

import model.AUser;
import util.Util;

/**
 * Created by axeleroy on 03/03/2015.
 */
public class CoreConsole {
    public static void getConnectedHeader(AUser user){
        Util.clearConsole();
        System.out.println("################### \n");
        System.out.println("Bonjour " + user.getUsername() + ", bienvenue sur GymTonik ! \n");
        System.out.println("################### \n");
    }

    public static void getAnonymousHeader(){
        Util.clearConsole();
        System.out.println("################### \n");
        System.out.println("Bonjour, bienvenue sur GymTonik.\n1 - Connection\n2 - Inscription\n");
        System.out.println("################### \n");
    }
}
