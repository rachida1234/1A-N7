package src.classes_communes;

/** Ingredient modélise un produit alimentaire avec sa quantité et son unité de mesure.
 * @author  Philippe WU
 * @version 09/04/22
 */
public class Ingredient extends Produit {
    
    private double quantite;
    private String unite;		// unité en g (gramme) ou en L (litre) ou en u (unité)

	/** Construire un ingredient à partir de son nom, son rayon au supermarché, sa
	 * quantité et son unité de mesure.
	 * @param nom son nom
	 * @param rayon son rayon au supermarché
	 * @param quantite sa quantité
	 * @param unite son unité de mesure
	 */
    public Ingredient(String nom, String rayon, double quantite, String unite) {
		super(nom, rayon);
		this.quantite = quantite;
        this.unite = unite;
    }

	/** Obtenir la quantité restante de l'ingrédient. 
	 * @return la quantité restante de l'ingrédient
	 */
    public double getQuantite() {
		return this.quantite;
	}
	
	/** Obtenir l'unité de mesure de l'ingrédient.
	 * @return l'unité de mesure de l'ingrédient.
	 */    	
	public String getUnite() {
		return this.unite;
	}
	
	/** Modifier la quantité de l'ingrédient.
	*/
	public void setQuantite(double qte) {
		this.quantite = qte;
    }
	
	public void incQuantite() {
		this.quantite ++;
	}
	
	public void decQuantite() {
		this.quantite --;
	}
}