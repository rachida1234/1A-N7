package src.base_de_donnees;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import src.classes_communes.Ingredient;
import src.classes_communes.Produit;
import src.recettes.ListeRecette;
import src.recettes.Recette;

/** Répertorie les recettes qui seront dans l'application
 * 
 * @author hdupuis
 * @version 07/05
 */
public class RecettesAppli {
	
	List<Produit> l = new ProduitsAppli().getListe();
	ResizeImageExample r = new ResizeImageExample();
	Ingredient spaghetti = new Ingredient(l.get(0).getNom(), l.get(0).getRayon(), 250.0, "g");
	Ingredient boeufHache = new Ingredient(l.get(1).getNom(), l.get(1).getRayon(), 500.0, "g");
	Ingredient huile = new Ingredient(l.get(2).getNom(), l.get(2).getRayon(), 2, "cuillères à soupe");
	Ingredient tomate = new Ingredient(l.get(3).getNom(), l.get(3).getRayon(), 800.0, "g");
	Ingredient sucre = new Ingredient(l.get(4).getNom(), l.get(4).getRayon(), 1, "cuillère à café");
	Ingredient ail = new Ingredient(l.get(5).getNom(), l.get(5).getRayon(), 3, "u");
	Ingredient oignon = new Ingredient(l.get(6).getNom(), l.get(6).getRayon(), 1, "u");
	Ingredient thym = new Ingredient(l.get(7).getNom(), l.get(7).getRayon(), 2, "u");
	Ingredient parmesan = new Ingredient(l.get(8).getNom(), l.get(8).getRayon(), 50.0, "g");
	Recette spaghBolo = new Recette("Spaghetti bolognaise", new ArrayList<>(), 20, 15, 4, new ImageIcon(this.getClass().getResource("spaghBolo_resized.jpg")));
	
	Ingredient patePizza = new Ingredient(l.get(9).getNom(), l.get(9).getRayon(), 1, "u");
	Ingredient cheddar = new Ingredient(l.get(10).getNom(), l.get(10).getRayon(), 65.0, "g");
	Ingredient comte = new Ingredient(l.get(11).getNom(), l.get(11).getRayon(), 40.0, "g");
	Ingredient bleu = new Ingredient(l.get(12).getNom(), l.get(12).getRayon(), 50.0, "g");
	Ingredient mozzarella = new Ingredient(l.get(13).getNom(), l.get(13).getRayon(), 1, "boule");
	Ingredient basilic = new Ingredient(l.get(14).getNom(), l.get(14).getRayon(), 1, "u");
	Recette pizza = new Recette("Pizza 4 fromages", new ArrayList<>(), 20, 20, 4, new ImageIcon(this.getClass().getResource("pizza_resized.jpg")));
	
	Ingredient haricots = new Ingredient(l.get(15).getNom(), l.get(15).getRayon(), 350.0, "g");
	Ingredient canard = new Ingredient(l.get(16).getNom(), l.get(16).getRayon(), 2, "u");
	Ingredient porcP = new Ingredient(l.get(17).getNom(), l.get(17).getRayon(), 250.0, "g");
	Ingredient porcC = new Ingredient(l.get(18).getNom(), l.get(18).getRayon(), 200.0, "g");
	Ingredient carotte = new Ingredient(l.get(19).getNom(), l.get(19).getRayon(), 1, "u");
	Ingredient ail2 = new Ingredient(l.get(5).getNom(), l.get(5).getRayon(), 6, "u");
	Ingredient oignon2 = new Ingredient(l.get(6).getNom(), l.get(6).getRayon(), 1, "u");
	Recette cassoulet = new Recette("Cassoulet", new ArrayList<>(), 35, 160, 6, new ImageIcon(this.getClass().getResource("cassoulet_resized.jpg")));
	
	Ingredient paprika = new Ingredient(l.get(20).getNom(), l.get(20).getRayon(), 2.5, "ml");
	Ingredient fArtic = new Ingredient(l.get(21).getNom(), l.get(21).getRayon(), 2, "u");
	Ingredient safran = new Ingredient(l.get(22).getNom(), l.get(22).getRayon(), 1, "u");
	Ingredient poulet = new Ingredient(l.get(23).getNom(), l.get(23).getRayon(), 340.0, "g");
	Ingredient poivronR = new Ingredient(l.get(24).getNom(), l.get(24).getRayon(), 1, "u");
	Ingredient chorizo = new Ingredient(l.get(25).getNom(), l.get(25).getRayon(), 150.0, "g");
	Ingredient riz = new Ingredient(l.get(26).getNom(), l.get(26).getRayon(), 330.0, "g");
	Ingredient vinB = new Ingredient(l.get(27).getNom(), l.get(27).getRayon(), 125.0, "ml");
	Ingredient crevettes = new Ingredient(l.get(28).getNom(), l.get(28).getRayon(), 340.0, "g");
	Ingredient moules = new Ingredient(l.get(29).getNom(), l.get(29).getRayon(), 20, "u");
	Ingredient petitspois = new Ingredient(l.get(30).getNom(), l.get(30).getRayon(), 75.0, "g");
	Ingredient citron = new Ingredient(l.get(31).getNom(), l.get(31).getRayon(), 1, "u");
	Ingredient tomate2 = new Ingredient(l.get(3).getNom(), l.get(3).getRayon(), 45.0, "ml");
	Ingredient oignon3 = new Ingredient(l.get(6).getNom(), l.get(6).getRayon(), 1, "u");
	Ingredient ail3 = new Ingredient(l.get(5).getNom(), l.get(5).getRayon(), 2, "u");
	Recette paella = new Recette("Paëlla", new ArrayList<>(), 45, 45, 8, new ImageIcon(this.getClass().getResource("paella_resized.jpg")));
	
