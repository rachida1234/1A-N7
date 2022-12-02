package allumettes;
public class Expert implements Strategie {
    /**redéfinition de le méthode getPrise en suivant cette fois-ci une stratégie experte.
    *@param jeu
    *@param joueur
    *@return prises
    */
    @Override
    public int getPrise(Jeu jeu, Joueur joueur) {
        if ((jeu.getNombreAllumettes() - 1) % (jeu.PRISE_MAX + 1) == 0) {
            return Math.min(jeu.PRISE_MAX, jeu.getNombreAllumettes());
        } else {
        return (jeu.getNombreAllumettes() - 1) % (jeu.PRISE_MAX + 1);
        }
        }
}
