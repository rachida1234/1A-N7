package src.Cuisine;
import src.Cuisine.Poubelle_it2.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import src.Cuisine.Poubelle_it2.Poubelle;
import src.Cuisine.Poubelle_it2.VuePoubelle;
import src.liste_courses.ListeCourses;

public class Vuegardemanger {
	/** Model de la garde à manger*/
    public GardeManger garde_manger;
	/** Fenêtre principale */
    private JFrame fenetre;
    /** Contenu de la fenetre*/
    private Container contenu ;
    /** Ingredients de la garde à manger*/
    public JPanel ingredients ;
    private Map<IngredientGM,LocalDate> produitsJetés = new HashMap<>(); 

    /** Construire le jeu*/
    public Vuegardemanger(GardeManger garde_manger1) {
    	/**Initialisation de la garde à manger*/
    	this.garde_manger=garde_manger1; 
		//	Définir la fenêtre principale
    	this.fenetre=new JFrame("Garde Manger");
		// Construire le contrôleur (gestion des événements)
		contenu = this.fenetre.getContentPane();
		contenu.setLayout(new BorderLayout()); 
    	//JMenuBar menubar = new JMenuBar();
    	//JMenu menu = new JMenu("Retour Menu Principale");
    	//menubar.add(menu);
    	contenu.add(northbar1(),BorderLayout.NORTH);
    	contenu.add(organiser(garde_manger),BorderLayout.CENTER);
    	//Afficher la quantité, l'unité et le rayon de chaque ingredient 
    	
    	
    	// creation des button ajouter et retirer ingredient
		contenu.add(creatButton(),BorderLayout.SOUTH); 	
    	this.fenetre.setLocation(600, 400); //localiser la fenêtre
    	this.fenetre.setSize(700,400);			// redimensionner la fenêtre
		this.fenetre.setVisible(true);	// l'afficher
		
		// Construire le contrôleur (gestion des événements)
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    }
    
    
    