	Ingredient moutarde = new Ingredient(l.get(32).getNom(), l.get(32).getRayon(), 50.0, "g");
	Ingredient ketchup = new Ingredient(l.get(33).getNom(), l.get(33).getRayon(), 50.0, "g");
	Ingredient painBurger = new Ingredient(l.get(34).getNom(), l.get(34).getRayon(), 8, "u");
	Ingredient salade = new Ingredient(l.get(35).getNom(), l.get(35).getRayon(), 4, "u");
	Ingredient boeufHache2 = new Ingredient(l.get(1).getNom(), l.get(1).getRayon(), 500.0, "g");
	Ingredient tomate3 = new Ingredient(l.get(3).getNom(), l.get(3).getRayon(), 800.0, "g");
	Ingredient cheddar2 = new Ingredient(l.get(10).getNom(), l.get(10).getRayon(), 8, "u");
	Recette hamburger = new Recette("Hamburger", new ArrayList<>(), 10, 10, 4, new ImageIcon(this.getClass().getResource("hamburger_resized.jpg")));
	
	Ingredient patate = new Ingredient(l.get(36).getNom(), l.get(36).getRayon(), 1500.0, "g");
	Ingredient fromRaclette = new Ingredient(l.get(37).getNom(), l.get(37).getRayon(), 500.0, "g");
	Ingredient poivre = new Ingredient(l.get(38).getNom(), l.get(38).getRayon(), 0.5, "cuillère à café");
	Ingredient sel = new Ingredient(l.get(39).getNom(), l.get(39).getRayon(), 1, "u");
	Ingredient oignon4 = new Ingredient(l.get(6).getNom(), l.get(6).getRayon(), 3, "u");
	Recette tartiflette = new Recette("Tartiflette", new ArrayList<>(), 20, 35, 6, new ImageIcon(this.getClass().getResource("tartiflette_resized.jpg")));
	
	Ingredient beurre = new Ingredient(l.get(40).getNom(), l.get(40).getRayon(), 60.0, "g");
	Ingredient farine = new Ingredient(l.get(41).getNom(), l.get(41).getRayon(), 60.0, "g");
	Ingredient vinR = new Ingredient(l.get(42).getNom(), l.get(42).getRayon(), 50.0, "cl");
	Ingredient champignon = new Ingredient(l.get(43).getNom(), l.get(43).getRayon(), 250.0, "g");
	Ingredient boeuf = new Ingredient(l.get(44).getNom(), l.get(44).getRayon(), 1500.0, "g");
	Ingredient carotte2 = new Ingredient(l.get(19).getNom(), l.get(19).getRayon(), 2, "u");
	Ingredient ail4 = new Ingredient(l.get(5).getNom(), l.get(5).getRayon(), 2, "u");
	Ingredient oignon5 = new Ingredient(l.get(6).getNom(), l.get(6).getRayon(), 3, "u");
	Recette boeufB = new Recette("Boeuf bourgignon", new ArrayList<>(), 20, 210, 6, new ImageIcon(this.getClass().getResource("boeufbourgignon_resized.jpg")));
	
	Ingredient patate2 = new Ingredient(l.get(36).getNom(), l.get(36).getRayon(), 2000.0, "g");
	Ingredient fromRaclette2 = new Ingredient(l.get(37).getNom(), l.get(37).getRayon(), 1000.0, "g");
	Ingredient charcuterie = new Ingredient(l.get(45).getNom(), l.get(45).getRayon(), 1000.0, "g");
	Ingredient oignon6 = new Ingredient(l.get(6).getNom(), l.get(6).getRayon(), 3, "u");
	Ingredient cornichons = new Ingredient(l.get(46).getNom(), l.get(46).getRayon(), 300.0, "g");
	Ingredient poivre2 = new Ingredient(l.get(38).getNom(), l.get(38).getRayon(), 1, "u");
	Recette raclette = new Recette("Raclette", new ArrayList<>(), 50, 0, 6, new ImageIcon(this.getClass().getResource("raclette_resized.jpg")));
	
