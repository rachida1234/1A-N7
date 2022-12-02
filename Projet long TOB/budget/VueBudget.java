package src.budget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import src.Cuisine.GardeManger;
import src.Cuisine.IngredientGM;
import src.Cuisine.Poubelle_it2.Poubelle;
import src.base_de_donnees.Utilisateur;
import src.classes_communes.MenuPrincipal;
import src.liste_courses.IngredientCourses;
import src.liste_courses.ListeCourses;
/** Interface graphique pour la gestion de budget.
 * @author  Rachida Oussakel
 */
public class VueBudget {
	
		public VueBudget(Budget Historique) {
	        
	        //Creation de Frame
	        JFrame frame = new JFrame("Gestion de budget");
	    	JButton boutonRetour = new JButton("Retour au menu");
	    	boutonRetour.setIcon(new ImageIcon(this.getClass().getResource("accueil.png")));
	        JButton m2 = new JButton("Etablir mes dépenses entre deux dates");
	        m2.setIcon(new ImageIcon(this.getClass().getResource("money.png")));
	    	Container contenu = frame.getContentPane();
	    	contenu.setLayout(new BorderLayout());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	        frame.setSize(700, 400); 
	        frame.setLayout(new BorderLayout());
	        
	        
	       //Creation du panel et des boutons
	        JPanel bas = new JPanel(new BorderLayout());
	        JPanel icones = new JPanel(new FlowLayout());
	        
	        icones.add(boutonRetour,BorderLayout.SOUTH);
			icones.add(m2,BorderLayout.SOUTH);
			bas.add(icones);
			contenu.add(bas,BorderLayout.SOUTH);
			// Construction de la vue centrale, avec les recettes et les boutons associés
			JPanel centre = new JPanel(new GridLayout(Historique.getHistoriqueAchat().size()+2, 1));
			centre.setBackground(Color.BLUE);
			JPanel colonnes = new JPanel(new FlowLayout()); 
			colonnes.setBackground(Color.BLUE); 
			//JTextArea nom_colonnes = new JTextArea("    Mois		        Budget(Euro)	           ");
			String[] columns=new String[]{"MOIS","BUDGET(EURO)"};
			Object[][] lignes=new Object[][] {
				{"JANVIER",Historique.getDepensePeriode(LocalDate.parse("2022-01-01"),LocalDate.parse("2022-01-31"))},
				{"FEVRIER",Historique.getDepensePeriode(LocalDate.parse("2022-02-01"),LocalDate.parse("2022-02-28"))} ,
				{"MARS",Historique.getDepensePeriode(LocalDate.parse("2022-03-01"),LocalDate.parse("2022-03-31"))},
				{"AVRIL",Historique.getDepensePeriode(LocalDate.parse("2022-04-01"),LocalDate.parse("2022-04-30"))} ,
				{"MAI",Historique.getDepensePeriode(LocalDate.parse("2022-05-01"),LocalDate.parse("2022-06-01"))} ,
				{"JUIN",Historique.getDepensePeriode(LocalDate.parse("2022-06-02"),LocalDate.parse("2022-06-30"))},
				{"JUILLET",Historique.getDepensePeriode(LocalDate.parse("2022-07-01"),LocalDate.parse("2022-07-31"))} ,
				{"AOUT",Historique.getDepensePeriode(LocalDate.parse("2022-08-01"),LocalDate.parse("2022-08-31"))} ,
				{"SEPTEMBRE",Historique.getDepensePeriode(LocalDate.parse("2022-09-01"),LocalDate.parse("2022-09-30"))},
				{"OCTOBRE",Historique.getDepensePeriode(LocalDate.parse("2022-10-01"),LocalDate.parse("2022-10-31"))} ,
				{"NOVEMBRE",Historique.getDepensePeriode(LocalDate.parse("2022-11-01"),LocalDate.parse("2022-11-30"))} ,
				{"DECEMBRE",Historique.getDepensePeriode(LocalDate.parse("2022-12-01"),LocalDate.parse("2022-12-31"))} ,
			};
			
			JTable table=new JTable(lignes,columns);
			JScrollPane scroll=new JScrollPane(table);
			table.setFillsViewportHeight(true);
			
			//centre.add(colonnes,BorderLayout.CENTER);
			contenu.add(scroll, BorderLayout.CENTER);
			m2.addActionListener(new ActionListener() {
				 
	   			@Override 
	   			public void actionPerformed(ActionEvent arg0) {
	   				JFrame maNewFrame = new JFrame("Budget!"); 
	   		    	Container contenu = maNewFrame.getContentPane();
	   				contenu.setLayout(new BorderLayout());
	   		        contenu.add(creatElement(), BorderLayout.WEST);
	   		        JPanel status = new JPanel(new GridLayout(4,1));
	   		        JTextField dateDebut = new JTextField(30);
	   		        dateDebut.setBackground(Color.PINK);
	   		        JTextField dateFin = new JTextField(30);
	   		        dateFin.setBackground(Color.PINK);
	   		        String Debut = dateDebut.getText();
	   		        dateDebut.setForeground(Color.WHITE); 
	   		        dateFin.setForeground(Color.WHITE);
	   		        String Fin = dateFin.getText();
	   		        
	   				status.add(dateDebut); 
	   				status.add(dateFin);
	   		        contenu.add(status, BorderLayout.EAST);
	   		        JButton calculer = new JButton("Calculer");
	   		        calculer.setIcon(new ImageIcon(this.getClass().getResource("calculator.png")));
	   		        calculer.setForeground(Color.BLACK);
	   		        calculer.addActionListener(new ActionListener() {
	   					 
	   					@Override
	   					public void actionPerformed(ActionEvent arg0) {
	   						double budget=Historique.getDepensePeriode(LocalDate.parse(Debut),LocalDate.parse(Fin));
	   						JTextArea Budget = new JTextArea();
	   		   				Budget.append("Le budget total depensé est : "+budget+ "euro");
	   		   			    status.add(Budget);
	   		   			    contenu.add(status);
	   		   			    maNewFrame.setLocation(200, 400);
	   	 		            maNewFrame.setSize(700, 400);
	   	 		    	    maNewFrame.setVisible( true );
	   		   		  } 
	   		}
	   		);
	   		    contenu.add(calculer,BorderLayout.SOUTH);
	   	        maNewFrame.setLocation(200, 400);
	   	    	maNewFrame.setSize(700, 400);
	   	    	maNewFrame.setVisible( true );
	   			}
	        }
	   	    );
	        boutonRetour.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					frame.dispose();
				}
			}); 
		     frame.setVisible( true );   
	    }
		 
	 	//Creation des cases pour renseigner les dates
	 	public JPanel creatElement() {
	 				JPanel status = new JPanel(new GridLayout(4,1));
	 				JLabel dateDebut = new JLabel("     Date début : ");
	 				dateDebut.setFont(new Font("Serif",Font.BOLD,20)); 
	 				JLabel dateFin = new JLabel("     Date fin :");
	 				dateFin.setFont(new Font("Serif",Font.BOLD,20));
	 				JLabel condition = new JLabel("                                     Date entrée en format AA-MM-JJ");
	 				condition.setFont(new Font("Serif",Font.BOLD,15));
	 				status.add(dateDebut);
	 				status.add(dateFin);
	 				status.add(condition);
	 				return status;
	    }
		public static void main(String[] args) throws Exception {
			ArrayList<ListeCourses> Achats = new ArrayList<>();
		    Budget budget = new Budget(0,Achats);
		    ListeCourses listetest = new ListeCourses(new ArrayList<IngredientCourses>(),LocalDate.now());
			IngredientCourses pates = new IngredientCourses("pâtes", "épicerie", 3, "", 10.0);
			IngredientCourses poulet = new IngredientCourses("poulet", "viande", 1, "", 3.5);
			listetest.ajouterIngredient(pates);
			listetest.ajouterIngredient(poulet);
			Achats.add(listetest);
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					new VueBudget(budget);
				}
			});
		}
		
		
	}



