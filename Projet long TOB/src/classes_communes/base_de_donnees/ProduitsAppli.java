package src.base_de_donnees;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import src.classes_communes.Produit;
import src.liste_courses.ListeCourses;
import src.supermarche.ProduitSupermarche;
import src.supermarche.Supermarche;

/** Répertorie les ingrédients qui seront dans l'application
 * Les différents types de rayon : Protéine animale, Epicerie salée, Epicerie sucrée, 
 * 								   Légumes, Fruits, Epices, Boissons
 * @author hdupuis
 * @version 11/05
 */
public class ProduitsAppli {

	private List<Produit> l = new ArrayList<>();
	private List<ProduitSupermarche> c = new ArrayList<>();
	private Supermarche carrefour;
	
	Produit spaghetti = new Produit("Spaghetti", "Epicerie salée");
	ProduitSupermarche spaghettiC = new ProduitSupermarche(spaghetti.getNom(), spaghetti.getRayon(), 1.39, "kg");
	Produit boeufHache = new Produit("Viande hachée", "Protéine animale");
	ProduitSupermarche boeufHacheC = new ProduitSupermarche(boeufHache.getNom(), boeufHache.getRayon(), 10.0, "kg");
	Produit huile = new Produit("Huile d'olive", "Epicerie salée");
	ProduitSupermarche huileC = new ProduitSupermarche("Huile d'olive", "Epicerie salée", 6.99, "L");
	Produit tomate = new Produit("Tomates", "Fruits");
	ProduitSupermarche tomateC = new ProduitSupermarche(tomate.getNom(), tomate.getRayon(), 2.79, "kg");
	Produit sucre = new Produit("Sucre en poudre", "Epicerie sucrée");
	ProduitSupermarche sucreC = new ProduitSupermarche(sucre.getNom(), sucre.getRayon(), 0.8, "kg");
	Produit ail = new Produit("Ail", "Légumes");
	ProduitSupermarche ailC = new ProduitSupermarche(ail.getNom(), ail.getRayon(), 8.54, "kg");
	Produit oignon = new Produit("Oignon", "Légumes");
	ProduitSupermarche oignonC = new ProduitSupermarche(oignon.getNom(), oignon.getRayon(), 1.69, "kg");
	Produit thym = new Produit("Thym", "Légumes");
	ProduitSupermarche thymC = new ProduitSupermarche(thym.getNom(), thym.getRayon(), 12.9, "kg");
	Produit parmesan = new Produit("Parmesan", "Protéine animale");
	ProduitSupermarche parmesanC = new ProduitSupermarche(parmesan.getNom(), parmesan.getRayon(), 21.8, "kg");
	
	Produit patePizza = new Produit("Pâte à pizza", "Epicerie salée");
	ProduitSupermarche patePizzaC = new ProduitSupermarche(patePizza.getNom(), patePizza.getRayon(), 3.61, "kg");
	Produit cheddar = new Produit("Cheddar", "Protéine animale");
	ProduitSupermarche cheddarC = new ProduitSupermarche(cheddar.getNom(), cheddar.getRayon(), 4.75, "kg");
	Produit comte = new Produit("Comté", "Protéine animale");
	ProduitSupermarche comteC = new ProduitSupermarche(comte.getNom(), comte.getRayon(), 17.9, "kg");
	Produit bleu = new Produit("Bleu", "Protéine animale");
	ProduitSupermarche bleuC = new ProduitSupermarche(bleu.getNom(), bleu.getRayon(), 10.8, "kg");
	Produit mozzarella = new Produit("Mozzarella", "Protéine animale");
	ProduitSupermarche mozzarellaC = new ProduitSupermarche(mozzarella.getNom(), mozzarella.getRayon(), 8.32, "kg");
	Produit basilic = new Produit("Basilic", "Légumes");
	ProduitSupermarche basilicC = new ProduitSupermarche(basilic.getNom(), basilic.getRayon(), 145.45, "kg");
	
