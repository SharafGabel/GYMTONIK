package console;

import model.AUser;
import service.ProfilService;

import java.util.Scanner;

public class ProfilConsole {
    public static void profilRouting(AUser user, int scenario){
        boolean error = true; int entry = 0;
        while(error) {
            switch (scenario) {
                case 1:
                    Scanner sc = new Scanner(System.in);
                    Scanner sc2 = new Scanner(System.in);
                    try{
                        entry = sc.nextInt();
                        System.out.println(entry);
                        error = false;
                    }catch (Exception e){
                    }
                    switch (entry){
                        case 1:
                            System.out.println("Entrez votre nouveau nom d'utilisateur: ");
                            String username = sc2.nextLine();
                            ProfilService.changeUsername(user, username);
                            break;
                        case 2:
                            System.out.println("Entrez votre nouveau mail: ");
                            String email = sc2.nextLine();
                            ProfilService.changeEmail(user, email);
                            break;
                        case 3:
                            System.out.println("Entrez votre nouvelle taille : ");
                            int height = sc2.nextInt();
                            ProfilService.changeHeight(user, height);
                            break;
                        case 4:
                            System.out.println("Entrez votre nouveau poid: ");
                            int weight = sc2.nextInt();
                            ProfilService.changeWeight(user, weight);
                            break;
                        case 5:
                            error = false;
                            break;
                        default:
                            System.out.println("Veuillez entre un nombre entre 1 et 4");
                            break;
                    }
                    break;

            }
        }
    }

    public static void showProfil(AUser user){
        System.out.println("Voici vos informations personnelles :" + "\n");
        System.out.println("Nom d'utilisateur: " + user.getUsername());
        System.out.println("Adresse email: " + user.getEmail());
        System.out.println("Taille: " + user.getHeight());
        System.out.println("Poid: " + user.getWeight());

        editProfil(user);
    }

    public static void editProfil(AUser user){
        System.out.println("Pour modifier votre profil tapez:" + "\n");
        System.out.println("1 - Modifier votre nom d'utilisateur");
        System.out.println("2 - Modifier votre adresse email");
        System.out.println("3 - Modifier votre taille");
        System.out.println("4 - Modifier votre poid");
        System.out.println("5 - Retour");

        profilRouting(user, 1);
    }
}
