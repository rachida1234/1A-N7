package allumettes;
import javax.naming.ConfigurationException;

/** Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 * @author Xavier Crégut
 * @version $Revision: 1.5 $
 */
public class Jouer {
        /** Lancer une partie. En argument sont donnés les deux joueurs sous
        * la forme nom@stratégie.
        * @param args la description des deux joueurs
        */
        public static void main(String[] args) {
        	final int nbrAllumettes = 13;
            String etat = new String("Non_confiant");
            int commande1 = 0;
            int commande2 = 1;
            try {
            verifierNombreArguments(args);
            Jeu jeuEncours = new ImplantationJeu(nbrAllumettes);
            if (args.length == Jeu.PRISE_MAX && args[0].equals("-confiant")) {
                  etat = "confiant";
                  commande1 = 1;
                  commande2 = 2;
            } else if (args.length == 2) {
                  commande1 = 0;
                  commande2 = 1;
             } else {
                 throw new ConfigurationException(" ");
             }
            String commande = new String(args[commande1] + "@" + args[commande2]);
            String[] partiesCommande = commande.split("@");
            Joueur joueur1 = new Joueur(
            partiesCommande[0], getStrategie(partiesCommande[1]));
            Joueur joueur2 = new Joueur(
            partiesCommande[2], getStrategie(partiesCommande[jeuEncours.PRISE_MAX]));
            Arbitre arbitre = new Arbitre(joueur1, joueur2);
            arbitre.arbitrer(jeuEncours, etat);
            } catch (ConfigurationException e) {
            System.out.println("Erreur : " + e.getMessage());
            afficherUsage();
            System.exit(1);
            } catch (ArrayIndexOutOfBoundsException e) { }
            }
    /**retourner à partir du nom d'une stratégie celle suivit par le joueur.
    * @param strategy
    * @return Startegie
    */
    public static Strategie getStrategie(String strategy)throws ConfigurationException {
       Strategie strategie;
            switch (strategy) {
            case "naif" :
                strategie = new Naif();
                break;
            case "rapide" :
               strategie = new Rapide();
               break;
            case "expert":
               strategie = new Expert();
               break;
            case "humain":
               strategie = new Humain();
               break;
            case "tricheur":
               strategie = new Tricheur();
               break;
            default :
               throw new ConfigurationException(" ");
               }
            return strategie;
            }
      private static void verifierNombreArguments(String[] args)
      throws ConfigurationException {
            final int nbJoueurs = 2;
            if (args.length < nbJoueurs) {
            throw new ConfigurationException("Trop peu d'arguments : "
            + args.length);
            }
            if (args.length > nbJoueurs + 1) {
            throw new ConfigurationException("Trop d'arguments : " + args.length);
            }
            }
  /** Afficher des indications sur la manière d'exécuter cette classe. */
    public static void afficherUsage() {
        System.out.println("\n" + "Usage :"
        + "\n\t" + "java allumettes.Jouer joueur1 joueur2"
        + "\n\t\t" + "joueur est de la forme nom@stratégie"
        + "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
        + "\n"
        + "\n\t" + "Exemple :"
        + "\n\t" + " java allumettes.Jouer Xavier@humain "
          + "Ordinateur@naif"
        + "\n"
        );
        }
}

