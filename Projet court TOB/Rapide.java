package allumettes;
/**La stratégie suivit par le joueur "Ordinateur" consiste à prendre.
 *le maximum des allumettes pour terminer la partie rapidement
 */
public class Rapide implements Strategie {
    @Override
    public int getPrise(Jeu jeu, Joueur j) {
      if (jeu.getNombreAllumettes() >= jeu.PRISE_MAX) {
                 return jeu.PRISE_MAX;
                 }
       if (jeu.getNombreAllumettes() == 2) {
                  return 2;
                  }
       return 1;
      }
}

