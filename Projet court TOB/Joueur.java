package allumettes;
public class Joueur {
     /**Nom du joueur.*/
     private String nom;
     /**la stratégie suivie par le joueur.*/
     private Strategie strategie;
    /**Construction du joueur.
    *@param nom
    *@param strategie
    */
     public Joueur(String nom, Strategie strategie) {
        assert nom != null && strategie != null;
        this.nom = nom;
        this.strategie = strategie;
    }
    /**Demander au joueur de choisir le nombre d'allumettes.
    *@param jeu
    *@param joueur
	*@return le nombre d'allumettes prise par le joueur
	*/
    int getPrise(Jeu jeu, Joueur joueur) {
        return this.strategie.getPrise(jeu, joueur);
    }
    /** Obtenir le nom du joueur.
		 * @return nom du joueur
		 */
    public String getNom() {
        return this.nom;
    }
    /** Obtenir la stratégie du joueur.
		 * @return nom du joueur
		 */
    public Strategie getStrategy() {
        return this.strategie;
    }
    /** Modifier la stratégie du joueur.
    * @param newStrategy
    */
    public void setStrategy(Strategie newStrategy) {
        assert newStrategy != null;
        this.strategie = newStrategy;
    }
}