	Produit haricots = new Produit("Haricots", "Légumes");
	ProduitSupermarche haricotsC = new ProduitSupermarche(haricots.getNom(), haricots.getRayon(), 1.70, "kg");
	Produit canard = new Produit("Cuisse de canard confite", "Protéine animale");
	ProduitSupermarche canardC = new ProduitSupermarche(canard.getNom(), canard.getRayon(), 17.55, "kg");
	Produit porcP = new Produit("Poitrine de porc", "Protéine animale");
	ProduitSupermarche porcPC = new ProduitSupermarche(porcP.getNom(), porcP.getRayon(), 7.5, "kg");
	Produit porcC = new Produit("Couenne de porc", "Protéine animale");
	ProduitSupermarche porcCC = new ProduitSupermarche(porcC.getNom(), porcC.getRayon(), 4.4, "kg");
	Produit carotte = new Produit("Carotte", "Légumes");
	ProduitSupermarche carotteC = new ProduitSupermarche(carotte.getNom(), carotte.getRayon(), 10.64, "kg");
	
	Produit paprika = new Produit("Paprika", "Epicerie salée");
	ProduitSupermarche paprikaC = new ProduitSupermarche(paprika.getNom(), paprika.getRayon(), 55.75, "kg");
	Produit fArtic = new Produit("Fonds d'artichauds", "Légumes");
	ProduitSupermarche fArticC = new ProduitSupermarche(fArtic.getNom(), fArtic.getRayon(), 13.2, "kg");
	Produit safran = new Produit("Safran", "Légumes");
	ProduitSupermarche safranC = new ProduitSupermarche(safran.getNom(), safran.getRayon(), 4.51, "kg");
	Produit poulet = new Produit("Cuisses de poulet", "Protéine animale");
	ProduitSupermarche pouletC = new ProduitSupermarche(poulet.getNom(), poulet.getRayon(), 6.99, "kg");
	Produit poivronR = new Produit("Poivron rouge", "Légumes");
	ProduitSupermarche poivronRC = new ProduitSupermarche(poivronR.getNom(), poivronR.getRayon(), 1.99, "kg");
	Produit chorizo = new Produit("Chorizo", "Protéine animale");
	ProduitSupermarche chorizoC = new ProduitSupermarche(chorizo.getNom(), chorizo.getRayon(), 18.2, "kg");
	Produit riz = new Produit("Riz", "Epicerie salée");
	ProduitSupermarche rizC = new ProduitSupermarche(riz.getNom(), riz.getRayon(), 2.41, "kg");
	Produit vinB = new Produit("Vin blanc", "Boissons");
	ProduitSupermarche vinBC = new ProduitSupermarche(vinB.getNom(), vinB.getRayon(), 3.23, "L");
	Produit crevettes = new Produit("Crevettes", "Protéine animale");
	ProduitSupermarche crevettesC = new ProduitSupermarche(crevettes.getNom(), crevettes.getRayon(), 14.26, "kg");
	Produit moules = new Produit("Moules", "Protéine animale");
	ProduitSupermarche moulesC = new ProduitSupermarche(moules.getNom(), moules.getRayon(), 3.56, "kg");
	Produit petitsPois = new Produit("Petits pois", "Légumes");
	ProduitSupermarche petitsPoisC = new ProduitSupermarche(petitsPois.getNom(), petitsPois.getRayon(), 1.38, "kg");
	Produit citron = new Produit("Citron", "Fruits");
	ProduitSupermarche citronC = new ProduitSupermarche(citron.getNom(), citron.getRayon(), 2.0, "kg");
	
	Produit moutarde = new Produit("Moutarde", "Epices");
	ProduitSupermarche moutardeC = new ProduitSupermarche(moutarde.getNom(), moutarde.getRayon(), 5.25, "kg");
	Produit ketchup = new Produit("Ketchup", "Epices");
	ProduitSupermarche ketchupC = new ProduitSupermarche(ketchup.getNom(), ketchup.getRayon(), 1.7, "kg");
	Produit painBurger = new Produit("Pain à hamburger", "Epicerie salée");
	ProduitSupermarche painBurgerC = new ProduitSupermarche(painBurger.getNom(), painBurger.getRayon(), 3.37, "kg");
	Produit salade = new Produit("Salade", "Légumes");
	ProduitSupermarche saladeC = new ProduitSupermarche(salade.getNom(), salade.getRayon(), 7.92, "kg");
	
