package allumettes;

public class Proccuration implements Jeu {
    /** Jeu à implanter.*/
    private ImplantationJeu jeu;
    /**Construction du Proxy_jeu à partir d'un jeu réel.
    * @param jeuReel
    */
    public Proccuration(ImplantationJeu jeuReel) {
        assert jeuReel != null;
        this.jeu = jeuReel;
    }
    @Override
	public int getNombreAllumettes() {
        return jeu.getNombreAllumettes();
    }
    @Override
	public void retirer(int nbPrises) throws OperationInterditeException {
       throw new OperationInterditeException("Abandon de la partie car");
    }
}
