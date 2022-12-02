package allumettes;
import org.junit.*;
import static org.junit.Assert.*;
public class RapideTest {
 private Jeu jeu1, jeu2, jeu3;
    private Strategie strategieRapide; 
    private Joueur joueur;
    @Before public void setUp() {
		// Construire de deux jeux avec des nbAllumettes différents
		 jeu1 = new ImplantationJeu(9);
		 jeu2 = new ImplantationJeu(2);
		 jeu3 = new ImplantationJeu(1);
		// construire une stratégie rapide
		strategieRapide = new Rapide();
		//Construction d'un joueur suivant une stratégie rapide
		joueur = new Joueur("rachida", strategieRapide);
		}

    @Test public void testerPrise1() {
		assertEquals(3, strategieRapide.getPrise(jeu1, joueur));
	}
    @Test public void testerPrise2() {
	   assertEquals(2, strategieRapide.getPrise(jeu2, joueur));
	}
    @Test public void testerPrise3() {
	   assertEquals(1, strategieRapide.getPrise(jeu3, joueur));
	}
}
