import console.LoginConsole;
import console.RegisterConsole;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import service.GetList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int NB_CHOIX = 2;

    public static void main(final String[] args) throws Exception {
        System.out.println("Bienvenue sur Gym Tonik !\n" +
                "Que voulez-vous faire ?\n");

        print_menu();

        int choix = choix();

        boolean reussi = false;
        switch (choix) {
            case 1:
                reussi = RegisterConsole.register();
                break;
            case 2:
                reussi = LoginConsole.login();
                break;
        }

        System.out.println(reussi);

    }

    private static int choix() {
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();

        if (choix < 0 || choix > NB_CHOIX) {
            System.out.println("Veuillez saisir un nombre entre 0 et " + NB_CHOIX);
            choix();
        }

        return choix;
    }

    private static void print_menu() {
        System.out.println("1 - Inscription");
        System.out.println("2 - Connexion");
        System.out.println("\n");
    }
}
