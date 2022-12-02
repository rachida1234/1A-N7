
package src.Cuisine.Poubelle_it2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import src.Cuisine.GardeManger;
import src.Cuisine.IngredientGM;
import src.Cuisine.Vuegardemanger;
public class VuePoubelle {
	private Poubelle poubelle;
	private GardeManger maGardeManger;
	private Map<IngredientGM,LocalDate> produitsJetés;
	
	public VuePoubelle(Poubelle poubelle,GardeManger maGardeManger) {
        
		
        //Creation de Frame
        JFrame frame = new JFrame("Ma poubelle");
        JLabel image=new JLabel(new ImageIcon(this.getClass().getResource("fig1.png")));
        frame.add(image);
        
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(700, 400);
      //Creation du panel et des boutons
        JPanel panel = new JPanel();  
		/**JButton menu = new JButton("Retour Menu Principal");
		menu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
			
		}); */
		// panel.add(menu, BorderLayout.NORTH);
        //Creation du menu et des boutons 
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Mon taux de gaspillage    ");
        JMenuItem m2 = new JMenuItem("Retour au menu");
        JMenuItem m3 = new JMenuItem("            Produits jetés");
        m2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		}); 
	 
        m3.addActionListener(new ActionListener() { 
			
     			@Override
     			public void actionPerformed(ActionEvent arg0) {
     				
     				Map<IngredientGM,LocalDate> ingredient=poubelle.getProduitsJetes();
     				//Construire une nouvelle fenêtre pour afficher les produits jetés
    				JFrame Newframe = new JFrame("Produits jetés");
     				Container contenu = Newframe.getContentPane();
     				contenu.setLayout(new BorderLayout());
    		    	JPanel status = new JPanel(new GridLayout(4,1));
    		    	for (IngredientGM p : ingredient.keySet()) {
     					JLabel jlabel = new JLabel(p.getNom());  
     					status.add(jlabel);  
     				} 
       				JLabel date_vidage=new JLabel(" La date du dernier vidage est :");
       				date_vidage.setFont(new Font("Serif",Font.BOLD,15));
       				status.add(date_vidage);
    		    	JLabel date = new JLabel(poubelle.getDernierVidagePoubelle().toString());
    		    	date.setFont(new Font("Serif",Font.BOLD,15));
    		    	status.add(date); 
     				contenu.add(status, BorderLayout.CENTER);
     				JButton m2 = new JButton("Vider ma poubelle");
     				m2.setIcon(new ImageIcon(this.getClass().getResource("vider.png")));
     				m2.addActionListener(new ActionListener() {
     					@Override
     		 			public void actionPerformed(ActionEvent arg0) {
     		 				
     						poubelle.viderPoubelle();
     		 				contenu.removeAll();
     		 				contenu.repaint(); 
     		 				Newframe.setVisible( true );
     		 		 }  
     		         } 
     		 		);
     				contenu.add(m2,BorderLayout.SOUTH);
     			    Newframe.setLocation(200, 400);
     		        Newframe.setSize(700, 400);
     		    	Newframe.setVisible( true );
     		    	Newframe.repaint();
     			}
             }
     		);
       
        mb.add(m2);
        mb.add(m1);
        mb.add(m3);
        JMenuItem m21 = new JMenuItem("Total");
        JMenuItem m22 = new JMenuItem("Entre deux dates");
        
        m21.addActionListener(new ActionListener() {
			 
   			@Override
   			public void actionPerformed(ActionEvent arg0) {
   				double budget=poubelle.getTotalBudgetGaspille();
   				JFrame maNewFrame = new JFrame("Gaspillage Total!");
   		    	Container contenu = maNewFrame.getContentPane();
   				contenu.setLayout(new BorderLayout());
   		        JPanel status = new JPanel(new GridLayout(4,1));
   				JLabel Budget = new JLabel(" Le budget total de gaspillage est : " + budget + " euro");
   				status.add(Budget);
	   			contenu.add(status,BorderLayout.CENTER); 
 				maNewFrame.setLocation(100, 50);
 		        maNewFrame.setSize(700, 400);
 		    	maNewFrame.setVisible( true );
   			}
            }
   			);
         m22.addActionListener(new ActionListener() {
			
   			@Override
   			public void actionPerformed(ActionEvent arg0) {
   				
   				Map<IngredientGM,LocalDate> ingredient=poubelle.getProduitsJetes();
   				JFrame maNewFrame = new JFrame("Gaspillage!");
   		    	Container contenu = maNewFrame.getContentPane();
   				contenu.setLayout(new BorderLayout());
   		        contenu.add(creatElement(), BorderLayout.WEST);
   		        JPanel status = new JPanel(new GridLayout(4,1));
   		        JTextField dateDebut = new JTextField(20);
   		        JTextField dateFin = new JTextField(20);
   		        String Debut = dateDebut.getText();
   		        dateFin.setBackground(Color.PINK);
   		        dateDebut.setBackground(Color.PINK);
   		        String Fin = dateFin.getText();
   		        
   				status.add(dateDebut); 
   				status.add(dateFin);
   		        contenu.add(status, BorderLayout.EAST);
   		        JButton calculer = new JButton("Calculer");
   		        calculer.setIcon(new ImageIcon(this.getClass().getResource("calculator.png")));
   		        calculer.addActionListener(new ActionListener() {
   					 
   					@Override
   					public void actionPerformed(ActionEvent arg0) {
   						double budget=poubelle.getBudgetGaspillePeriode(LocalDate.parse(Debut),LocalDate.parse(Fin));
   						JTextArea Budget = new JTextArea();
   		   				Budget.append("Le budget total de gaspillage est : "+budget+ "euro");
   		   			    status.add(Budget);
   		   			    contenu.add(status);
   		   			    maNewFrame.setLocation(200, 400);
   	 		            maNewFrame.setSize(300, 150);
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
        m1.add(m21);
        m1.add(m22);

        //Creation du panel et des boutons
        //JPanel panel = new JPanel(); 
        JLabel label = new JLabel("Entrer produit");
        JTextField produit = new JTextField(40); // accepter jusqu'au 20 caractères
        JButton Jeter = new JButton("Jeter");
        Jeter.setIcon(new ImageIcon(this.getClass().getResource("poubelle.png")));
        Jeter.addActionListener(new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nomproduit = produit.getText();
				ArrayList<IngredientGM> ingredient=maGardeManger.getIngredients();
			   for (int i=0; i<ingredient.size();i++) {
				    IngredientGM p=ingredient.get(i);
				    if (p.getNom().equals(nomproduit)) {
				    	maGardeManger.retirerIngredientGM(p);
				    	poubelle.jeterDansPoubelle(p);
				        maGardeManger.afficherContenu();
				        new Vuegardemanger(maGardeManger);
				    }
				    JTextField produit=new JTextField(80);
				}
			} 
		}
	
);
        panel.add(label);  
        panel.add(produit);
        panel.add(Jeter);

        // Texte à entrer en bas de la page
        JTextArea ta = new JTextArea();
 
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
 	//Creation des cases pour renseigner les dates
 	public JPanel creatElement() {
 				JPanel status = new JPanel(new GridLayout(4,1));
 				JLabel dateDebut = new JLabel("   Date début :");
 				dateDebut.setFont(new Font("Serif",Font.BOLD,20));
 				JLabel dateFin = new JLabel("   Date fin :");
 				dateFin.setFont(new Font("Serif",Font.BOLD,20));
 				JLabel condition = new JLabel("                                     Date entrée en format AA-MM-JJ");
 				condition.setFont(new Font("Serif",Font.BOLD,15));
 				status.add(dateDebut);
 				status.add(dateFin);
 				status.add(condition);
 				return status;
    }
	public static void main(String[] args) throws Exception {
		Map<IngredientGM,LocalDate> produitsJetés = new HashMap<>(); 
	    Poubelle poubelle = new Poubelle(produitsJetés,LocalDate.parse("2021-05-15"));
	    IngredientGM lait = new IngredientGM("Lait",LocalDate.parse("2022-05-15"),1,10, "L","ProteineAnimale");
	    IngredientGM confiture = new IngredientGM("Confiture",LocalDate.parse("2023-05-15"),1,13,"boite" ,"EpicerieSucree");
	    IngredientGM nuggets = new IngredientGM("Nuggets",LocalDate.parse("2022-04-15"),2,11,"Kg","ProteineAnimale");
	    ArrayList<IngredientGM> Produits = new ArrayList<>();
	    Collections.addAll(Produits,confiture,lait,nuggets);
        GardeManger maGardeManger=new GardeManger(Produits);
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        EventQueue.invokeLater(new Runnable() {
			public void run() { 
				new VuePoubelle(poubelle,maGardeManger);
			}
		});
	}
}
