package src.Cuisine;
import src.classes_communes.*;
import java.time.LocalDateTime;

import javax.print.DocFlavor.STRING;

import java.time.LocalDate;
public class IngredientGM extends Ingredient {

	private LocalDate date_peremption;
    private double Prix;
	/** Construction d'ingredientGM à partir d'Ingredient dans classe commune.
	* @param nom du produit
	* @param date de peremption
	* @param quantite
	* @param unite
	* @param rayon
	*/
	public IngredientGM(String nom, LocalDate date,double quantite,double prix ,String unite,String rayon) {
		super(nom,rayon, quantite, unite);
		this.date_peremption = date;
		this.Prix=prix;
		
	}
	/**Modifier le prix du produit.
    * @return prix
    */
	public void setPrix(double prix) {
		 this.Prix=prix;
	}
	
	/** Modifier la date de peremption du produit.
	* @param date la date de peremption du produit
	*/
	public void setDatePeremption(LocalDate date) {
		this.date_peremption=date;
	}
	 /**Obtenir le prix du produit.
	    * @return prix
	    */
		public double getPrix() {
			return this.Prix;
		}

    /** Récupérer la date de peremption du produit
	 * @return
	*/
	public LocalDate getDatePeremption() {
		return this.date_peremption;
	}

	/** Vérifier si un produit est périmé.
	*@return Boolean
	*/
	public Boolean estPerime() {
		return (this.date_peremption.isAfter(LocalDate.now())); 
	}
	
}