	Produit patate = new Produit("Pommes de terre", "Légumes");
	ProduitSupermarche patateC = new ProduitSupermarche(patate.getNom(), patate.getRayon(), 1.0, "kg");
	Produit fromRaclette = new Produit("Fromage à raclette", "Protéine animale");
	ProduitSupermarche fromRacletteC = new ProduitSupermarche(fromRaclette.getNom(), fromRaclette.getRayon(), 8.62, "kg");
	Produit poivre = new Produit("Poivre", "Epices");
	ProduitSupermarche poivreC = new ProduitSupermarche(poivre.getNom(), poivre.getRayon(), 17.14, "kg");
	Produit sel = new Produit("Sel", "Epices");
	ProduitSupermarche selC = new ProduitSupermarche(sel.getNom(), sel.getRayon(), 6.2, "kg");
	
	Produit beurre = new Produit("Beurre", "Protéine animale");
	ProduitSupermarche beurreC = new ProduitSupermarche(beurre.getNom(), beurre.getRayon(), 9.4, "kg");
	Produit farine = new Produit("Farine", "Epicerie salée");
	ProduitSupermarche farineC = new ProduitSupermarche(farine.getNom(), farine.getRayon(), 0.72, "kg");
	Produit vinR = new Produit("Vin rouge", "Boissons");
	ProduitSupermarche vinRC = new ProduitSupermarche(vinR.getNom(), vinR.getRayon(), 3.72, "L");
	Produit champignon = new Produit("Champignons", "Légumes");
	ProduitSupermarche champignonC = new ProduitSupermarche(champignon.getNom(), champignon.getRayon(), 5.0, "kg");
	Produit boeuf = new Produit("Boeuf", "Protéine animale");
	ProduitSupermarche boeufC = new ProduitSupermarche(boeuf.getNom(), boeuf.getRayon(), 9.9, "kg");
	Produit charcuterie = new Produit("Charcuterie", "Protéine animale");
	ProduitSupermarche charcuterieC = new ProduitSupermarche(charcuterie.getNom(), charcuterie.getRayon(), 18.25, "kg");
	Produit cornichons = new Produit("Cornichons", "Légumes");
	ProduitSupermarche cornichonsC = new ProduitSupermarche(cornichons.getNom(), cornichons.getRayon(), 8.05, "kg");
	
	Produit chili = new Produit("Chili", "Epices");
	ProduitSupermarche chiliC = new ProduitSupermarche(chili.getNom(), chili.getRayon(), 93.0, "kg");
	Produit cumin = new Produit("Chili", "Epices");
	ProduitSupermarche cuminC = new ProduitSupermarche(cumin.getNom(), cumin.getRayon(), 11.5, "kg");
	Produit poivronV = new Produit("Poivron vert", "Légumes");
	ProduitSupermarche poivronVC = new ProduitSupermarche(poivronV.getNom(), poivronV.getRayon(), 1.99, "kg");
	Produit cremeFraiche = new Produit("Crème fraiche", "Protéine animale");
	ProduitSupermarche cremeFraicheC = new ProduitSupermarche(cremeFraiche.getNom(), cremeFraiche.getRayon(), 3.18, "L");
	Produit tortilla = new Produit("Tortilla", "Epicerie salée");
	ProduitSupermarche tortillaC = new ProduitSupermarche(tortilla.getNom(), tortilla.getRayon(), 5.78, "kg");
	
	Produit oeuf = new Produit("Oeuf", "Protéine animale");
	ProduitSupermarche oeufC = new ProduitSupermarche(oeuf.getNom(), oeuf.getRayon(), 0.25, "u");
	Produit crevette = new Produit("Crevette", "Protéine animale");
	ProduitSupermarche crevetteC = new ProduitSupermarche(crevette.getNom(), crevette.getRayon(), 14.26, "kg");
	Produit jambon = new Produit("Jambon", "Protéine animale");
	ProduitSupermarche jambonC = new ProduitSupermarche(jambon.getNom(), jambon.getRayon(), 8.9, "kg");
	
