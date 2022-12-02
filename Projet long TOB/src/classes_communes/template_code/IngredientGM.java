import java.time.LocalDateTime;
import src.classes_communes.Produit;

/** Ingredient_gm modélise un produit situé au garde manger (frigo ou placard).
 *  L'ingrédient se définit par le nom du produit, sa quantité et sa date de péremption
 * @author  Philippe WU
 * @version 09/04/22
 */

public class IngredientGM extends Produit {
    
    private double quantite;    // double pour le moment pour éviter les erreurs d'opération
    private char unite;         // 'L', 'g' ou 'u' (unitaire)
    private LocalDateTime datePeremption;

	/** Construire un ingredient du garde manger à partir de son nom, sa
	 * quantité et son unité de mesure.
	 * @param nom son nom
	 * @param quantite sa quantité
	 * @param unite son unité
     * @param date sa date de péremption
	 */
    public IngredientGM(String nom, double quantite, char unite, LocalDateTime date) {
        super(nom);
        this.quantite = quantite;
        this.unite = unite;
        this.datePeremption = date;
    }

	/** Obtenir la quantité restante d'un produit. */
	public double getQuantite() {
		return this.quantite;
	}

	/** Obtenir l'unité de mesure de la quantité du produit. */
	public char getUnite() {
		return this.unite;
	}

    /** Récupérer la date de peremption du produit*/
	public LocalDateTime getDatePeremption() {
		return this.datePeremption;
	}

	/** Modifier la quantité du produit.*/
	public void setQuantite(float qte) {
		this.quantite = qte;
	}

	/** Vérifier si un produit est périmé.*/
	public Boolean estPerime() {
		return (this.datePeremption.isAfter(LocalDateTime.now())); 
	}    
}
