package allumettes;
/**le joueur choisit de tricher.*/
public class Tricheur implements Strategie {
   @Override
    public int getPrise(Jeu jeu, Joueur j) {
        assert jeu != null;
        System.out.println("[Je triche...]");
        try {
            while (jeu.getNombreAllumettes() > 2) {
                jeu.retirer(1);
                }
                System.out.println("[Allumettes restantes : 2]");
        } catch (CoupInvalideException e) {
            System.out.println(" ");
         }
        return 1;
        }
}
