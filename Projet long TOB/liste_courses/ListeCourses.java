package src.liste_courses;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import src.Cuisine.GardeManger;
import src.Cuisine.IngredientGM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
/** ListeCourses modélise l'ensemble des ingredients à acheter .
 * @author  Rachida Oussakel
 * @version 09/04/22
 */
public class ListeCourses { 
	private ArrayList<IngredientCourses> course;
    private LocalDate dateCourses;
	/** Construire une liste de courses à partir d'une liste d'ingredients.
	 * @param liste
	 */
    public ListeCourses(ArrayList<IngredientCourses> liste,LocalDate date) {
		this.course = liste;
		this.dateCourses=date;
	} 
    
    /**Récupérer la dernière date de courses.*/
    public LocalDate getDateCourse() {
    	return this.dateCourses;
    }
    /**calculer le budget total de la liste de courses.*/
    public float getBudgetCourse() {
    	float prix_total = 0;
		for (IngredientCourses prod :this.course) {
			prix_total += prod.getPrix()*prod.getQuantite();
		}
		return prix_total;
    }
    
    /** Afficher la liste des ingrédients contenus dans la liste de course.
	*/
	public void afficherTexte() {
		System.out.println("Votre liste de course contient : ");
		for (IngredientCourses ing :this.course) {
		    System.out.println(ing.getNom() + ":" + ing.getRayon() + " avec une quantité de "+  ing.getQuantite() + " " + ing.getUnite());
		}
    }
	
	/** Ajouter un ingredient à la liste de course.
	 * @param ingredient Nouveau ingrédient à ajouter
	 */
	public void ajouterIngredient(IngredientCourses ingredient) {
		this.course.add(ingredient);
	}

	 /** Retirer un ingredient de la liste de course.
     * @param ingredient à enlever
     */
    public void retirerIngredient(IngredientCourses ingredient) {
    	this.course.remove(ingredient);
    }
   
	/** Vérifier si un ingrédient existe déjà dans la liste de course.
	 * @param ingredient
	 * @return True si l'ingrédient est déjà présent dans la liste de course
	 */
	public boolean estDedans(IngredientCourses ingredient) {
		return this.course.contains(ingredient);
	}
	
	/** Renvoyer l'attribut "course"
	 * @return course l'attribut de la ListeCourses
	 */
	public ArrayList<IngredientCourses> getCourse(){
		return this.course;
	}
	/** Renvoie la taille de la liste de courses
	 * @return size la taille de la liste
	 */
	public int getSize() {
		int size = 0;
		for (IngredientCourses ing :this.course) {
			size ++;
		}
		return size;
	}
	/**
	 * Calcule et renvoie le prix de la liste de courses
	 * @return prix le cout de la liste
	 */
	public float prixListe() {
		float prix = 0;
		for (IngredientCourses ing :this.course) {
		    prix += ing.getPrix()*ing.getQuantite();
		}
		return prix;
	}
	
	public void ajouterGardeManger(GardeManger g) {
		for (IngredientCourses ing : this.course) {
			g.ajouterIngredient(new IngredientGM(ing.getNom(), LocalDate.now(), ing.getQuantite(), ing.getPrix(), ing.getUnite(), ing.getRayon()));
		}
	}
}

