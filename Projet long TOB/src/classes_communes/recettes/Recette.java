package src.recettes;
import src.classes_communes.*;
import src.liste_courses.IngredientCourses;
import src.liste_courses.ListeCourses;

import java.util.ArrayList;

import javax.swing.ImageIcon;

/** Recette modélise une recette alimentaire qui est caractérisé par son nom,
 *  sa liste d'ingrédients, si elle est en favorite, son temps de préparation,
 *  son temps de cuisson et le nombre de personnes.
 * @author Hugo Dupuis
 * @version 09/04/2022
 */
public class Recette {
	
	private ArrayList<Ingredient> ingredients;
	private String nom;
	private boolean estFavorite;
	private int tempsPreparation;
	private int tempsCuisson;
	private int nbPersonnes;
	private ImageIcon image;
	
	/** Constructeur d'une recette.
	 * 
	 * @param s nom de la recette
	 * @param l liste des ingrédients
	 * @param tp temps de préparation de la recette
	 * @param tc temps de cuisson de la recette
	 * @param nb nombre de personnes de la recette
	 */
	public Recette(String s, ArrayList<Ingredient> l, int tp, int tc, int nb, ImageIcon image) {
		this.nom = s;
		this.ingredients = l;
		this.estFavorite = false;
		this.tempsPreparation = tp;
		this.tempsCuisson = tc;
		this.nbPersonnes = nb;
		this.image = image;
	}
	
	/** Retourne la liste des ingrédients.
	 * @return liste des ingrédients
	 */
	public ArrayList<Ingredient> getIngredients() {
		assert(this.ingredients != null);
		return this.ingredients;
	}
	
	/** Obtenir le nom de la recette.
	 * @return le nom de la recette
	 */
	public String getNom() {
		return this.nom;
	}
	
	/** Obtenir le temps de préparation de la recette.
	 * @return temps de préparation
	 */
	public int getTempsPreparation() {
		return this.tempsPreparation;
	}
	
	/** Obtenir le temps de cuisson de la recette.
	 * @return temps de cuisson
	 */
	public int getTempsCuisson() {
		return this.tempsCuisson;
	}
	
	/** Obtenir le nombre de personnes de la recette.
	 * @return nb de personnes
	 */
	public int getNbPersonnes() {
		return this.nbPersonnes;
	}
	
	/** Déterminer si une recette est favorite ou non.
	 * @return attribut estFavorite
	 */
	public boolean estFavorite() {
		return this.estFavorite;
	}
	
	public ImageIcon getImage() {
		return this.image;
	}
	
	// Gestion de la liste des ingrédients
	
	/** Ajouter un ingrédient à la recette
	 * @param ing nouvel ingrédient
	 */
	public void ajouter(Ingredient ing) {
		this.ingredients.add(ing);
	}
	
	/** Supprimer un ingrédient de la recette.
	 * @param ing ingrédient à supprimer
	 */
	public void supprimer(Ingredient ing) {
		assert(ing != null);
		this.ingredients.remove(ing);
	}
	
	// Fonctions qui concernent la partie favoris

	/** Ajouter une recette en favoris
	 */
	public void ajouterFavoris() {
		assert(!this.estFavorite);
		this.estFavorite = true;
	}
	
	/** Supprimer une recette des favoris
	 */
	public void supprimerFavoris() {
		assert(this.estFavorite);
		this.estFavorite = false;
	}
	
	// Changer la recette en fonction du nombre de personnes
	
	/** Modifie le nombre de personne d'une recette
	 * @param newnb nouveau nombre de personnes
	 */
	public void modifierNbPersonnes(int newnb) {
		double rapport = (double) newnb / this.nbPersonnes;
		for (Ingredient ingredient : this.ingredients) {
			ingredient.setQuantite(rapport*ingredient.getQuantite());
		}
		this.nbPersonnes = newnb;
	}
	
	public boolean estSemblable(Recette autreRecette) {
		int nbIngSimil = 0;
		for (Ingredient ing : this.ingredients) {
			if (autreRecette.getIngredients().contains(ing)) {
				nbIngSimil++;
			}
		}
		return (nbIngSimil > this.ingredients.size()/2);
	}
	
	public void ajouterListeCourses(ListeCourses l) {
		for (Ingredient ing : this.ingredients) {
			IngredientCourses newing = new IngredientCourses(ing.getNom(), ing.getRayon(), ing.getQuantite(), ing.getUnite(), 1.0);
			l.ajouterIngredient(newing);
		}
	}
}

