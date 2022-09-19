package affichage.copy;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import grille.Grille;
import grille.elements.Cellule;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;



import jeu.InitialisationGrille;
import jeu.Jeu;


public class Affichage extends JFrame {

     //http://prevert.upmf-grenoble.fr/Prog/Java/swing/JTextPane.html
    Grille grille;
    JTextPane [][] cellulesAffichage ;
    JPanel panel;
    static final Dimension dimCellule =  new Dimension(40,40);
    static final Font grosseFonte = new Font("",Font.ROMAN_BASELINE,25);
    
    public Affichage(Grille grille)
    {
	this.grille=grille;
	
	initGrille();
	add(panel);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(500, 500);
	setVisible(true);
    }
    public void initGrille()
    {
	int cote =Grille.taille1CoteCarre*Grille.taille1CoteCarre;
	cellulesAffichage = new JTextPane [cote][cote];
	panel = new JPanel();
	
	for (int i = 0; i < cellulesAffichage.length; i++){
	    for (int j = 0; j < cellulesAffichage[i].length; j++) {
		
		cellulesAffichage[i][j]=new JTextPane();
		cellulesAffichage[i][j].setPreferredSize(dimCellule);
		GridLayout gl = new GridLayout((cote),(cote),0,0);
		panel.add(cellulesAffichage[i][j]);
		panel.setLayout(gl);
		
		
		
		int bas=1;int left=1;
		if((i+1)%Grille.taille1CoteCarre==0)
		    bas=3;
		 
		if((j)%Grille.taille1CoteCarre==0)
		    left=3;
		
		cellulesAffichage[i][j].setBorder(BorderFactory.createMatteBorder(1, left, bas, 1,Color.BLACK));
		
		if(grille.getTheGrille()[i][j].getValeur().compareTo(Cellule.CELLULE_VIDE)!=0)
		{
		    cellulesAffichage[i][j].setText(Integer.toString(grille.getTheGrille()[i][j].getValeur()));
		    cellulesAffichage[i][j].setEditable(false);
		    cellulesAffichage[i][j].setBackground(Color.LIGHT_GRAY);
		    cellulesAffichage[i][j].setFont(grosseFonte);
		      /*
		    StyledDocument doc = cellulesAffichage[i][j].getStyledDocument();
		    MutableAttributeSet standard = new SimpleAttributeSet();
		    StyleConstants.setAlignment(standard, StyleConstants.ALIGN_CENTER);
		    doc.setParagraphAttributes(0, 0, standard, true);*/


		}
		else
		{
		    String s = setToString(grille.getTheGrille()[i][j].getValeursPossibles(),Grille.taille1CoteCarre);
		    cellulesAffichage[i][j].setText(s);
		    
		}
		
	    }
	}

    }
    public String setToString (Set s,int taille)
    {
	Iterator<Integer> it = s.iterator();
	StringBuffer msg = new StringBuffer();
	int index =1;
		
	while (it.hasNext()) {
		    
	    Integer type =  it.next();
	    msg.append(type);
		    
	    if(index%taille==0)
		msg.append("\n");
	    else
		msg.append(" ");
	    index++;
	}
	return msg.toString();
    }
    
    
    public static void main(String[] args) {

	Jeu jeu =Jeu.jdd() ;
	new Affichage(jeu.grille);
    }
}
