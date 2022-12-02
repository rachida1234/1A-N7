package allumettes;
import java.util.Random;
public class Naif implements Strategie {
 /** Obtenir la prise associée à une stratégie naive.
*@param jeu
*@param joueur
* @return nombre d'allumettes prises
  */
public int getPrise(Jeu jeu, Joueur joueur) {
Random rand = new Random();
int priseAleatoire = rand.nextInt(Math.min(Jeu.PRISE_MAX, jeu.getNombreAllumettes())) + 1;
return priseAleatoire;
        }
}