	Produit sauceTomate = new Produit("Sauce tomate", "Légumes");
	ProduitSupermarche sauceTomateC = new ProduitSupermarche(sauceTomate.getNom(), sauceTomate.getRayon(), 2.5, "kg");
	Produit gruyere = new Produit("Gruyère", "Protéine animale");
	ProduitSupermarche gruyereC = new ProduitSupermarche(gruyere.getNom(), gruyere.getRayon(), 7.25, "kg");
	Produit thonEmiette = new Produit("Thon émietté", "Protéine animale");
	ProduitSupermarche thonEmietteC = new ProduitSupermarche(thonEmiette.getNom(), thonEmiette.getRayon(), 17.67, "kg");
	Produit tomme = new Produit("tomme fraiche d'Auvergne", "Protéine animale");
	ProduitSupermarche tommeC = new ProduitSupermarche(tomme.getNom(), tomme.getRayon(), 12.3, "kg");
	

	private void initialiser() {
		
		l.add(spaghetti); l.add(boeufHache); l.add(huile); l.add(tomate); l.add(sucre); l.add(ail); l.add(oignon); l.add(thym); l.add(parmesan);	
		l.add(patePizza); l.add(cheddar); l.add(comte); l.add(bleu); l.add(mozzarella); l.add(basilic);
		l.add(haricots); l.add(canard); l.add(porcP); l.add(porcC); l.add(carotte);
		l.add(paprika); l.add(fArtic); l.add(safran); l.add(poulet); l.add(poivronR); l.add(chorizo); l.add(riz); l.add(vinB); l.add(crevettes); l.add(moules); l.add(petitsPois); l.add(citron);
		l.add(moutarde); l.add(ketchup); l.add(painBurger); l.add(salade);
		l.add(patate); l.add(fromRaclette); l.add(poivre); l.add(sel);
		l.add(beurre); l.add(farine); l.add(vinR); l.add(champignon); l.add(boeuf);
		l.add(charcuterie); l.add(cornichons);
		l.add(chili); l.add(cumin); l.add(poivronV); l.add(cremeFraiche); l.add(tortilla);
		l.add(oeuf); l.add(crevette); l.add(jambon);
		l.add(sauceTomate); l.add(gruyere); l.add(thonEmiette); l.add(tomme);
	}
	
	private void initialiserCarrefour() {
		c.add(spaghettiC); c.add(boeufHacheC); c.add(huileC); c.add(tomateC); c.add(sucreC); c.add(ailC); c.add(oignonC); c.add(thymC); c.add(parmesanC);
		c.add(patePizzaC); c.add(cheddarC); c.add(comteC); c.add(bleuC); c.add(mozzarellaC); c.add(basilicC);
		c.add(haricotsC); c.add(canardC); c.add(porcPC); c.add(porcCC); c.add(carotteC);
		c.add(paprikaC); c.add(fArticC); c.add(safranC); c.add(pouletC); c.add(poivronRC); c.add(chorizoC); c.add(rizC); c.add(vinBC); c.add(crevettesC); c.add(moulesC); c.add(petitsPoisC); c.add(citronC);
		c.add(moutardeC); c.add(ketchupC); c.add(painBurgerC); c.add(saladeC);
		c.add(patateC); c.add(fromRacletteC); c.add(poivreC); c.add(selC);
		c.add(beurreC); c.add(farineC); c.add(vinRC); c.add(champignonC); c.add(boeufC);
		c.add(charcuterieC); c.add(cornichonsC);
		c.add(chiliC); c.add(cuminC); c.add(poivronVC); c.add(cremeFraicheC); c.add(tortillaC);
		c.add(oeufC); c.add(crevetteC); c.add(jambonC);
		c.add(sauceTomateC); c.add(gruyereC); c.add(thonEmietteC); c.add(tommeC);
		
		carrefour = new Supermarche("Carrefour", c, new ListeCourses(new ArrayList<>(), LocalDate.now()));
	}
	
	public ProduitsAppli() {
		initialiser();
		initialiserCarrefour();
	}
	
	public List<Produit> getListe() {
		return this.l;
	}
	
	public Supermarche getSupermarche() {
		return this.carrefour;
	}
}
