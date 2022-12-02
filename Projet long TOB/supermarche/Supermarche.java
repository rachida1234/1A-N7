package src.supermarche;

import java.util.List;

import src.liste_courses.ListeCourses;

/** Supermarché modélise un supermarché avec son nom et sa liste de produits.
 * @author  Hugo DUPUIS
 * @version 18/04/22
 */
public class Supermarche {
	
	private String nom;
	private List<ProduitSupermarche> produits;
	private ListeCourses listecourse;
	
	/** Constructeur d'un supermarché avec son nom et sa liste de produits
	 * 
	 * @param nom nom du supermarché
	 * @param produits liste de produits qu'il contient
	 */
	public Supermarche(String nom, List<ProduitSupermarche> produits, ListeCourses listeCourse) {
		this.nom = nom;
		this.produits = produits;
		this.listecourse = listeCourse;
	}
	
	/** Obtenir le nom du supermarché
	 * 
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}
	/** Obtenir les produits du supermarché
	 * 
	 * @return produits
	 */
	public List<ProduitSupermarche> getProduitSupermarche(){
		return this.produits;
	}
	
	public ListeCourses getListecourse() {
		return this.listecourse;
	}
	
	/** Détermine si un produit voulu est présent dans le supermarché
	 * 
	 * @param produit produit voulu
	 * @return si il est présent ou non
	 */
	public boolean estPresent(ProduitSupermarche produit) {
		return (this.produits.contains(produit));
	}
	
	/** Ajoute un produit dans le supermarché
	 * 
	 * @param produit produit à ajouter
	 */
	public void ajouterProduit(ProduitSupermarche produit) {
		this.produits.add(produit);
	}
	
	/** Supprime un produit du supermarché
	 * 
	 * @param produit produit à supprimer
	 */
	public void supprimerProduit(ProduitSupermarche produit) {
		this.produits.remove(produit);
	}
	
	/** Applique une promotion à un produit du supermarché
	 * 
	 * @param produit produit sur lequel on applique la réduction
	 * @param reduction réduction à appliquer
	 */
	public void appliquerPromo(ProduitSupermarche produit, int reduction) {
		produit.setPrix(produit.getPrix()*(1-reduction/100));
		produit.setPromo();
	}

}
