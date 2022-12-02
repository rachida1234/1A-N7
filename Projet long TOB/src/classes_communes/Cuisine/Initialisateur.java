package src.Cuisine;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public final class Initialisateur {
	
	public static ArrayList<IngredientGM> initProduit(){
		IngredientGM lait = new IngredientGM("Lait",LocalDate.parse("2022-05-30"),1,1, "L","Produit laitier");
		IngredientGM fromage = new IngredientGM("Fromage",LocalDate.parse("2022-06-15"),15,0.2, "u","Produit laitier");
		IngredientGM yaourt = new IngredientGM("Yaourt",LocalDate.parse("2022-06-20"),20,0.3, "u","Produit laitier");
		IngredientGM laitfer = new IngredientGM("Lait fermenté",LocalDate.parse("2022-05-29"),2,0.9, "L","Produit laitier");
		IngredientGM fromagefrais = new IngredientGM("Fromage Frais",LocalDate.parse("2022-06-30"),250,12, "g","Produit laitier");
	    IngredientGM limonade = new IngredientGM("Fanta",LocalDate.parse("2023-09-20"),1,2,"L" ,"Liquide");
	    IngredientGM vinr= new IngredientGM("Vin Rouge",LocalDate.parse("2023-09-20"),1.5,4,"L" ,"Liquide");
	    IngredientGM vinb= new IngredientGM("Vin Blanc",LocalDate.parse("2023-09-25"),1.5,3.5,"L" ,"Liquide");
	    IngredientGM Jus= new IngredientGM("Jus d'orange",LocalDate.parse("2022-08-20"),2,3.2,"L" ,"Liquide");
	    IngredientGM boisson= new IngredientGM("Boisson énergétique",LocalDate.parse("2022-08-20"),0.8,3.5,"L" ,"Liquide");
	    IngredientGM boeuf = new IngredientGM("Viande boeuf",LocalDate.parse("2022-06-05"),1.5,10,"kg","Viande");
	    IngredientGM porc = new IngredientGM("Porc",LocalDate.parse("2022-06-10"),0.8,6,"kg","Viande");
	    IngredientGM nuggets = new IngredientGM("Nuggets",LocalDate.parse("2022-06-10"),0.5,10,"kg","Viande");
	    IngredientGM poisson = new IngredientGM("saumon",LocalDate.parse("2022-06-13"),0.5,20,"kg","Viande");
	    IngredientGM thon = new IngredientGM("Thon",LocalDate.parse("2022-12-10"),0.6,6,"kg","Viande");
	    IngredientGM bannane = new IngredientGM("Banane",LocalDate.parse("2022-06-10"),2,2,"kg","Fruits");
	    IngredientGM pomme = new IngredientGM("Pomme",LocalDate.parse("2022-06-10"),2,3,"kg","Fruits");
	    IngredientGM annanas = new IngredientGM("Ananas",LocalDate.parse("2022-06-10"),1,3,"kg","Fruits");
	    IngredientGM fraise = new IngredientGM("Fraise",LocalDate.parse("2022-06-10"),1,5,"kg","Fruits");
	    IngredientGM kaki = new IngredientGM("Kaki",LocalDate.parse("2022-06-10"),2,2.9,"kg","Fruits");
	    ArrayList<IngredientGM> Produits = new ArrayList<>();
	    Collections.addAll(Produits,lait,fromage,yaourt,laitfer,fromagefrais,limonade,vinr,vinb,Jus,boisson,boeuf,porc,nuggets,poisson,thon,bannane,
	    		pomme,annanas,fraise,kaki);
	    return Produits;
	
	}
}
