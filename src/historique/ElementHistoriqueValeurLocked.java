package historique;

import grille.elements.PortionDroite;

public class ElementHistoriqueValeurLocked implements ElementHistorique{
  
    Integer valeur;
    PortionDroite portionDroite;
    
    public ElementHistoriqueValeurLocked(Integer valeur, PortionDroite portionDroite) {
	this.valeur=valeur;
	this.portionDroite=portionDroite;
    }

    public void clean() {
	portionDroite.getValeursReservess().set(valeur,false);
    }
}
