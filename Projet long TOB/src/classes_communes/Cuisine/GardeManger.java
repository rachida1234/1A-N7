package src.Cuisine;
import java.util.ArrayList;
import java.util.Collections; 
/** Garde_manger modélise l'ensemble des ingredients disponibles dans 
 *  son garde manger (frigo ou placard).
 * @author  Rachida Oussakel
 */
public class GardeManger { 
	private ArrayList<IngredientGM> MaGardeManger ;
   
	/**Construction de la garde-manger à partir d'une liste d'ingredients.
	 * @param Contenu
	 */
    public GardeManger(ArrayList<IngredientGM> Contenu) {
    	this.MaGardeManger = Contenu;
	} 
    /**Récupérer la liste des ingrédients contenus dans la garde-manger.*/
	public ArrayList<IngredientGM> getIngredients() {
		return this.MaGardeManger;
    }
	
	/**Afficher tous les ingrédients contenus dans la garde manger.*/
	public void afficherContenu() {
		System.out.println("Votre garde-manger contient");
		for (IngredientGM produit :this.MaGardeManger) {
		    System.out.println(produit.getNom() + ":" + produit.getRayon() +" avec une quantité de "+produit.getQuantite() + " " + produit.getUnite());
		}
    }
	/**Ajouter un ingredient à la garde-manger.
	 * @param NewProduct Nouveau produit à ajouter
	 */
	public void ajouterIngredient(IngredientGM NewProduct) {
		assert NewProduct!=null;
		Collections.addAll(this.MaGardeManger,NewProduct);
	}
	
	/**Vérifier si un ingrédient existe dans la garde-manger.
	 * @param produit
	 * @return boolean
	 */
	public boolean estDedans(IngredientGM produit) {
		assert produit!=null;
		return this.MaGardeManger.contains(produit);
	}
	
	/**Supprimer un produit si sa quantité est nulle ou s'il est perimé.*/
	public void Refresh() {
		for (IngredientGM p :this.MaGardeManger) {
		    if (!p.estPerime()) {
				this.MaGardeManger.remove(p);
			}
		    else if (p.getQuantite()==0) {
		    	this.MaGardeManger.remove(p);
		    }
		}
	} 
    /**Retirer un ingredient de la garde-manger.
     * @param ingredient à enlever
     */
    public void retirerIngredientGM(IngredientGM ingredient) {
    	this.MaGardeManger.remove(ingredient);
    	
    }
   
    /**Retirer une quantite d'un ingredient de la garde-manger
     * 
     * @param nom de l'ingredient
     * @param qte quantite à retirer
     */
    public void retirerQuantite(String nom,double qte) {
    	for (IngredientGM p : this.MaGardeManger) {
    		if (p.getNom()==nom) {
    			p.setQuantite(p.getQuantite()-qte);
    		}
    	}
    }
   
    /**Rajouter une quantite d'un ingredient de la garde-manger
     * 
     * @param nom de l'ingredient
     * @param qte quantiter à ajouter
     */
    public void ajouterQuantite(String nom,double qte) {
    	for (IngredientGM p : this.MaGardeManger) {
    		if (p.getNom()==nom) {
    			p.setQuantite(p.getQuantite()+qte);
    		}
    	}
    }
}

