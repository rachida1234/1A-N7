package src.liste_courses;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import src.recettes.VueListeRecettes;

/**
 * Classe qui crée la vue d'une liste de courses
 *
 * @author Louis Bru
 * @version 6.6.7
 * */
public class VueListeCourses2 {
	
	private ArrayList<IngredientCourses> liste;
	private ListeCourses liste_backup;
	
	public VueListeCourses2(ListeCourses l, ListeCourses lbu) {
		JFrame fenetre = new JFrame("Liste de courses");
		JButton boutonRetour = new JButton("Retour au menu");
		boutonRetour.setIcon(new ImageIcon(this.getClass().getResource("accueil.png")));
	    JButton boutonReprendre = new JButton("Reprendre la dernière liste de courses");
	    boutonReprendre.setIcon(new ImageIcon(this.getClass().getResource("importer.png")));
	    JButton boutonVider = new JButton("Vider la liste de courses");
	    boutonVider.setIcon(new ImageIcon(this.getClass().getResource("vider.png")));
		this.liste = l.getCourse();
		this.liste_backup = lbu;
		
		Container contenu = fenetre.getContentPane();
		contenu.setLayout(new BorderLayout());
		
		// Vue du haut, avec le bouton retour
		JPanel haut = new JPanel(new BorderLayout());
		JPanel icones = new JPanel(new FlowLayout());
		icones.add(boutonRetour);
		icones.add(boutonReprendre);
		icones.add(boutonVider);
		haut.add(icones);
		contenu.add(haut,BorderLayout.PAGE_START);
		// Construction de la vue centrale, avec les recettes et les boutons associés
		JPanel centre = new JPanel(new GridLayout(l.getSize()+2, 1));
		List<JButton> listeBoutons = new ArrayList<>();
		
		JPanel colonnes = new JPanel(new FlowLayout());
		JTextArea nom_colonnes = new JTextArea("Quantité		Produit		Prix unitaire		Prix total		Ajouter aux favoris");
		colonnes.add(nom_colonnes);
		centre.add(colonnes,BorderLayout.CENTER);
		
		for (IngredientCourses ing : this.liste) { 
			JPanel panelListe = new JPanel(new FlowLayout());
			JTextArea listeTexte = new JTextArea(ing.getQuantite()+"		"+ing.getNom()+"		"+ing.getPrix()+"		"+ing.getPrix()*ing.getQuantite()+"		");
			if (ing.estFavori()) {
				listeTexte.setFont(new Font(Font.SERIF, Font.BOLD, 12));
				listeTexte.setBackground(Color.RED);
			}
			panelListe.add(listeTexte, BorderLayout.WEST);
			centre.add(panelListe);
			JButton bouton1 = creerBouton("Favoris");
			bouton1.setIcon(new ImageIcon(this.getClass().getResource("Favoris.png")));
			panelListe.add(bouton1, BorderLayout.EAST);
			listeBoutons.add(bouton1);
		}
		contenu.add(centre, BorderLayout.CENTER);
		
		JPanel prix_total = new JPanel(new FlowLayout());
		JTextArea prix = new JTextArea("Prix total : "+l.prixListe()+"€");
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
		fenetre.setSize(new Dimension(700,400));
		
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
				new VueListeCourses2(lbu, l);
			}
		});
		boutonVider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fenetre.dispose();
				new VueListeCourses2(new ListeCourses(new ArrayList<IngredientCourses>(),LocalDate.now()), l);
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
	

	public static void main(String[] args) {
		ListeCourses listetest = new ListeCourses(new ArrayList<IngredientCourses>(),LocalDate.now());
		IngredientCourses pates = new IngredientCourses("pâtes", "épicerie", 3, "", 1.0);
		IngredientCourses poulet = new IngredientCourses("poulet", "viande", 1, "", 3.5);
		listetest.ajouterIngredient(pates);
		listetest.ajouterIngredient(poulet);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new VueListeCourses2(listetest, new ListeCourses(new ArrayList<IngredientCourses>(),LocalDate.now()));
			}
		});
	}
	
	private JButton creerBouton(String texte) {
		return new JButton(texte);
	}
}

