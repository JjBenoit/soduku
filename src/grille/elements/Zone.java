package grille.elements;


import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Zone {

	
	protected BitSet valeursPosees;
	protected List cellules = new LinkedList<Cellule>(); 
	protected Set<Cellule> cellulesRestantes = new HashSet(); 
	protected String id;
	// somme de toutes les valeurs deja posées
	

	public Zone(int tailleValeurs,String id)
	{
		this.id=id;
		valeursPosees = new BitSet(); // 8octest d'un coup 64 bits
		//System.out.println(valeursPosees.size());
	}

	public BitSet getValeursPosees() {
		return valeursPosees;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Cellule> getCellules() {
		return cellules;
	}

	public void addCellule(Cellule cellule) {
		this.cellules.add(cellule);
	}

	public Set<Cellule> getCellulesRestantes() {
		return cellulesRestantes;
	}


	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+id;
	}
	
}
