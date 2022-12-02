package allumettes;
import java.util.Scanner;
/**La stratégie suivie par le joueur "humain" consiste à donner.
 *le choix au joueur
*/
public class Humain implements Strategie {
    /**scanner de la bibliotheque Scanner.*/
    private  static Scanner scanner = new Scanner(System.in);
    /**une methode qui demande à l'utilisateur d'entrer le nombre de prises à prendre.
    *@param jeuCourant
    *@param joueur
    *@return prises
    */
    public int getPrise(Jeu jeuCourant, Joueur joueur) {
        int prises = 0;
        boolean estPrise = false;
        do {
             try {
                        //Lit l'entier écrit par le joueur et l'affecte
                        //à la variable 'prises' du programme
                 String entree = this.scanner.nextLine();
                 if (entree.contentEquals("triche")) {
                 System.out.println("[Une allumette en moins, plus que 4. Chut !]");
                   try {
                   jeuCourant.retirer(1);
                   System.out.print(joueur.getNom() + ", combien d'allumettes ? ");
                   } catch (CoupInvalideException h) {
                   System.out.println(" ");
                   }
                   } else {
                    prises = Integer.parseInt(entree);
                    estPrise = true;
                    }
             } catch (NumberFormatException e) {
                System.out.print("Vous devez donner un entier.\n "
              + joueur.getNom() + ", combien d'allumettes ? ");
                   }
		}
        while (!estPrise);
     return prises;
     }
}
