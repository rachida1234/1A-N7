package src.Cuisine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GardeMangerTest {
    private GardeManger Magarde;
    private IngredientGM lait,confiture;
   
    @Before public void setUp() {
		// Construire les ingredients à ajouter dans la garde manger
		 lait = new IngredientGM("Lait",LocalDate.parse("2022-05-15"),2.0,10, "L","ProteineAnimale");
		 confiture= new IngredientGM("Confiture Delice",LocalDate.now(),250,13,"g" ,"EpicerieSucree");
		 //construire une garde manger 
		 ArrayList<IngredientGM> Produits = new ArrayList<>();
	     Produits.add(lait);
	     Produits.add(confiture);
	     Magarde = new GardeManger(Produits);
    	 
		}

    @Test public void testerestDedans() {
    	assertTrue(Magarde.estDedans(lait));
	} 
    @Test public void testerAjouter() {
       Magarde.ajouterIngredient(confiture);
	   assertTrue(Magarde.estDedans(confiture));
	}
	@Test public void testerRetirer() {
	   Magarde.retirerIngredientGM(lait);
	   assertTrue(!Magarde.estDedans(lait));
	}
	
	@Test public void testerRetirerQte() {
		Magarde.retirerQuantite("Lait", 1.0);
		assertTrue(Magarde.getIngredients().get(0).getQuantite()==1.0);
	}
    
}
