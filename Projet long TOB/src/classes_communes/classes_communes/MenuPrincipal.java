package src.classes_communes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import src.Cuisine.Vuegardemanger;
import src.agenda.RepasDuJour;
import src.base_de_donnees.Utilisateur;
import src.budget.VueBudget;
import src.liste_courses.VueListeCourses;
import src.recettes.Recette;
import src.recettes.VueListeRecettes;
import src.supermarche.VueSupermarche;


public class MenuPrincipal {
	
	private JFrame fenetre;
	
	private JPanel calendrier = new JPanel(new GridBagLayout());
	private GridBagConstraints c = new GridBagConstraints();
	private LocalDate date2 = LocalDate.now();

   public MenuPrincipal(Utilisateur user) {
	   
	   	MenuPrincipal m = this;
	   	
		initialiser(user);
		JButton boutonBudget = new JButton("Budget");
		boutonBudget.setIcon(new ImageIcon(this.getClass().getResource("money.png")));
		JButton boutonGM = new JButton("Cuisine");
		boutonGM.setIcon(new ImageIcon(this.getClass().getResource("cuisine.png")));
		JButton boutonListeCourses = new JButton("Liste de courses"); 
		boutonListeCourses.setIcon(new ImageIcon(this.getClass().getResource("liste.png")));
		JButton boutonSupermarche = new JButton("Supermarche");
		//boutonSupermarche.setIcon(new ImageIcon(this.getClass().getResource("supermarche.png")));
		
		this.fenetre = new JFrame("Menu principal (" + user.getNom() + ")");
		
		Container contenu = this.fenetre.getContentPane();
		contenu.setLayout(new BorderLayout());
		
		// Date
		JPanel haut = new JPanel(new BorderLayout());
		LocalDateTime date = LocalDateTime.now();
		String affichage_date = date.getDayOfWeek().name() + " " + date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
		JTextField texte_date = new JTextField(affichage_date + "  " + date.getHour() + "h" + date.getMinute());
		
		// Barre de menu
		JMenuBar menu = new JMenuBar();
		JMenu barre = new JMenu("Barre");
		JMenuItem mesRecettes = new JMenuItem("Mes recettes");
		JMenuItem mesIngredients = new JMenuItem("Mes produits");
		JMenuItem quitter = new JMenuItem("Quitter");
		barre.add(mesRecettes);
		barre.add(mesIngredients);
		barre.add(quitter);
		
		menu.add(barre);
		
		// Icones
		JPanel icones = new JPanel(new FlowLayout());
		icones.add(boutonBudget);
		icones.add(boutonListeCourses);
		
		haut.add(menu, BorderLayout.WEST);
		haut.add(texte_date, BorderLayout.CENTER);
		haut.add(icones, BorderLayout.EAST);
		
		contenu.add(haut, BorderLayout.PAGE_START);
		
		// Agenda
		JTextArea mois = new JTextArea(getMois(date.getMonthValue()));
		mois.setAlignmentX(SwingConstants.CENTER);
		contenu.add(mois, BorderLayout.NORTH);
		
		c.weightx = 1.0;
		calendrier.add(new JTextArea(""), 0);
		for (int i = 0; i < 7; i++) {
			LocalDate newDate = date2.plusDays(i);
			JTextArea jour = new JTextArea(newDate.getDayOfMonth() + "");
			if (i == 6) {
				c.gridwidth = GridBagConstraints.REMAINDER;
			}
			calendrier.add(jour, c);
			calendrier.getComponent(i+1).setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		}
		c.weightx = 0;
		c.gridwidth = 1;
		calendrier.add(new JTextArea("Repas du midi"), c);
		
		for (int j = 0; j < 7; j++) {
			LocalDate newDate = date2.plusDays(j);
			JTextArea texteMidi;
			if (user.getAgenda().getListeRepas().get(newDate) == null) {
				texteMidi = new JTextArea("");
			}
			else {
				if (user.getAgenda().getListeRepas().get(newDate).getRecetteMidi() == null) {
					texteMidi = new JTextArea("");
				}
				else {
				Recette recetteMidi = user.getAgenda().getListeRepas().get(newDate).getRecetteMidi();
				texteMidi = new JTextArea(recetteMidi.getNom());
				}
			}
			c.weighty = 3.0;
			if (j == 6) {
				c.gridwidth = GridBagConstraints.REMAINDER;
			}
			calendrier.add(texteMidi, c);
		}
		c.weightx = 0;
		c.gridwidth = 1;
		calendrier.add(new JTextArea("Repas du soir"), c);
		
		for (int k = 0; k < 7; k++) {
			LocalDate newDate = date2.plusDays(k);
			JTextArea texteSoir;
			if (user.getAgenda().getListeRepas().get(newDate) == null) {
				texteSoir = new JTextArea("");
			}
			else {
				if (user.getAgenda().getListeRepas().get(newDate).getRecetteSoir() == null) {
					texteSoir = new JTextArea("");
				}
				else {
					Recette recetteSoir = user.getAgenda().getListeRepas().get(newDate).getRecetteSoir();
					texteSoir = new JTextArea(recetteSoir.getNom());
					
				}
			} 
			c.weighty = 3.0;
			calendrier.add(texteSoir, c);
		}
			
		majAgenda(user);
		contenu.add(calendrier, BorderLayout.CENTER);
		
		JPanel bas = new JPanel(new BorderLayout());

		// Boutons
		JPanel boutons = new JPanel(new FlowLayout());
		boutons.add(boutonGM);
		boutons.add(boutonSupermarche);
		
		bas.add(boutons, BorderLayout.SOUTH);
		contenu.add(bas, BorderLayout.PAGE_END);
		
        try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.fenetre.setLocation(800, 500);
		
		this.fenetre.setSize(new Dimension(800,500));
		
		// afficher la fenêtre
		this.fenetre.setSize(700, 400);			// redimensionner la fenêtre
		this.fenetre.setVisible(true);	// l'afficher
		
		// Construire le contrôleur (gestion des événements)
		boutonGM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Vuegardemanger(user.getGardeManger());
			}
		});
		
		boutonListeCourses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new VueListeCourses(user.getListeCourses(), user.getListeCourses(), user.getGardeManger());
			}
		});

		boutonBudget.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new VueBudget(user.getBudget());
			}	
		});
		
		quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fenetre.dispose();
			}
		});
		
		mesRecettes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new VueListeRecettes(user.getListeRecettes(), m);
			}
		});
		
		boutonSupermarche.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new VueSupermarche(user.getSupermarche());
			}	
		});
	}
	
   public void majAgenda(Utilisateur user) {
		
		for (int j = 0; j < 7; j++) {
			LocalDate newDate = date2.plusDays(j);
			JTextArea texteMidi = (JTextArea) calendrier.getComponent(j+9);
			if (user.getAgenda().getListeRepas().get(newDate) == null) {
				texteMidi.setText("");
			}
			else {
				if (user.getAgenda().getListeRepas().get(newDate).getRecetteMidi() == null) {
					texteMidi.setText("");
				}
				else {
				Recette recetteMidi = user.getAgenda().getListeRepas().get(newDate).getRecetteMidi();
				texteMidi.setText(recetteMidi.getNom());
				}
			}
		}
		
		for (int k = 0; k < 7; k++) {
			LocalDate newDate = date2.plusDays(k);
			JTextArea texteSoir = (JTextArea) calendrier.getComponent(k+17);
			if (user.getAgenda().getListeRepas().get(newDate) == null) {
				texteSoir.setText("");
			}
			else {
				if (user.getAgenda().getListeRepas().get(newDate).getRecetteSoir() == null) {
					texteSoir.setText("");
				}
				else {
					Recette recetteSoir = user.getAgenda().getListeRepas().get(newDate).getRecetteSoir();
					texteSoir.setText(recetteSoir.getNom());
					
				}
			}
		}
		
	}

	private void initialiser(Utilisateur user) {
		// TODO Auto-generated method stub
		if (user == null) {
			new VueNom();
		}
	}


	public static String getMois(int mois) {
		String res = null;
		switch (mois) {
			case 1 :
				res = "Janvier";
				break;
			case 2 :
				res = "Fevrier";
				break;
			case 3 : 
				res = "Mars";
				break;
			case 4 :
				res = "Avril";
				break;
			case 5 :
				res = "Mai";
				break;
			case 6 : 
				res = "Juin";
				break;
			case 7 :
				res = "Juillet";
				break;
			case 8 :
				res = "Août";
				break;
			case 9 : 
				res = "Septembre";
				break;
			case 10 :
				res = "Octobre";
				break;
			case 11 :
				res = "Novembre";
				break;
			case 12 :
				res = "Décembre";
				break;	
		}
		return res;		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MenuPrincipal(Utilisateur.utilisateur);
			}
		});
	}

}
