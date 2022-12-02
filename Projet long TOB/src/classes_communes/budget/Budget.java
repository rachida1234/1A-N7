package src.budget;
import java.time.LocalDate;
import java.util.*;

import src.Cuisine.IngredientGM;
import src.liste_courses.ListeCourses; 

/** Budget modélise les dépenses de l'utilisateur.
 * @author  Philippe Wu
 * @version 09/04/22
 */
public class Budget { 
	
    private double depenseTotal;
    private ArrayList<ListeCourses> historiqueAchats;
   
	/** Construire un budget à partir de ses dépenses totales et son historique d'achats. ff
	 * @param double depenseTotal
     * @param ArrayList<ListeCourses> historiqueAchats
	 */
    public Budget(double depenseTotal, ArrayList<ListeCourses> historiqueAchats) {
		this.depenseTotal = depenseTotal;
        this.historiqueAchats = historiqueAchats;
	}
    /** Obtenir l'historique d'achats.
     */
    public ArrayList<ListeCourses> getHistoriqueAchat() {
    	return this.historiqueAchats;
    }

    /** Obtenir les dépenses entre une période de début et une période de fin.
     * @return depense
     */
    public double getDepensePeriode(LocalDate debut, LocalDate fin) {
    	double budget = 0; 
		for ( ListeCourses courses : this.historiqueAchats ) {
		    LocalDate date = courses.getDateCourse();
			if (date.isBefore(fin)&& date.isAfter(debut)) {
			      budget+=courses.getBudgetCourse();
		}
		}
		this.depenseTotal=budget;
		return budget;
    }
    

    /** Ajouter une liste de courses dans l'historique des achats
     * 
     */
    public void ajouterListeCourses(ListeCourses courses) {
        this.historiqueAchats.add(courses);
        this.depenseTotal = this.depenseTotal + courses.getBudgetCourse();
    }
}