	Ingredient oignon7 = new Ingredient(l.get(6).getNom(), l.get(6).getRayon(), 4, "u");
	Ingredient ail5 = new Ingredient(l.get(5).getNom(), l.get(5).getRayon(), 4, "u");
	Ingredient huile2 = new Ingredient(l.get(2).getNom(), l.get(2).getRayon(), 12, "cuillères à soupe");
	Ingredient chili = new Ingredient(l.get(47).getNom(), l.get(47).getRayon(), 2, "cuillères à café");
	Ingredient cumin = new Ingredient(l.get(48).getNom(), l.get(48).getRayon(), 2, "cuillères à café");
	Ingredient sel2 = new Ingredient(l.get(39).getNom(), l.get(39).getRayon(), 1, "u");
	Ingredient poivre3 = new Ingredient(l.get(38).getNom(), l.get(38).getRayon(), 1, "u");
	Ingredient poulet2 = new Ingredient(l.get(23).getNom(), l.get(23).getRayon(), 800.0, "g");
	Ingredient poivronR2 = new Ingredient(l.get(24).getNom(), l.get(24).getRayon(), 2, "u");
	Ingredient poivronV = new Ingredient(l.get(49).getNom(), l.get(49).getRayon(), 2, "u");
	Ingredient cremeFraiche = new Ingredient(l.get(50).getNom(), l.get(50).getRayon(), 40.0 , "cl");
	Ingredient tortilla = new Ingredient(l.get(51).getNom(), l.get(51).getRayon(), 16 , "u");
	Recette fajitas = new Recette("Fajitas", new ArrayList<>(), 120, 0, 8, new ImageIcon(this.getClass().getResource("fajitas_resized.png")));
	
	Ingredient riz2 = new Ingredient(l.get(26).getNom(), l.get(26).getRayon(), 300.0, "g");
	Ingredient oignon8 = new Ingredient(l.get(6).getNom(), l.get(6).getRayon(), 3, "u");
	Ingredient huile3 = new Ingredient(l.get(2).getNom(), l.get(2).getRayon(), 3, "cuillères à soupe");
	Ingredient oeuf = new Ingredient(l.get(52).getNom(), l.get(52).getRayon(), 3 , "u");
	Ingredient crevette = new Ingredient(l.get(53).getNom(), l.get(53).getRayon(), 75.0 , "g");
	Ingredient petitspois2 = new Ingredient(l.get(30).getNom(), l.get(30).getRayon(), 75.0, "g");
	Ingredient jambon = new Ingredient(l.get(54).getNom(), l.get(54).getRayon(), 75.0, "g");
	Recette rizCantonais = new Recette("Riz cantonais", new ArrayList<>(), 20, 20, 4, new ImageIcon(this.getClass().getResource("rizcantonais_resized.jpg")));
	
	Ingredient spaghetti2 = new Ingredient(l.get(0).getNom(), l.get(0).getRayon(), 400.0, "g");
	Ingredient oignon9 = new Ingredient(l.get(6).getNom(), l.get(6).getRayon(), 1.0, "u");
	Ingredient sauceTomate = new Ingredient(l.get(55).getNom(), l.get(55).getRayon(), 1,"u");
	Ingredient cremeFraiche2 = new Ingredient(l.get(51).getNom(), l.get(51).getRayon(), 3 ,"cuillères à soupe");
	Ingredient gruyere = new Ingredient(l.get(56).getNom(), l.get(56).getRayon(), 100.0, "g");
	Ingredient thonEmiette = new Ingredient(l.get(57).getNom(), l.get(57).getRayon(), 200.0, "g");
	Recette pateAuThon = new Recette("Pâte au thon", new ArrayList<>(), 20, 3, 2, new ImageIcon(this.getClass().getResource("patesthon_resized.jpg")));

	Ingredient patate3 = new Ingredient(l.get(36).getNom(), l.get(36).getRayon(), 500.0, "g");
	Ingredient beurre2 = new Ingredient(l.get(40).getNom(), l.get(40).getRayon(), 50, "g");
	Ingredient ail6 = new Ingredient(l.get(5).getNom(), l.get(5).getRayon(), 2, "u"); 
	Ingredient tomme = new Ingredient(l.get(58).getNom(), l.get(58).getRayon(), 300.0,"g");
	Recette aligot = new Recette("Aligot", new ArrayList<>(), 30, 30, 6, new ImageIcon(this.getClass().getResource("aligot_resized.jpg")));

