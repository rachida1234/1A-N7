package src.classes_communes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import src.base_de_donnees.Utilisateur;

/** Crée une vue qui demande le nom de l'utilisateur
 * @author hdupuis
 * @version 17/05
 */
public class VueNom {
	
	private JFrame fenetre;
	
	public VueNom() {
		
		this.fenetre = new JFrame("Nom");
		Container contenu = this.fenetre.getContentPane();
		contenu.setLayout(new BorderLayout());
		
		JButton bQuitter = new JButton("Quitter");
		bQuitter.setIcon(new ImageIcon(this.getClass().getResource("quitter.png")));
		
		JPanel demande = new JPanel(new FlowLayout());
		JTextArea demandenom = new JTextArea("Entrer votre nom :                 ");
		JTextField texte = new JTextField(20); 
		JTextArea demandePassword = new JTextArea("Entrer votre mot de passe :  ");
		JTextField Password = new JTextField(20);
		JButton bOk = new JButton("Se connecter");
		bOk.setIcon(new ImageIcon(this.getClass().getResource("user.png")));
		 
		demande.add(demandenom);
		demande.add(texte);
		demande.add(demandePassword);
		demande.add(Password);
		demande.add(bOk);
		contenu.add(demande);
		contenu.add(bQuitter, BorderLayout.SOUTH);
		this.fenetre.setLocation(100, 200);
		this.fenetre.setSize(new Dimension(700,400));
		
		// afficher la fenêtre
		this.fenetre.setSize(500,200);			// redimensionner la fenêtre
		this.fenetre.setVisible(true);	// l'afficher
		// Construire le contrôleur (gestion des événements)
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (texte.getText().length() != 0) {
					Utilisateur.utilisateur = new Utilisateur(texte.getText());
					new MenuPrincipal(Utilisateur.utilisateur);
					fenetre.dispose();
				}
				
			}
		});
		
		bQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fenetre.dispose();
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new VueNom();
			}
		});
	}

}
