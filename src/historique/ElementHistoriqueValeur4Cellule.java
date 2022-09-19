package historique;

import grille.elements.Cellule;

public class ElementHistoriqueValeur4Cellule implements ElementHistorique {

    Cellule cell;
    
    public ElementHistoriqueValeur4Cellule(Cellule cell) {
	this.cell=cell;
    }
    public void clean() {
	cell.removeValeur();
    }


}
