package src.liste_courses;
import java.time.LocalDateTime;
import src.classes_communes.*;

/** IngredientCourses modélise l'ensemble des ingredients à ajouter dans la liste de course 
 * IngredientCourses est modélisé par son prix, son état de favori et les paramètres d'un Ingredient 
 * @author  Rachida Oussakel
 * @version 09/04/22
 */
public class IngredientCourses extends Ingredient {

	private double prix;
	private boolean favori;

	/** Construire un IngredientCourses à partir de son nom, son rayon au supermarche,
	 *  sa quantité, son unité, son prix et est par défaut non favori.
	 * @param nom le nom de l'ingrédient
	 * @param rayon le rayon où l'ingrédient est situé au supermarché
	 * @param qte la quantié de l'ingrédient
	 * @param unite son unité de mesure : g, L, u (unité)
	 * @param prix son prix en euros
	 * l'ingrédient n'est pas en favori par défaut.
	 */
	public IngredientCourses(String nom, String rayon, double qte, String unite, double prix) {
		super(nom, rayon, qte, unite);
		this.prix = prix;
		this.favori = false;
	}

    /** Obtenir le prix du produit.
    @return prix
    */
	public double getPrix() {
		return this.prix;
	}

	/** Modifier le prix du produit.
    @return prix
    */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/** Savoir si un produit est en favori.
    @return True si le produit est en favori
    */
	public boolean estFavori() {
		return this.favori;
	}
	
	/** Ajouter l'ingredient en favori.*/
	public void ajouterFavoris() {
		this.favori = true;
	}

	/** Enlever l'ingredient des favoris.*/
	public void supprimerFavoris() {
		this.favori = false;
	}
	
}

