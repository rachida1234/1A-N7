package allumettes;
public class Arbitre {
    /**Le premier joueur.*/
    private Joueur premierJoueur;
    /**Deuxième joueur.*/
    private Joueur deuxiemeJoueur;
    /**Constructeur de l'arbitre.
    *@param joueur1
    *@param joueur2
    */
    public Arbitre(Joueur joueur1, Joueur joueur2) {
        assert joueur1 != null && joueur2 != null;
        this.premierJoueur = joueur1;
        this.deuxiemeJoueur = joueur2;
    }
    /**Décider le joueur gagnant en se basant sur le dernier joueur.
    *@param joueurActuel
    *@return Joueur gagnant
    */
    public Joueur joueurGagnant(Joueur joueurActuel) {
    assert joueurActuel != null;
    if (joueurActuel == premierJoueur) {
        return deuxiemeJoueur;
     } else {
        return premierJoueur;
     }
    }
    /**Assurer le déroulement du jeu.
     * @param jeu
     * @param etat
    */
    public void arbitrer(Jeu jeu, String etat) {
    assert jeu != null;
    assert jeu.getNombreAllumettes() > 0;
    Joueur joueurActuel = this.premierJoueur;
    Boolean tourJactuel = true;
    System.out.println("Allumettes restantes : " + jeu.getNombreAllumettes());
    do {
    	 try {
            if (tourJactuel) {
                joueurActuel = this.premierJoueur;
            } else {
                joueurActuel = this.deuxiemeJoueur;
                }
            if (!joueurActuel.getNom().equals("Ordinateur")
            		&& !(joueurActuel.getStrategy() instanceof Rapide)
            		&& !(joueurActuel.getStrategy() instanceof Tricheur)) {
                    System.out.print(joueurActuel.getNom() + ", combien d'allumettes ? ");
                }
            int nbPrises = 0;
            if (etat.equals("confiant")) {
              nbPrises = joueurActuel.getStrategy().getPrise(jeu, joueurActuel);
            } else {
              Jeu jeuProxy = new Proccuration((ImplantationJeu) jeu);
              nbPrises = joueurActuel.getStrategy().getPrise(jeuProxy, joueurActuel);
            }
            if (nbPrises > 1) {
                System.out.print(joueurActuel.getNom()
                + " prend " + nbPrises + " allumettes.\n");
            } else {
                System.out.print(joueurActuel.getNom()
                + " prend " + nbPrises + " allumette.\n");
            }
            jeu.retirer(nbPrises);
            tourJactuel = !tourJactuel;
            } catch (CoupInvalideException e) {
            System.out.print("Impossible ! Nombre invalide : "
            + e.getCoup() + " (" + e.getProbleme() + ")\n");
            } catch (OperationInterditeException e) {
                System.out.println("Abandon de la partie car "
            + joueurActuel.getNom() + " triche !");
                Runtime.getRuntime().exit(1);
            }
            if (jeu.getNombreAllumettes() > 0) {
                  System.out.println("\nAllumettes restantes : "
            + jeu.getNombreAllumettes());
            }
        } while (jeu.getNombreAllumettes() > 0);
    System.out.println("\n" + joueurActuel.getNom() + " perd !");
    System.out.println(joueurGagnant(joueurActuel).getNom() + " gagne !");
    }
    }
