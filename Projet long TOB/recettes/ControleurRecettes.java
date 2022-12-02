package src.recettes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe qui crée le controleur d'une recette
 *
 * @author Hugo Dupuis
 * @version 18/04/22
 * */
public class ControleurRecettes extends JPanel {

	public ControleurRecettes(Recette recette, VueRecette vue) {
		
		super(new BorderLayout());
		
		final JButton boutonAugmenterNbPersonnes = new JButton(new ImageIcon(this.getClass().getResource("plus.png")));
		final JButton boutonDiminuerNbPersonnes = new JButton(new ImageIcon(this.getClass().getResource("moins.png")));
		
		boutonAugmenterNbPersonnes.setBackground(new Color(242, 203, 241));
		boutonDiminuerNbPersonnes.setBackground(new Color(242, 203, 241));
		this.add(boutonAugmenterNbPersonnes, BorderLayout.NORTH);
		this.add(boutonDiminuerNbPersonnes, BorderLayout.SOUTH);
		boutonAugmenterNbPersonnes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				recette.modifierNbPersonnes(recette.getNbPersonnes() + 1);
				vue.mettreAJourPersonnes();
				vue.mettreAJourIngredients();
			}
		});
		
		boutonDiminuerNbPersonnes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (recette.getNbPersonnes() > 1) {
					recette.modifierNbPersonnes(recette.getNbPersonnes() - 1);
					vue.mettreAJourPersonnes();
					vue.mettreAJourIngredients();
				}
			}
		});
	}

}
