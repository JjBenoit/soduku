package grille.elements;

import grille.Grille;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * Portions de zone
 * utiles aux calcul des probailités des cellules d'une meme ligne par exemple, mais de portion différente (contenues dans un autre carré) 
 * Une valeur ne peut etre placée dans des portions voisines si toutes les cellules d'un carré sont remplies à l'exception de ceux de la portion 
 */
public class PortionDroite {

    // ligne , ou colonne qui englobe la portion
    protected Droite zoneMere;
    private Set<PortionDroite> portionsVoisines = new HashSet();
    protected Zone carre;
    protected BitSet valeursReservees = new BitSet();

    
    public PortionDroite(Droite zoneMere) {
	this.zoneMere=zoneMere;
	//new Thread(this).start();
    }
    
    
    public Droite getZoneMere() {
        return zoneMere;
    }
   
   
    public BitSet getValeursReservess() {
        return valeursReservees;
    }


    public Zone getCarre() {
        return carre;
    }


    public void setCarre(Zone carre) {
        this.carre = carre;
    }


    public Set<PortionDroite> getPortionsVoisines() {
        return portionsVoisines;
    }


    public void setPortionsVoisines(Set<PortionDroite> portionsVoisines) {
        this.portionsVoisines = portionsVoisines;
    }



}
