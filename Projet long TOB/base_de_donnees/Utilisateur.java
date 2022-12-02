package src.base_de_donnees;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import src.Cuisine.*;
import src.Cuisine.Poubelle_it2.Poubelle;
import src.agenda.*;
import src.budget.Budget;
import src.liste_courses.*;
import src.recettes.*;
import src.supermarche.Supermarche;
import src.classes_communes.*;

/** Utilisateur comprend toutes les donnees relatives
 * à l'utilisateur
 * @author Rémi Hallopeau / Tanguy Muller
 * @version 17/05/2022
 */

public class Utilisateur{
	private String nom;
	private GardeManger gardeManger;
	private Poubelle poubelle;
	private ListeCourses listeCourses;
	private ListeRecette listeRecettes;
	private Agenda agenda;
	private Budget budget;
	
	private ListeRecette recettesApp;
	private List<Produit> produitsApp;
	private Supermarche carrefour;
	
	public static Utilisateur utilisateur;
	
	public Utilisateur(String nom) {
		this.nom = nom;
		this.gardeManger = new GardeManger(new ArrayList<>());
		this.poubelle = new Poubelle(new HashMap<>(),LocalDate.now());
		this.listeCourses = new ListeCourses(new ArrayList<>(),LocalDate.now());
		this.listeRecettes = new ListeRecette(new ArrayList<>());
		this.agenda = new Agenda(new HashMap<>());
		this.budget = new Budget(0, new ArrayList<>());
		initialiserAppli();
	}
	
	/** Obtenir le nom de l'utilisateur
	 * @return nom
	 */
	public String getNom() {
		return(this.nom);
	}
	
	/** Obtenir le garde-manger de l'utilisateur
	 * @return gardeManger
	 */
	public GardeManger getGardeManger() {
		return(this.gardeManger);
	}
	
	/** Obtenir la poubelle de l'utilisateur
	 * @return poubeelle
	 */
	public Poubelle getPoubelle() {
		return(this.poubelle);
	}
	
	/** Obtenir la liste de courses de l'utilisateur
	 * @return ListeCourses
	 */
	public ListeCourses getListeCourses() {
		return(this.listeCourses);
	}
	
	/** Obtenir la liste d'ingrédients de l'utilisateur
	 * @return ListeIngrédients
	 */
	public ListeRecette getListeRecettes() {
		return(this.listeRecettes);
	}
	
	/** Obtenir l'agenda des repas de l'utilisateur
	 * @return agenda
	 */
	public Agenda getAgenda() {
		return(this.agenda);
	}
	
	/** Obtenir le budget de l'utilisateur
	 * @return le budget
	 */
	public Budget getBudget() {
		return this.budget;
	}
	
	/** Initialiser les données de l'application
	 * 
	 */
	private void initialiserAppli() {
		this.recettesApp = new RecettesAppli().getListe();
		ProduitsAppli p = new ProduitsAppli();
		this.produitsApp = p.getListe();
		this.carrefour = p.getSupermarche();
	}
	
	public ListeRecette getRecettesApp() {
		return this.recettesApp;
	}
	
	public List<Produit> getProduitsApp() {
		return this.produitsApp;
	}
	
	public Supermarche getSupermarche() {
		return this.carrefour;
	}
}