	private ListeRecette listeRecette = new ListeRecette(new ArrayList<>());
	
	
	private void initialiser() {
		spaghBolo.ajouter(spaghetti); spaghBolo.ajouter(boeufHache); spaghBolo.ajouter(huile); spaghBolo.ajouter(tomate);
		spaghBolo.ajouter(sucre); spaghBolo.ajouter(ail); spaghBolo.ajouter(oignon); spaghBolo.ajouter(thym); spaghBolo.ajouter(parmesan);
		listeRecette.ajouterRecette(spaghBolo);
		
		pizza.ajouter(patePizza); pizza.ajouter(cheddar); pizza.ajouter(comte); pizza.ajouter(bleu); pizza.ajouter(mozzarella); pizza.ajouter(basilic);
		listeRecette.ajouterRecette(pizza);
		
		cassoulet.ajouter(haricots); cassoulet.ajouter(canard); cassoulet.ajouter(porcP); cassoulet.ajouter(porcC); cassoulet.ajouter(carotte);
		cassoulet.ajouter(ail2); cassoulet.ajouter(oignon2);
		listeRecette.ajouterRecette(cassoulet);
		
		paella.ajouter(paprika); paella.ajouter(fArtic); paella.ajouter(safran); paella.ajouter(poulet); paella.ajouter(poivronR); paella.ajouter(chorizo); paella.ajouter(riz); paella.ajouter(vinB);
		paella.ajouter(crevettes); paella.ajouter(moules); paella.ajouter(petitspois); paella.ajouter(citron); paella.ajouter(tomate2); paella.ajouter(oignon3); paella.ajouter(ail3);
		listeRecette.ajouterRecette(paella);
		
		hamburger.ajouter(moutarde); hamburger.ajouter(ketchup); hamburger.ajouter(painBurger); hamburger.ajouter(salade); hamburger.ajouter(boeufHache2); hamburger.ajouter(tomate3); hamburger.ajouter(cheddar2);
		listeRecette.ajouterRecette(hamburger);
		
		tartiflette.ajouter(patate); tartiflette.ajouter(fromRaclette); tartiflette.ajouter(poivre); tartiflette.ajouter(sel); tartiflette.ajouter(oignon4);
		listeRecette.ajouterRecette(tartiflette);
		
		boeufB.ajouter(beurre); boeufB.ajouter(farine); boeufB.ajouter(vinB); boeufB.ajouter(champignon); 
		boeufB.ajouter(boeuf); 
		boeufB.ajouter(carotte2); boeufB.ajouter(ail4); boeufB.ajouter(oignon5); 
		boeufB.ajouter(sel); boeufB.ajouter(poivre);
		listeRecette.ajouterRecette(boeufB);
		
		raclette.ajouter(patate2); raclette.ajouter(fromRaclette2); raclette.ajouter(charcuterie); raclette.ajouter(oignon6); raclette.ajouter(cornichons); raclette.ajouter(poivre2);
		listeRecette.ajouterRecette(raclette);
		
		fajitas.ajouter(oignon7);fajitas.ajouter(ail5);fajitas.ajouter(huile2);fajitas.ajouter(chili);fajitas.ajouter(cumin);fajitas.ajouter(sel2);fajitas.ajouter(poivre3);fajitas.ajouter(poulet2);
		fajitas.ajouter(poivronR2);fajitas.ajouter(poivronV);fajitas.ajouter(cremeFraiche); fajitas.ajouter(tortilla);
		listeRecette.ajouterRecette(fajitas);
		
		rizCantonais.ajouter(riz2); rizCantonais.ajouter(oignon8); rizCantonais.ajouter(huile3); rizCantonais.ajouter(oeuf); rizCantonais.ajouter(crevette); rizCantonais.ajouter(petitspois2); rizCantonais.ajouter(jambon);
		listeRecette.ajouterRecette(rizCantonais);
		
		pateAuThon.ajouter(spaghetti2);pateAuThon.ajouter(oignon9);pateAuThon.ajouter(sauceTomate);pateAuThon.ajouter(cremeFraiche2);pateAuThon.ajouter(gruyere);pateAuThon.ajouter(thonEmiette);
		listeRecette.ajouterRecette(pateAuThon);

		aligot.ajouter(patate3);aligot.ajouter(beurre2);aligot.ajouter(cremeFraiche2);aligot.ajouter(ail6);aligot.ajouter(tomme);
		listeRecette.ajouterRecette(aligot);
	}
	
	public RecettesAppli() {
		initialiser();
	}
	
	/** Obtenir la liste de recettes contenues dans l'application
	 * @return liste des recettes dans l'application
	 */
	public ListeRecette getListe() {
		return this.listeRecette;
	}
	
	/** Ajouter une recette à la base de données de l'application
	 * 
	 * @param recette
	 */
	public void ajouterRecette(Recette recette) {
		this.listeRecette.ajouterRecette(recette);
	}

}
