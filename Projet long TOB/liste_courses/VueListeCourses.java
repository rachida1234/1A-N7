package src.liste_courses;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import src.Cuisine.GardeManger;
import src.budget.Budget;
import src.budget.VueBudget;
import src.recettes.VueListeRecettes;

/**
 * Classe qui crée la vue d'une liste de courses
 *
 * @author Louis Bru
 * @version 6.6.7
 * */
public class VueListeCourses {
	
	private ArrayList<IngredientCourses> liste ;
	private ArrayList<ListeCourses> historiqueAchats;
	private ListeCourses liste_backup;
	private JPanel centre ;
	private JTextArea prixtot = new JTextArea();
	private GardeManger g;
	public VueListeCourses(ListeCourses l, ListeCourses lbu, GardeManger g) {
		JFrame fenetre = new JFrame("Liste de courses");
		JButton boutonRetour = new JButton("Retour au menu");
		boutonRetour.setIcon(new ImageIcon(this.getClass().getResource("accueil.png")));
	    JButton boutonReprendre = new JButton("Reprendre la dernière liste de courses");
	    boutonReprendre.setIcon(new ImageIcon(this.getClass().getResource("importer.png")));
	    JButton boutonVider = new JButton("Vider la liste de courses");
	    boutonVider.setIcon(new ImageIcon(this.getClass().getResource("vider.png")));
	    JButton boutonGM = new JButton("Faire ses courses");
	    boutonGM.setIcon(new ImageIcon(this.getClass().getResource("caddie.png")));
		this.liste = l.getCourse();
		this.liste_backup = lbu;
		this.liste = l.getCourse();
		this.liste_backup = lbu;
		centre = new JPanel(new GridLayout(liste.size()+2, 1));
		Container contenu = fenetre.getContentPane();
		contenu.setLayout(new BorderLayout());
		
		// Vue du haut, avec le bouton retour
		JPanel haut = new JPanel(new BorderLayout());
		JPanel icones = new JPanel(new FlowLayout());
		icones.add(boutonRetour);
		icones.add(boutonReprendre);
		icones.add(boutonVider);
		icones.add(boutonGM);
		haut.add(icones);
		contenu.add(haut,BorderLayout.PAGE_START);
		// Construction de la vue centrale, avec les recettes et les boutons associés
		JPanel centre = new JPanel(new GridLayout(l.getSize()+2, 1));
		List<JButton> listeBoutons = new ArrayList<>();
		
		JPanel colonnes = new JPanel(new FlowLayout());
		JTextArea nom_colonnes = new JTextArea("Quantité		Produit		Prix unitaire		Prix total		Ajouter aux favoris");
		colonnes.add(nom_colonnes);
		
		mettreAJourProduit();
		mettreAJourPrixtotal();
		
		
		centre.add(colonnes,BorderLayout.CENTER);
		
		for (IngredientCourses ing : this.liste) {
			JPanel panelListe = new JPanel(new FlowLayout());
			JTextArea listeTexte = new JTextArea(ing.getQuantite()+"		"+ing.getNom()+"		"+ing.getPrix()+"		"+ing.getPrix()*ing.getQuantite()+"		");
			if (ing.estFavori()) {
				listeTexte.setFont(new Font(Font.SERIF, Font.BOLD, 12));
				listeTexte.setBackground(Color.RED);
			}
			panelListe.add(listeTexte);
			centre.add(panelListe);
			JButton bouton1 = creerBouton("Favoris");
			panelListe.add(bouton1);
			JButton bouton2 = creerBouton("+");
			JButton bouton3 = creerBouton("-");
			panelListe.add(bouton2);
			panelListe.add(bouton3);
			listeBoutons.add(bouton1);
		}
		contenu.add(centre, BorderLayout.CENTER);
		
		
		JPanel prix_total = new JPanel(new FlowLayout());
		JTextArea prix = new JTextArea(" Prix total : "+l.prixListe()+"€");
		prix.add(prix_total);
		centre.add(prix,BorderLayout.CENTER);
		
		
		
		for (int j = 0; j < listeBoutons.size(); j++) {
			JButton bouton = listeBoutons.get(j);
			IngredientCourses lingre= liste.get(j);
			if (bouton.getText().equals("Favoris")) {
				bouton.addActionListener(new ActionBoutonFavoris(lingre, bouton));
			}
		}
		
		
		
		fenetre.setLocation(100, 200);
		fenetre.setSize(new Dimension(300,200));
		
		// afficher la fenêtre
		fenetre.pack();			// redimensionner la fenêtre
		fenetre.setVisible(true);	// l'afficher
		
		// Construire le contrôleur (gestion des événements)
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		boutonRetour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fenetre.dispose();
			}
		});
		boutonReprendre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fenetre.dispose();
				new VueListeCourses(lbu, l, g);
			}
		});
		boutonVider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fenetre.dispose();
				new VueListeCourses(new ListeCourses(new ArrayList<IngredientCourses>(),LocalDate.now()), l, g);
			}
		});
		
		boutonGM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				l.ajouterGardeManger(g);
				ArrayList<ListeCourses> list=new ArrayList<ListeCourses>();
				list.add(l);
				Budget Achat=new Budget( 104, list);
				new  VueBudget(Achat);
			}	
		});
		
	}

	public class ActionBoutonFavoris implements ActionListener {

		private IngredientCourses ingredient;
		private JButton bouton;
		
		public ActionBoutonFavoris(IngredientCourses ingredient, JButton bouton) {
			this.ingredient = ingredient;
			this.bouton = bouton;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!ingredient.estFavori()) {
				ingredient.ajouterFavoris();
				bouton.setBackground(Color.RED);
			} else {
				ingredient.supprimerFavoris();
				bouton.setBackground(Color.WHITE);
			}
		}
		
	}
	
	public class ActionBoutonPlus implements ActionListener {

		private ArrayList<IngredientCourses> liste;
		private IngredientCourses ingredient;
		
		public ActionBoutonPlus(IngredientCourses ingredient, ArrayList<IngredientCourses> l) {
			this.ingredient = ingredient;
			this.liste = l;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			for (IngredientCourses ing : this.liste) {
				if (ing == this.ingredient) {
					ing.incQuantite();
				}
			}
		}
		
	}
	
	public class ActionBoutonMoins implements ActionListener {

		private ArrayList<IngredientCourses> liste;
		private IngredientCourses ingredient;
		
		public ActionBoutonMoins(IngredientCourses ingredient, ArrayList<IngredientCourses> l) {
			this.ingredient = ingredient;
			this.liste = l;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			for (IngredientCourses ing : liste) {
				if (ing == this.ingredient) {
					if (ing.getQuantite() == 1) {
						this.liste.remove(ing);
					} else {
						ing.decQuantite();
					}
				}
			}
		}		
	}
	

	public static void main(String[] args) {
		ListeCourses listetest = new ListeCourses(new ArrayList<IngredientCourses>(),LocalDate.now());
		IngredientCourses pates = new IngredientCourses("pâtes", "épicerie", 3, "", 1.0);
		IngredientCourses poulet = new IngredientCourses("poulet", "viande", 1, "", 3.5);
		listetest.ajouterIngredient(pates);
		listetest.ajouterIngredient(poulet);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new VueListeCourses(listetest, new ListeCourses(new ArrayList<IngredientCourses>(),LocalDate.now()), new GardeManger(new ArrayList<>()));
			}
		});
	}
	
	private JButton creerBouton(String texte) {
		return new JButton(texte);
	}
	
	public void mettreAJourProduit() {
		// TODO Auto-generated method stub
		List<JButton> listeBoutonsfav = new ArrayList<>();
		List<JButton> listeBoutonsplus = new ArrayList<>();
		List<JButton> listeBoutonsmoins = new ArrayList<>();
		for (IngredientCourses ing : this.liste) {
			JPanel panelproduit = new JPanel(new FlowLayout());
			JPanel boutons = new JPanel(new FlowLayout());
			JTextArea produit = new JTextArea();
			produit.setText(ing.getQuantite()+"		"+ing.getNom()+"		"+ing.getPrix()+"		"+ing.getPrix()*ing.getQuantite()+"		");
			panelproduit.add(produit);
			JButton bouton1 = creerBouton("Favoris");
			//bouton1.setIcon(new ImageIcon(this.getClass().getResource("Favoris.png")));
			boutons.add(bouton1);
			listeBoutonsfav.add(bouton1);
			JButton bouton2 = creerBouton("+");
			boutons.add(bouton2);
			listeBoutonsplus.add(bouton2); 
			JButton bouton3 = creerBouton("-");
			
			boutons.add(bouton3);
			listeBoutonsmoins.add(bouton3);
			panelproduit.add(boutons);
			this.centre.add(panelproduit);
		}
		
		for (int j = 0; j < listeBoutonsfav.size(); j++) {
			JButton bouton = listeBoutonsfav.get(j);
			IngredientCourses lingre= liste.get(j);
			if (bouton.getText().equals("Favoris")) {
				bouton.addActionListener(new ActionBoutonFavoris(lingre, bouton));
			}
		}
		
		for (int j = 0; j < listeBoutonsplus.size(); j++) {
			JButton bouton = listeBoutonsplus.get(j);
			IngredientCourses lingre= liste.get(j);
			if (bouton.getText().equals("Favoris")) {
				bouton.addActionListener(new ActionBoutonPlus(lingre, this.liste));
			}
		}
		
		for (int j = 0; j < listeBoutonsmoins.size(); j++) {
			JButton bouton = listeBoutonsmoins.get(j);
			IngredientCourses lingre= liste.get(j);
			if (bouton.getText().equals("Favoris")) {
				bouton.addActionListener(new ActionBoutonMoins(lingre, this.liste));
			}
		}
	}
	
	public void mettreAJourPrixtotal() {
		// TODO Auto-generated method stub
		double prixliste = 0.0;
		for (IngredientCourses ing : this.liste) {
			prixliste += ing.getPrix()*ing.getQuantite();
		}
		prixtot.setText(" Prix total : " + prixliste + "€");
		JPanel prix_total = new JPanel(new FlowLayout());
		prix_total.add(prixtot);
		this.centre.add(prix_total);
	}
}

