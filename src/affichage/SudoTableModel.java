package affichage;

import grille.Grille;
import grille.elements.Cellule;


import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SudoTableModel implements TableModel{

    Cellule [][] model ;
    boolean [][] editablesCell ;
    int sizeOf1cote;
    
    
    public SudoTableModel(Grille grille) {
	model=grille.getTheGrille();
	sizeOf1cote=Grille.taille1CoteCarre*Grille.taille1CoteCarre;
	editablesCell = new  boolean [sizeOf1cote][sizeOf1cote];
		
	for (int i = 0; i < model.length; i++) {
	    for (int j = 0; j < model[i].length; j++) {
		if(model[i][j].getValeur().compareTo(Cellule.CELLULE_VIDE)==0)
		    editablesCell[i][j]= true;
	    }
	}
	
    }
   
    
    public void addTableModelListener(TableModelListener l) {
	// TODO Auto-generated method stub
	
    }

    public Class<?> getColumnClass(int columnIndex) {
	// TODO Auto-generated method stub
	return Cellule.class;
    }

    public int getColumnCount() {
	// TODO Auto-generated method stub
	return sizeOf1cote;
    }

    public String getColumnName(int columnIndex) {
	// TODO Auto-generated method stub
	return null;
    }

    public int getRowCount() {
	// TODO Auto-generated method stub
	return sizeOf1cote;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

	return model[rowIndex][columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
	// TODO Auto-generated method stub
	return editablesCell[rowIndex][columnIndex];
    }
    public void removeTableModelListener(TableModelListener l) {
	// TODO Auto-generated method stub
	
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {
	model[rowIndex][columnIndex].tryValeur((Integer)value);
	
    }

}
