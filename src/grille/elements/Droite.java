package grille.elements;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Ligne ou colonne
public class Droite extends Zone{
	
	public static final String LIGNE="Ligne";
	public static final String COLONNE="Colonne";
    	
	private Set<PortionDroite> portionsLigne = new HashSet();
	
	
	private String type;
	
	

	public Droite(int tailleValeurs,String id,String type)
	{
		super(tailleValeurs, id);
		this.type=type;
	}



	public Set<PortionDroite> getPortionsDroite() {
	    return portionsLigne;
	}




}
