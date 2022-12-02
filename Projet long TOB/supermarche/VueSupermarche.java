package src.supermarche;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import src.base_de_donnees.Utilisateur;
import src.liste_courses.IngredientCourses;
import src.liste_courses.ListeCourses;
import src.liste_courses.VueListeCourses;

public class VueSupermarche {
	/** Model de la garde à manger*/
    public Supermarche supermarche;
	/** Fenêtre principale */
    private JFrame fenetre;
    /** Contenu de la fenetre*/
    private Container contenu;

    public VueSupermarche(Supermarche supermarche) {
    	ListeCourses listetest = new ListeCourses(new ArrayList<IngredientCourses>(),LocalDate.now());
		IngredientCourses pates = new IngredientCourses("pâtes", "épicerie", 3, "", 1.0);
		IngredientCourses poulet = new IngredientCourses("poulet", "viande", 1, "", 3.5);
		listetest.ajouterIngredient(pates);
		listetest.ajouterIngredient(poulet);
		new VueListeCourses(Utilisateur.utilisateur.getListeCourses(), new ListeCourses(new ArrayList<IngredientCourses>(),LocalDate.now()), Utilisateur.utilisateur.getGardeManger());   	
    	this.supermarche=supermarche;
    	this.fenetre = new JFrame("SUPER-MARCHE");
    	contenu = this.fenetre.getContentPane();
    	contenu.setLayout(new BorderLayout()); 
    	contenu.add(northbar1(),BorderLayout.NORTH);
    	contenu.add(centerbar(supermarche.getProduitSupermarche(), supermarche.getListecourse()),BorderLayout.SOUTH);
    	
    	
    	this.fenetre.setLocation(600, 400); //localiser la fenêtre
    	this.fenetre.pack();			// redimensionner la fenêtre
		this.fenetre.setVisible(true);	// l'afficher
		
		// Construire le contrôleur (gestion des événements)
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    }
 
    public JPanel northbar1() {
    	JPanel status = new JPanel(new FlowLayout());
    	JButton menu = new JButton("Retour Menu Principal");
    	status.add(menu);
    	menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fenetre.dispose();
			}
    	});
    	return status;
    }
    
    public JPanel centerbar(List<ProduitSupermarche> produits,ListeCourses listecourse) {
    	JPanel status = new JPanel(new GridLayout(produits.size(),1));
    	for (ProduitSupermarche produit : produits) {
    		int i=0;
    		JPanel status1 = new JPanel(new GridLayout(1,5));
    		JLabel nomprd = new JLabel(produit.getNom());
    		JLabel prixprd = new JLabel(Double.toString(produit.getPrix()) +"€"+ " /" +produit.getUnite());
    		JButton plus = new JButton("+");
    		JButton moin = new JButton("-");
    		JLabel num1 = new JLabel("            "+Integer.toString(i));
    		
           plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub				
				String s = num1.getText();
				Integer j =(int) Double.parseDouble(s)+1;
				num1.setText("            "+j.toString());
				
			}
		});
			
           moin.addActionListener(new ActionListener() {
   			
   			@Override
   			public void actionPerformed(ActionEvent arg0) {
   				// TODO Auto-generated method stub				
   				String s = num1.getText();
   				Integer j =(int) Double.parseDouble(s)-1;
   				num1.setText("            "+j.toString());
   				
   			}
   		});
    		
    		
    		
            
    		status1.add(nomprd);
    		status1.add(prixprd);
    		status1.add(plus);
    		status1.add(num1);
    		status1.add(moin);

    		status.add(status1);
    		
    	}
    	
    	return status;
    }
    
    
    public static void main(String[] args) throws Exception{
    	//Initialiser le Supermarche
    	ProduitSupermarche spaghetti = new ProduitSupermarche("Spaghetti", "Epicerie salée",2,"kg");
    	ProduitSupermarche boeufHache = new ProduitSupermarche("Viande hachée", "Protéine animale",1,"kg");
    	ProduitSupermarche huile = new ProduitSupermarche("Huile d'olive", "Epicerie salée",5,"kg");
    	ProduitSupermarche tomate = new ProduitSupermarche("Tomates", "Fruits",2,"kg");
    	ProduitSupermarche sucre = new ProduitSupermarche("Sucre en poudre", "Epicerie sucrée",2,"kg");
    	ProduitSupermarche ail = new ProduitSupermarche("Ail", "Légumes",2,"kg");
    	ProduitSupermarche oignon = new ProduitSupermarche("Oignon", "Légumes",11,"kg");
    	ProduitSupermarche thym = new ProduitSupermarche("Thym", "Légumes",2,"kg");
    	ProduitSupermarche parmesan = new ProduitSupermarche("Parmesan", "Protéine animale",4,"kg");
    	
    	ProduitSupermarche patePizza = new ProduitSupermarche("Pâte à pizza", "Epicerie salée",19,"kg");
    	ProduitSupermarche cheddar = new ProduitSupermarche("Cheddar", "Protéine animale",9,"kg");
    	ProduitSupermarche comte = new ProduitSupermarche("Comté", "Protéine animale",2,"kg");
    	ProduitSupermarche bleu = new ProduitSupermarche("Bleu", "Protéine animale",8,"kg");
    	ProduitSupermarche mozzarella = new ProduitSupermarche("Mozzarella", "Protéine animale",5,"kg");
    	ProduitSupermarche basilic = new ProduitSupermarche("Basilic", "Légumes",2,"kg");
    	List<ProduitSupermarche> l = new ArrayList<>();
    	l.add(spaghetti); l.add(boeufHache); l.add(huile); l.add(tomate); l.add(sucre); l.add(ail); l.add(oignon); l.add(thym); l.add(parmesan);	
		l.add(patePizza); l.add(cheddar); l.add(comte); l.add(bleu); l.add(mozzarella); l.add(basilic);
		
		ListeCourses listetest = new ListeCourses(new ArrayList<IngredientCourses>(),LocalDate.now());
		IngredientCourses pates = new IngredientCourses("pâtes", "épicerie", 3, "", 1.0);
		IngredientCourses poulet = new IngredientCourses("poulet", "viande", 1, "", 3.5);
		listetest.ajouterIngredient(pates);
		listetest.ajouterIngredient(poulet);
		
    	Supermarche carrefour = new Supermarche("Carrefour", l,listetest);
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				new VueSupermarche(carrefour);
			}
		});
    }
    
}
    
