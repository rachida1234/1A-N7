package allumettes;
public class ImplantationJeu implements Jeu {
    /**le nombre d'allumettes du jeu.*/
    private int nbAllumettes;
    /** Choisir le nombre d'allumettes pour entamer le jeu.
	 * @param allumettes nombre d'allumettes choisi pour le jeu.
     */
    public ImplantationJeu(int allumettes) {
        assert allumettes > 0;
        this.nbAllumettes = allumettes;
    }
    @Override
	public int getNombreAllumettes() {
        return nbAllumettes;
    }
    @Override
	public void retirer(int nbPrises) throws CoupInvalideException {
        if (nbPrises > PRISE_MAX && this.nbAllumettes > PRISE_MAX) {
            throw new CoupInvalideException(nbPrises, "> " + Jeu.PRISE_MAX);
            }
        if (nbPrises < 1) {
            throw new CoupInvalideException(nbPrises, "< 1");
            }
        if (nbPrises > this.nbAllumettes) {
            throw new CoupInvalideException(nbPrises, "> 1");
        }
        this.nbAllumettes -= nbPrises;
    }
}