    public JPanel creatButton() {
		JPanel status = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JMenuBar mb = new JMenuBar();
		JButton ajout = new JButton("Ajouter");
		JButton supp = new JButton("Jeter un produit");
		supp.setIcon(new ImageIcon(this.getClass().getResource("supprimer.png")));
		ajout.setIcon(new ImageIcon(this.getClass().getResource("ajouter.png")));
		
		mb.add(supp);
		 
		supp.addActionListener( new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Poubelle poubelle = new Poubelle(produitsJetés,LocalDate.parse("2021-05-15"));
				new VuePoubelle(poubelle,garde_manger);
			}
			});	
		
		

    	ajout.addActionListener( new ActionListener()
    	{
    	public void actionPerformed(ActionEvent ae)
    	{
    	JFrame maNewFrame = new JFrame("Ajouter Ingredient");
    	Container contenu = maNewFrame.getContentPane();
		contenu.setLayout(new BorderLayout());
        contenu.add(creatElement(), BorderLayout.WEST);
        JPanel status2 = new JPanel(new GridLayout(5,1));
        JTextField produit = new JTextField(40);
        JTextField rayon = new JTextField(40);
        JTextField qte = new JTextField(40);
        JTextField unite = new JTextField(40);
        JTextField date = new JTextField(40);
         
		status2.add(produit);
		status2.add(rayon);
		status2.add(qte);
		status2.add(unite);
		status2.add(date);
        contenu.add(status2, BorderLayout.EAST);
        JButton ok = new JButton("Enregistrer");
        ok.setIcon(new ImageIcon(this.getClass().getResource("save.png")));
        ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nomproduit = produit.getText();
				String nomrayon = rayon.getText();
				String qteing = qte.getText();
				String u = unite.getText();
				String dateperemption = date.getText(); 
				IngredientGM ing =new IngredientGM(nomproduit,LocalDate.parse(dateperemption), Double.parseDouble(qteing),8,u, nomrayon);
				//Ajouter l'ingredient à la garde à manger
				garde_manger.ajouterIngredient(ing);
				new Vuegardemanger(garde_manger);
				//fermer la fentre à la fin d'enregitrement
				fenetre.dispose();
				maNewFrame.dispose();
				
			} 
		});
        contenu.add(ok,BorderLayout.SOUTH);
        maNewFrame.setLocation(190, 390);
    	maNewFrame.setSize(700,400);
    	maNewFrame.setVisible( true );
    	}
    	}
    	);
    	mb.add(ajout);
    	JButton Modifier=new JButton("Modifier");
    	Modifier.setIcon(new ImageIcon(this.getClass().getResource("Modifier.png")));
    	Modifier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame maNewFrame = new JFrame("Ajouter une quantité ");
		    	Container contenu = maNewFrame.getContentPane();
		    	contenu.setLayout(new BorderLayout());
		    	contenu.add(creatElement1(), BorderLayout.WEST);
		    	JPanel status2 = new JPanel(new GridLayout(2,1));
		        JTextField produit = new JTextField(40);
		        JTextField qte = new JTextField(40);
		        status2.add(produit);
				status2.add(qte);
				contenu.add(status2,BorderLayout.EAST);
				JButton ok = new JButton("Ajouter cette quantité");
				ok.setIcon(new ImageIcon(this.getClass().getResource("save.png")));
				ok.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String nomproduit = produit.getText();
						String qteretirer = qte.getText();
						for (int i = 0; i < garde_manger.getIngredients().size(); i++) {
						    if (garde_manger.getIngredients().get(i).getNom().equals(nomproduit)) {
						    	garde_manger.ajouterQuantite(garde_manger.getIngredients().get(i).getNom(),Double.parseDouble(qteretirer));
						    	maNewFrame.dispose(); 
						    	fenetre.dispose();
						    	new Vuegardemanger(garde_manger);
						   
						    	}
						   		
						}
					}
				});
				contenu.add(ok,BorderLayout.SOUTH);
		        maNewFrame.setLocation(200, 400);
		    	maNewFrame.setSize(700,400);
		    	maNewFrame.setVisible( true );
				
			}
		});	
    	
    	
    	mb.add(Modifier);
       
		status.add(mb);
		return status;
	}
    //Creation des cases pour savoir les informations à renseigner
    public JPanel creatElement() {
		JPanel status = new JPanel(new GridLayout(5,1));
		JLabel produit = new JLabel("Nom Produit :");
		produit.setFont(new Font("Serif",Font.BOLD,20));
		JLabel rayon = new JLabel("Rayon :");
		rayon.setFont(new Font("Serif",Font.BOLD,20));
		JLabel qte = new JLabel("Quantité Produit :");
		qte.setFont(new Font("Serif",Font.BOLD,20));
		JLabel unite = new JLabel("Unité : ");
		unite.setFont(new Font("Serif",Font.BOLD,20));
		JLabel date = new JLabel("Date de péremption");
		date.setFont(new Font("Serif",Font.BOLD,20));
		status.add(produit);
		status.add(rayon);
		status.add(qte);
		status.add(unite);
		status.add(date);
		return status;

    }
    
  //Creation des cases pour savoir les informations à renseigner
    public JPanel creatElement1() {
		JPanel status = new JPanel(new GridLayout(2,1));
		JLabel produit = new JLabel("Nom Produit :");
		produit.setFont(new Font("Serif",Font.BOLD,20));
		JLabel qte = new JLabel("Quantité à ajouter :");
		qte.setFont(new Font("Serif",Font.BOLD,20));
		status.add(produit);
		status.add(qte);
		return status;

    }
    
    public JPanel northbar1() {
    	JPanel status = new JPanel(new GridLayout(3,1));
    	JButton menu = new JButton("Retour Menu Principal");
    	menu.setIcon(new ImageIcon(this.getClass().getResource("accueil.png")));
    	menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fenetre.dispose();
			}
    	});
    	status.add(menu);
    	status.add(new JLabel("GARDE A MANGER :"));
    	status.add(northbar());
    	return status;
    }
   
    
    public JPanel northbar() {
    	
    	JPanel status = new JPanel(new GridLayout(1,7));
    	JLabel epiceriesalee = new JLabel("Epicerie salée");
    	epiceriesalee.setFont(new Font("Serif",Font.BOLD,20));
    	JLabel epiceriesucree = new JLabel("Epicerie sucrée");
    	epiceriesucree.setFont(new Font("Serif",Font.BOLD,20));
    	JLabel liquide = new JLabel("Boissons");
    	liquide.setFont(new Font("Serif",Font.BOLD,20));
    	JLabel viande = new JLabel("Protéine animale");
    	viande.setFont(new Font("Serif",Font.BOLD,20));
    	JLabel fruit = new JLabel("Fruits");
    	fruit.setFont(new Font("Serif",Font.BOLD,20));
    	JLabel legumes = new JLabel("Légumes");
    	legumes.setFont(new Font("Serif",Font.BOLD,20));
    	JLabel epices = new JLabel("Epices");
    	epices.setFont(new Font("Serif",Font.BOLD,20));
    	status.add(epiceriesalee);
    	status.add(epiceriesucree);
    	status.add(liquide);
    	status.add(viande);
    	status.add(fruit);
    	status.add(legumes);
    	status.add(epices);
    	return status;
    }
    
    
    public JPanel organiser(GardeManger garde_manger2) {
    	JPanel status = new JPanel(new GridLayout(1,7));
    	int max=7;
		
    	JPanel liquide = new JPanel(new GridLayout(max+1, 1));
    	JPanel proteineanimale = new JPanel(new GridLayout(max+1, 1));
    	JPanel fruits = new JPanel(new GridLayout(max+1, 1));
    	JPanel epiceriesalee = new JPanel(new GridLayout(max+1,1));
    	JPanel legumes = new JPanel(new GridLayout(max+1,1));
    	JPanel epiceriesucree = new JPanel(new GridLayout(max+1,1));
       	JPanel epices = new JPanel(new GridLayout(max+1,1));
    	
    	for (int i=0;i<garde_manger2.getIngredients().size();i++) {
    		JLabel ingredient = new JLabel(garde_manger2.getIngredients().get(i).getNom());
    		ingredient.setToolTipText("Quantité : "+garde_manger2.getIngredients().get(i).getQuantite()+" "+garde_manger2.getIngredients().get(i).getUnite() + "  " +garde_manger2.getIngredients().get(i).getRayon());    		
    		switch (garde_manger2.getIngredients().get(i).getRayon()) {
    		case "Boissons":
    			liquide.add(ingredient);
    			break;
    		case "Légumes":
    			legumes.add(ingredient);
    			break;
    		case "Protéine animale":
    			proteineanimale.add(ingredient);
    			break;
    		case "Fruits" :
    			fruits.add(ingredient);
    			break;
    		case "Epicerie salée":
    			epiceriesalee.add(ingredient);
    			break;
    		case "Epicerie sucrée":
    			epiceriesucree.add(ingredient);
    			break;
    		case "Epices":
    			epices.add(ingredient);
    			break;
    		}
    	}
    	status.add(epiceriesalee);
    	status.add(epiceriesucree);
    	status.add(liquide);
    	status.add(proteineanimale);
    	status.add(fruits);
    	status.add(legumes);
    	return status;
    }
    
    
    
    public static void main(String[] args) throws Exception{
    	//Initialiser la garde à manger
        ArrayList<IngredientGM> ingredients = Initialisateur.initProduit();
        GardeManger gardemanger = new GardeManger(ingredients);
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Vuegardemanger(gardemanger);
			}
		});
    }
    
    
}
