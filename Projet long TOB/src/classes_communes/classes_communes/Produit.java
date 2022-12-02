package src.classes_communes;

/** Produit modélise un produit alimentaire qui est caractérisé par 
 *  son nom et le nom du rayon où il est situé au supermarché.
 * @author  Philippe WU
 * @version 09/04/22
 */
public class Produit {

	private String nom;
	private String rayon; // son rayon au supermarché

	/** Construire un produit à partir de son nom et le nom du rayon
	 *  où il est situé au supermarché.
	 * @param nom le nom 
	 * @param rayon le rayon où il est stocké au supermarché
	 */    	
	public Produit(String nom, String rayon) {
		this.nom = nom;
		this.rayon = rayon;
	}
	
	/** Obtenir le nom du produit.
	 * @return le nom du produit
	 */    	
	public String getNom() {
		return this.nom;
	}

	/** Obtenir le nom du rayon où est situé produit au supermarché.
	 * @return le nom du rayon
	 */    	
	public String getRayon() {
		return this.rayon;
	}
}

