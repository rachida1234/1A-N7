package src.recettes;
import java.util.ArrayList;

/** ListeRecette permet d'ajouter et de
 * retirer des recettes de la liste
 * @author Rémi Hallopeau
 * @version 20/04/2022
 */

public class ListeRecette {
	private ArrayList<Recette> recette;
	
	public ListeRecette(ArrayList<Recette> r) {
		this.recette = r;
	}
	
	/** Permet d'ajouter une recette à la liste des recettes
	 * @param recette
	 */
	public void ajouterRecette(Recette recette) {
		assert(recette != null);
		this.recette.add(recette);
	}
	
	/** Permet de supprimer une recette de la liste des recettes
	 * @param recette
	 */
	public void retirerRecette(Recette recette) {
		assert(recette != null);
		this.recette.remove(recette);
	}
	
	public ArrayList<Recette> getListeRecettes() {
		return this.recette;
	}
	
}

