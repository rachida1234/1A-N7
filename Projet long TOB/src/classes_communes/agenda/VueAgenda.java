package src.agenda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import src.base_de_donnees.Utilisateur;
import src.classes_communes.MenuPrincipal;
import src.recettes.Recette;
import src.recettes.VueRecette;

public class VueAgenda {
	
	private LocalDate date = LocalDate.now();
	private JPanel calendrier = new JPanel(new GridBagLayout());
	private GridBagConstraints c = new GridBagConstraints();
	
	public VueAgenda(JPanel contenu, Agenda agenda) {
		
		
		JTextArea mois = new JTextArea(getMois(date.getMonthValue()));
		mois.setAlignmentX(SwingConstants.CENTER);
		contenu.add(mois, BorderLayout.NORTH);
		
		c.weightx = 1.0;
		calendrier.add(new JTextArea(""), 0);
		for (int i = 0; i < 7; i++) {
			LocalDate newDate = date.plusDays(i);
			JTextArea jour = new JTextArea(newDate.getDayOfMonth() + "");
			if (i == 6) {
				c.gridwidth = GridBagConstraints.REMAINDER;
			}
			calendrier.add(jour, c);
			calendrier.getComponent(i+1).setBackground(Color.CYAN);
			calendrier.getComponent(i+1).setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		}
		c.weightx = 0;
		c.gridwidth = 1;
		calendrier.add(new JTextArea("Repas du midi"), c);
		
		for (int j = 0; j < 7; j++) {
			LocalDate newDate = date.plusDays(j);
			JTextArea texteMidi;
			if (agenda.getListeRepas().get(newDate) == null) {
				texteMidi = new JTextArea("");
			}
			else {
				Recette recetteMidi = agenda.getListeRepas().get(newDate).getRecetteMidi();
				texteMidi = new JTextArea(recetteMidi.getNom());
			}
			c.weighty = 3.0;
			if (j == 6) {
				c.gridwidth = GridBagConstraints.REMAINDER;
			}
			calendrier.add(texteMidi, c);
			calendrier.getComponent(j+9).setBackground(Color.RED);
		}
		c.weightx = 0;
		c.gridwidth = 1;
		calendrier.add(new JTextArea("Repas du soir"), c);
		
		for (int k = 0; k < 7; k++) {
			LocalDate newDate = date.plusDays(k);
			JTextArea texteSoir;
			if (agenda.getListeRepas().get(newDate) == null) {
				texteSoir = new JTextArea("");
			}
			else {
				Recette recetteSoir = agenda.getListeRepas().get(newDate).getRecetteSoir();
				texteSoir = new JTextArea(recetteSoir.getNom());
			} 
			c.weighty = 3.0;
			calendrier.add(texteSoir, c);
			calendrier.getComponent(k+17).setBackground(Color.GREEN);
		}
		
		majAgenda(Utilisateur.utilisateur);
		contenu.add(calendrier, BorderLayout.CENTER);
		
		
	}
	
	public void majAgenda(Utilisateur user) {
		this.calendrier = new JPanel(new GridBagLayout());
		for (int j = 0; j < 7; j++) {
			LocalDate newDate = date.plusDays(j);
			JTextArea texteMidi;
			try {
				Recette recetteMidi = user.getAgenda().getListeRepas().get(newDate).getRecetteMidi();
				texteMidi = new JTextArea(recetteMidi.getNom());
			} catch (NullPointerException e) {
				texteMidi = new JTextArea("");
			}
			c.weighty = 3.0;
			if (j == 6) {
				c.gridwidth = GridBagConstraints.REMAINDER;
			}
			calendrier.add(texteMidi, c);
			calendrier.getComponent(j+9).setBackground(Color.RED);
		}
		c.weightx = 0;
		c.gridwidth = 1;
		calendrier.add(new JTextArea("Repas du soir"), c);
		
		for (int k = 0; k < 7; k++) {
			LocalDate newDate = date.plusDays(k);
			JTextArea texteSoir;
			try {
				Recette recetteSoir = user.getAgenda().getListeRepas().get(newDate).getRecetteSoir();
				texteSoir = new JTextArea(recetteSoir.getNom());
			} catch (NullPointerException f) {
				texteSoir = new JTextArea("");
			}
			c.weighty = 3.0;
			calendrier.add(texteSoir, c);
			calendrier.getComponent(k+17).setBackground(Color.GREEN);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recette spaghBolo = new Recette("Spaghetti bolognaise", new ArrayList<>(), 20, 15, 1, null);
		Recette pizza = new Recette("Pizza 4 fromages", new ArrayList<>(), 45, 20, 2, null);
		Agenda agenda = new Agenda(new HashMap<>());
		RepasDuJour repas1 = new RepasDuJour(spaghBolo, pizza);
		agenda.ajouter(repas1, LocalDate.now());
		RepasDuJour repas2 = new RepasDuJour(pizza, spaghBolo);
		agenda.ajouter(repas2, LocalDate.now().plusDays(1));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new VueAgenda(new JPanel(new BorderLayout()), agenda);
			}
		});
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

}
