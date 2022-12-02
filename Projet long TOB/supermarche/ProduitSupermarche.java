package src.supermarche;

import src.classes_communes.Produit;

/** ProduitSupermarché modélise un produit, dans un supermarché, avec son prix.
 * @author  Hugo DUPUIS
 * @version 18/04/22
 */
public class ProduitSupermarche extends Produit {

	private double prix;
	private String unite;
	private boolean promo;
	
	/** Constructeur d'un produit de supermarché
	 * 
	 * @param nom nom du produit
	 * @param rayon rayon dans lequel il est stocké
	 * @param prix prix du produit, dans le supermarché associé
	 * @param unite unite de mesure de produit en kilo(kg) ou litre (L)
	 */
	public ProduitSupermarche(String nom, String rayon, double prix,String unite) {
		super(nom, rayon);
		this.prix = prix;
		this.promo = false;
		this.unite = unite;
	}
	
	/** Obtenir le prix du produit
	 * 
	 * @return prix du produit
	 */
	public double getPrix() {
		return this.prix;
	}
	
	/** Obtenir l'unite de mesure
	 * 
	 * @return l'unite de mesure
	 */
	public String getUnite() {
		return this.unite;
	}
	
	/** Modifie le prix du produit
	 * 
	 * @param newprix nouveau prix
	 */
	public void setPrix(double newprix) {
		this.prix = newprix;
	}
	
	public boolean estPromo() {
		return this.promo;
	}
	
	public void setPromo() {
		this.promo = true;
	}
	
	public void retirerPromo() {
		this.promo = false;
	}
	
	
	
}
