package src.agenda;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


/** Agenda repertorie les repas du jour de l'utilisateur,
 *  avec les recettes du midi et les recettes du soir
 * @author Rémi Hallopeau
 * @version 20/04/2022
 */

public class Agenda {
	private Map<LocalDate, RepasDuJour> listeRepas = new HashMap<>();
	
	public Agenda (Map<LocalDate, RepasDuJour> agenda){
		this.listeRepas = agenda;
	}
	
	/** Permet d'ajouter un repas à une certaine date
	 * dans l'agenda de l'utilisateur 
	 * @param repas
	 * @param date
	 */
	public void ajouter(RepasDuJour repas, LocalDate date) {
		assert date!=null;
		assert(repas!=null);
		this.listeRepas.put(date,repas);
	}
	
	/** Permet de supprimer le repas à une certaine date
	 * de l'agenda de l'utilisateur 
	 * @param repas
	 * @param date
	 */
	public void supprimer(RepasDuJour repas, LocalDate date) {
		assert date!=null;
		assert(repas!=null);
		this.listeRepas.remove(repas,date);
	}
	
	/** Obtenir la liste des repas du jour
	 * 
	 * @return liste des repas de la journée
	 */
	public Map<LocalDate, RepasDuJour> getListeRepas() {
		return this.listeRepas;
	}
}
