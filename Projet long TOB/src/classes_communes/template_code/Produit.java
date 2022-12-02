/** Produit modélise produit alimentaire qui porte un nom.
 * @author  Philippe WU
 * @version 09/04/22
 */

public class Produit {

    private String nom;

	/** Construire un produit à partir de son nom.
	 * @param nom le nom 
	 */    
    public Produit(String nom){
        this.nom = nom;
    }

	/** Obtenir le nom du produit.
	 * @return le nom du produit
	 */    
    public String getNom() {
        return this.nom;
    }
}