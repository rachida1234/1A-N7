package src.agenda;
import src.recettes.*;

/** RepasDuJour contient les différents repas d'un jour
 * du midi et du soir de l'utilisateur
 * @author Rémi Hallopeau
 * @version 20/04/2022
 */

public class RepasDuJour {
	
	private Recette recetteMidi;
	private Recette recetteSoir;
	
	public RepasDuJour(Recette recetteMidi, Recette recetteSoir) {
		this.recetteMidi = recetteMidi;
		this.recetteSoir = recetteSoir;
	}
	
	public Recette getRecetteMidi() {
		return this.recetteMidi;
	}
	
	public Recette getRecetteSoir() {
		return this.recetteSoir;
	}
	
	public void setRecetteMidi(Recette recette) {
		assert(recette!=null);
		this.recetteMidi = recette;
	}
	
	public void setRecetteSoir(Recette recette) {
		assert(recette!=null);
		this.recetteSoir = recette;
	}
	
}