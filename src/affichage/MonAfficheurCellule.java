package affichage;

import grille.Grille;
import grille.elements.Cellule;

import java.awt.Color;
import java.awt.Component;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class MonAfficheurCellule implements TableCellRenderer{

    JTextArea l = new JTextArea();
    int taille;
    
    public MonAfficheurCellule(int taille) {
	this.taille=taille;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
	    boolean isSelected, boolean hasFocus, int row, int column) {
	
	
	
	Cellule c = (Cellule) value;
	int bas=1;
	int left=1;
	if((row+1)%taille==0)
	    bas=3;
	 
	if((column)%taille==0)
	    left=3;
	    
	
	l.setBorder(BorderFactory.createMatteBorder(1, left, bas, 1,Color.BLACK));
	l.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        l.setAlignmentY(JLabel.CENTER_ALIGNMENT);
	if(c.getValeur().compareTo(Cellule.CELLULE_VIDE)!=0)
	{
	
	    
	    l.setOpaque(true);
	    l.setForeground(Color.gray);
            l.setText(""+c.getValeur()); 
	}
        else 
        {
            l.setEditable(true);
            l.setOpaque(false);
            l.setForeground(Color.blue);
         
           
                   
            Iterator<Integer> it = c.getValeursPossibles().iterator();
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
 
            l.setText(""+msg.toString()); 
        }


        return l;
        
    }

}
