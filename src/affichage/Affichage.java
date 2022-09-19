package affichage;
import grille.Grille;
import grille.elements.Cellule;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import jeu.Jeu;


public class Affichage extends JFrame {

    SudoTableModel model;
    JTable table;
    
    
    public static void main(String[] args) {

	Jeu jeu =Jeu.jdd() ;

	new Affichage(jeu.grille);
    }
    
    public Affichage(Grille grille) {
	
	model = new SudoTableModel(grille);
	table = new JTable(model);
	table.setRowHeight(40);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	table.setDefaultRenderer(Cellule.class, new MonAfficheurCellule(Grille.taille1CoteCarre));
	table.setShowGrid(false);
	
	
	
	add(table);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(500, 500);
	setVisible(true);
    }
}
