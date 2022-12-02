package allumettes;
public interface Strategie {
   /** Obtenir la prise correspondadnte à la stratégie.
	 * @param  jeuEnCours
	 * @param joueur
	 * @return nombre d'allumettes prises
	 */
    int getPrise(Jeu jeuEnCours, Joueur joueur);
}
