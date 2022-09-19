package grille.elements;
 
import grille.Grille;
 
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Cellule {
	
public static final Integer CELLULE_VIDE=0 ;

private Grille grille;
private Integer valeur=0;
private Zone carre ;
private Droite ligne ;
private Droite colonne ;
//Portion de la ligne qui traverse le carré : optim -> calculs des proba
private PortionDroite portionLigne ;
//Portion de la colonne qui traverse le carré : optim -> calculs des proba
private PortionDroite portionColonne ;
//Cellules dumeme carré, meme ligne, meme colonne : optim 
private List<Cellule> cellulesLiees ;
//Paire, triples
private BitSet valeursReservees;

private BitSet bitSet = new BitSet();

private String id;


	public Cellule(String id,Grille grille)
	{
		this.id=id;
		this.grille=grille;
	 
	}

	public Integer getValeur() {
		return valeur;
	}

	public boolean tryValeur(Integer valeur) {
		
				
		// si la cellule est vide
		if(getValeur().compareTo(CELLULE_VIDE)==0)
		{
        		if(!getLigne().getValeursPosees().get(valeur))
        		{
        			if(!getColonne().getValeursPosees().get(valeur))
        			{
        				if (!getCarre().getValeursPosees().get(valeur))
        				{
        				    
        						this.valeur=valeur;
        						getLigne().getValeursPosees().set(valeur,true);
        						getColonne().getValeursPosees().set(valeur,true);
        						getCarre().getValeursPosees().set(valeur,true);
        						
        						getLigne().getCellulesRestantes().remove(this);
        						getColonne().getCellulesRestantes().remove(this);
        						getCarre().getCellulesRestantes().remove(this);
        						grille.getCellulesRestantes().remove(this);
        		       						       						
        						
        						//grille.afficher();	
        						return true;
        						
               					
        				}
        			}
             		}
		}
		return false;
	}
	public void removeValeur() {
		
		// n'est pas une cellule vide
		if(valeur.compareTo(CELLULE_VIDE)!=0)
		{
			getLigne().getValeursPosees().set(valeur, false);
			getColonne().getValeursPosees().set(valeur, false);
			getCarre().getValeursPosees().set(valeur, false);
						
			getLigne().getCellulesRestantes().add(this);
			getColonne().getCellulesRestantes().add(this);
			getCarre().getCellulesRestantes().add(this);
			grille.getCellulesRestantes().add(this);
					
			valeur=CELLULE_VIDE;
					
			//grille.afficher();	
		}
	}
	
	 

	public void forceValeur(Integer i)
	{
		valeur=i;
	}
	
	public Set getValeursPossibles()
	{
	   Set<Integer> valPoss = new HashSet();
	   BitSet bitSet =OR(valPoss);
	 
	 
	   Iterator<PortionDroite> it = portionColonne.getPortionsVoisines().iterator();
	   while (it.hasNext()) {
	       PortionDroite p =it.next();
	       if(!p.getValeursReservess().isEmpty())
	       {
		  // grille.afficher();
		   bitSet.or(p.getValeursReservess());
	       }
	   }
	    
	   it = portionLigne.getPortionsVoisines().iterator();
	     while (it.hasNext()) {
		 PortionDroite p =it.next();
		 if(!p.getValeursReservess().isEmpty())
		 {
			   bitSet.or(p.getValeursReservess());
		 }
	    }
	     
	    del(valPoss,bitSet);
	   return valPoss;
	}
	/*
	 * Operation logique OR. Si une des deux valeurs est a vrai. Alors le résultat est vrai 
	 * Utile pour garder la trace des valeurs deja posées. (valeur à true ) 
	 */
	public BitSet OR(Set valPoss)
	{
		//BitSet bitSet =(BitSet) getLigne().getValeursPosees().clone() ;
	    	bitSet.or(getLigne().getValeursPosees());
	    	bitSet.or(getColonne().getValeursPosees());
		bitSet.or(getCarre().getValeursPosees());
		return bitSet;
	}
	public void del(Set valPoss,BitSet bitSet)
	{
		int max =(Grille.nbTotalValPoss+1);
		for (int i = 1; i < max; i++) {
			
			if(!bitSet.get(i))
			{
			    valPoss.add(i);
			}
		}
		
	}
	

	public  void afficher()
	{
	    System.out.print(" | Cellule "+getId());
	}
	
	public Zone getCarre() {
		return carre;
	}

	public void setCarre(Zone carre) {
		this.carre = carre;
	}

	public Droite getLigne() {
		return ligne;
	}

	public void setLigne(Droite ligne) {
		this.ligne = ligne;
	}

	public Droite getColonne() {
		return colonne;
	}

	public void setColonne(Droite colonne) {
		this.colonne = colonne;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Cellule> getCellulesLiees() {
		return cellulesLiees;
	}

	public void setCellulesLiees(List<Cellule> cellulesLiees) {
		this.cellulesLiees = cellulesLiees;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+" "+valeur;
	}
	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public PortionDroite getPortionLigne() {
	    return portionLigne;
	}

	public void setPortionLigne(PortionDroite portionLigne) {
	    this.portionLigne = portionLigne;
	}

	public PortionDroite getPortionColonne() {
	    return portionColonne;
	}

	public void setPortionColonne(PortionDroite portionColonne) {
	    this.portionColonne = portionColonne;
	}

	public BitSet getValeursReservees() {
	    return valeursReservees;
	}

	
}
