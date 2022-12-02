package src.Cuisine.Poubelle_it2;

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;

 public class test {
 final private JFrame fenetrePrincipale = new JFrame("Saisie plein");
 
 final private JTextField odometre = new JTextField(10);
 final private JTextField prixAuLitre = new JTextField(10);
 final private JTextField prix = new JTextField(10);
final private JButton annuler = new JButton("Annuler");
 final private JButton enregistrer = new JButton("Enregistrer");

public test() {
 this.initComponents();
 this.fenetrePrincipale.pack();
 this.fenetrePrincipale.setVisible(true);
 }
private void initComponents() {

JPanel p = new JPanel();
 p.add(new JLabel("Odomètre : "));

 p.add(this.odometre);
 p.add(new JLabel("Prix plein : "));
 p.add(this.prix);
 p.add(new JLabel("Prix au litre : "));
 p.add(this.prixAuLitre);
 p.add(this.annuler);
 p.add(this.enregistrer);
 Container contenu=this.fenetrePrincipale.getContentPane();
 contenu.setLayout(new BorderLayout());
contenu.add(p);
 }
 public static void main(String[] args) {
 EventQueue.invokeLater(new Runnable() {
 public void run() {
 new test();
 }
 });
}
 }
