package historique;

import java.util.List;

import grille.elements.Cellule;
import grille.elements.PortionDroite;
import grille.elements.Zone;

public class ElementHistoriqueValeurResa implements ElementHistorique{
  
    List<Integer> valeurs;
    List<Cellule> cellules;
    Zone zone;
    
    public ElementHistoriqueValeurResa(List<Integer> valeurs,List<Cellule> cellules, Zone zone) {
	this.valeurs=valeurs;
	this.cellules=cellules;
	this.zone=zone;
    }

    public void clean() {
	
	//Suppression des valeurs fictivement possées
	for (int i = 0; i < valeurs.size(); i++) {
	    zone.getValeursPosees().set(valeurs.get(i),false);
	}
	//Suppression des valeurs reservées
	for (int i = 0; i < cellules.size(); i++) {
	    Cellule c =cellules.get(i);
	    for (int j = 0; j < valeurs.size(); j++) {
		  c.getValeursReservees().set(valeurs.get(j),false);
	    }
	}
	
    }
}
