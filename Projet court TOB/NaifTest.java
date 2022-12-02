package allumettes;
import org.junit.*;
import static org.junit.Assert.*;
public class NaifTest {
    private Jeu jeu1, jeu2, jeu3;
    private Strategie strategieNaif;
    private Joueur joueur;
    @Before public void setUp() {
		// Construire de deux jeux avec des nbAllumettes différents
		jeu1 = new ImplantationJeu(1);
		jeu2 = new ImplantationJeu(2);
		jeu3 = new ImplantationJeu(10);
		// construire une stratégie rapide
		strategieNaif = new Naif();
		//Construction d'un joueur suivant une stratégie rapide
		joueur=new Joueur("rachida", strategieNaif);
		}
    @Test public void testerPrise1() {
		assertEquals(1, strategieNaif.getPrise(jeu1, joueur));
	}
    @Test public void testerPrise2() {
	    assertTrue(strategieNaif.getPrise(jeu2,joueur) >= 1 && strategieNaif.getPrise(jeu2, joueur) <= 2);
	}
    @Test public void testerPrise3() {
	   assertTrue(strategieNaif.getPrise(jeu3,joueur) >= 1 && strategieNaif.getPrise(jeu3, joueur) <= 3);
	}
}
