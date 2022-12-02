package src.Cuisine.Poubelle_it2;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import src.Cuisine.IngredientGM;
/** Poubelle modélise une liste de produits périmés jetés.
 * @author  Rachida Oussakel
 * @version 06/04/22
 */
public class Poubelle {
	private Map<IngredientGM,LocalDate> produitsJetes = new HashMap<>();
	private LocalDate datePoubelleVide;
	/**Construction de la poubelle à partir d'une map de produits avec leurs dates de rejet.
	*@param poubelle
	*/
	public Poubelle(Map<IngredientGM,LocalDate> poubelle,LocalDate date) {
		this.produitsJetes = poubelle;
		this.datePoubelleVide=date;
	}
	/**Récupérer la dernière date de vidage de la poubelle.*/
    public LocalDate getDernierVidagePoubelle() {
    	return this.datePoubelleVide;
    }
	/**Récupérer la liste des produits jetés.*/
	public Map<IngredientGM,LocalDate> getProduitsJetes() {
		return produitsJetes;		 
	}
	/**Modifier la date de vidage de la poubelle.*/
    public void setDernierVidagePoubelle(LocalDate date) {
    	assert date!=null;
    	this.datePoubelleVide=date;
    }
	/**Le nombre total de produits gaspillés*/
	public int getNbTotalGaspille() {
		return produitsJetes.size();
	}
	/**Calcul du budget du gaspillage.*/
	public double getTotalBudgetGaspille() {
		double budget = 0; 
		for(IngredientGM p : produitsJetes.keySet()) {
		     budget+=p.getPrix()*p.getQuantite();
		} 
		return budget;
	}
	/**Calculer le budget du gaspillage entre deux dates limites.
	* @param dateDebut
	* @param dateFin
	*/
	public double getBudgetGaspillePeriode(LocalDate dateDebut,LocalDate dateFin) {
		double budget = 0; 
		for ( Map.Entry<IngredientGM,LocalDate> entry : produitsJetes.entrySet() ) {
		    IngredientGM produit = entry.getKey();
		    LocalDate date = entry.getValue();
			if (date.isBefore(dateFin)&& date.isAfter(dateDebut)) {
			      budget+=produit.getPrix()*produit.getQuantite();
		}
		}
		return budget;
	}
	/**Jeter les produits perimés.
	*@param produit produit à jeter
	*/
	public void jeterDansPoubelle(IngredientGM produit) {
		    this.produitsJetes.put(produit,LocalDate.now()); 
	}
	/**Vider la poubelle de tous les produits qui sont dedans.*/
	public void viderPoubelle() {
	     this.produitsJetes.clear();
	}
}
