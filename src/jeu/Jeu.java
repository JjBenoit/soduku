package jeu;
import exceptions.CulDeSacException;
import grille.Grille;
import grille.elements.Zone;
import grille.elements.Cellule;
import grille.elements.Droite;
import grille.elements.PortionDroite;
import grille.elements.Zone;
import historique.ElementHistoriqueValeur4Cellule;
import historique.ElementHistoriqueValeurLocked;
import historique.ElementHistoriqueValeurResa;
import historique.HistoriqueList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;





public class Jeu {

	 
	public Grille grille;
	int retourArr=0;
		
	
	Cellule celluleMoinHypo=null;
	//Set valPossibleOfcelluleMoinHypo=null;
	int maxValValeur4CelluleMoinHypo=-1;
	int maxTotalValValeur4CelluleMoinHypo=0;
	public Jeu(Grille grille) {
		
		this.grille=grille;
		maxTotalValValeur4CelluleMoinHypo=(grille.getNbTotalValPoss()+1);
	}
	
	
	public static void main(String[] args) {
	
	    int ratioCellulesAAffcicher =3; //(1=100%,2=50%, 3=33% ....)
	    int grillesresolues=0;
	    long total=0;
	    boolean continuer =true;
		
	    /* ... */
		while(continuer)
		{
	
		    Jeu jeu =Jeu.jdd() ;
		    //jeu.grille.afficher();
		   // InitialisationGrille.supressRandomValues((jeu.grille.getNbTotalCellules()/ratioCellulesAAffcicher),jeu.grille);
			
		 //   System.out.println("----------------------> DEBUT JEU");
		    long deb =System.currentTimeMillis();
		    jeu.jouer();
		    long fin =System.currentTimeMillis();
		    continuer=jeu.grille.validerGrille();	
		    total+=(fin-deb);
		    grillesresolues++;
        		
		    System.out.println("grillesresolues: "+grillesresolues);
		    System.out.println("tmps total: "+total+" ms");
		    System.out.println("tps moyen par grille : "+total/grillesresolues+" ms");
		  //  jeu.grille.afficher();
		    
		}
		

		/* ....  
	    
	    while(continuer)
		{
	
    		int dim =3;
    		Grille grille=InitialisationGrille.genererGrille(dim,dim);
    		InitialisationGrille.supressRandomValues((grille.getNbTotalCellules()/ratioCellulesAAffcicher),grille);
    		Jeu jeu =new Jeu(grille);
    		
    		long deb =System.currentTimeMillis();
    		
    		continuer=jeu.jouer();
    		   
    		
    		long fin =System.currentTimeMillis();
    		
    		total+=(fin-deb);
    		grillesresolues++;
    		
    		
    		System.out.println("grille resolue en: "+(fin-deb)+" ms");
    		System.out.println("nbr total grilles resolues: "+grillesresolues);
    		System.out.println("tmps total ecoulé: "+total+" ms");
    		System.out.println("tps moyen par grille : "+total/grillesresolues+" ms");
		    	
		}
	    	*/
	  
	    	
	}
	public static Jeu jdd() {
		
		int dim =3;
		Jeu jeu =new Jeu(new Grille(dim,dim));
		
		Integer [] jdd = {
				
			/* 
			6,15,4,11,5,16,7,2,12,3,9,1,8,13,14,10,
			3,8,16,7,9,15,11,14,13,4,10,5,12,2,6,1,
			12,2,13,9,4,8,1,10,15,11,6,14,5,16,3,7,
			10,1,14,5,3,12,13,6,8,16,7,2,4,15,11,9,
			15,4,8,16,11,3,12,7,2,13,1,9,6,14,10,5,
			11,3,7,12,15,4,8,16,6,14,5,10,2,9,1,13,
			2,13,9,6,1,14,10,5,4,15,8,11,16,3,7,12,
			1,14,5,10,2,13,6,9,16,7,3,12,15,4,8,11,
			4,11,15,8,16,7,3,12,9,2,13,6,1,10,5,14,
			16,7,3,2,8,11,15,1,10,5,14,4,13,12,9,6,
			13,12,6,14,10,9,5,4,11,1,15,8,3,7,16,2,
			9,5,10,1,6,2,14,13,3,12,16,7,11,8,15,4,
			8,16,11,4,7,6,9,15,1,10,12,13,14,5,2,3,
			7,9,12,15,13,5,16,11,14,6,2,3,10,1,4,8,
			14,6,1,3,12,10,2,8,5,9,4,15,7,11,13,16,
			5,10,2,13,14,1,4,3,7,8,11,16,9,6,12,15,*/
			
			
			/*
			0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,3,0,8,5,
			0,0,1,0,2,0,0,0,0,
			0,0,0,5,0,7,0,0,0,
			0,0,4,0,0,0,1,0,0,
			0,9,0,0,0,0,0,0,0,
			5,0,0,0,0,0,0,7,3,
			0,0,2,0,1,0,0,0,0,
			0,0,0,0,4,0,0,0,9, */
			
			
			
			0,4,0,8,0,0,0,5,0,
			2,0,0,0,0,0,9,0,0,
			0,0,0,6,0,0,0,0,0,
			0,0,0,5,0,0,0,8,0,
			7,0,0,0,1,0,0,0,0,
			0,0,0,0,0,0,0,4,0,
			0,8,3,0,0,0,0,0,0,
			0,0,0,0,9,0,1,0,0,
			0,0,0,0,0,0,7,0,2, /* */
			
			
				};
		
		
		int index=0;
		Cellule[][] grille = jeu.grille.getTheGrille();
		for (int ligne =0; ligne < dim*dim; ligne++) 
		{
			for (int col=0; col < dim*dim; col++)
			{
			    if(jdd[index].compareTo(Cellule.CELLULE_VIDE)!=0)
				grille[ligne][col].tryValeur(jdd[index]);
			    index++;
			}
		}
		
		return jeu;

	}
	
	
	

	public  void jouer()
	{
		
		retourArr=0;
		//long deb =System.currentTimeMillis();
		parcourirTabCellulesJeux(); 
		//long fin =System.currentTimeMillis();
		/*grille.afficher();
		
		System.out.println("retourArr :"+retourArr);
		System.out.println("Grille résolue en  :"+(fin-deb)+" mils");
		System.out.println("Grille valide :"+grille.validerGrille());*/
		
	}
	

	
	
	int nombreIncrementParcourirTabCellulesJeux=0;
	public  boolean parcourirTabCellulesJeux()
	{
		Cellule cellule=null;
		nombreIncrementParcourirTabCellulesJeux++;
		boolean succes=true;
		HistoriqueList historiqueTmp = new HistoriqueList();
		
		if(!grille.isGrilleRemplie())
		{
			nombreIncrementParcourirTabCellulesJeux=0;
			try {
				cellule=strategie(historiqueTmp);
			} catch (CulDeSacException e) {
			    historiqueTmp.backToThePastAllElements();
			    return false;
			}
			if(!grille.isGrilleRemplie())
			{
			
			// Test car cellulePlusSureSuivante peut etre null, si il n'y a plus de valeurs possibles dans les cellules restantes 
			if(cellule==null)
			{
			    historiqueTmp.backToThePastAllElements();
			    return false;
			}
			if(cellule.getValeur().compareTo(Cellule.CELLULE_VIDE)!=0)
			{
				System.out.println("ERRRRRRRRRRRRRRRRRRRRRRRRRRRRREUR");
				System.exit(1);
			}
			// ajoute d'abord les valeurs hautement probables
			
		
			Set val = cellule.getValeursPossibles();
			if(val.size()==0)
			{
			    historiqueTmp.backToThePastAllElements();
			    
			    return false;
			}
			
			Iterator it= val.iterator();
			while (it.hasNext())
			{
				
				Integer valeur = (Integer) it.next();
				
				if(cellule.tryValeur(valeur))
				{
					succes=parcourirTabCellulesJeux();
						
					if(!succes)
					{
						cellule.removeValeur();
						historiqueTmp.backToThePastAllElements();
						
					}
					else
					{
						return true;
					}
				}
						
			}
			}
		}
		
			
		
			if(!succes)
			{
				retourArr++;
			}
		
		return succes;
	
			
	}
	public  Cellule strategie(HistoriqueList historiqueTmp) throws CulDeSacException
	{
	
		//long deb =System.currentTimeMillis();
		
				
		while(true)
		{
			/*
			 * RAZ cell moins hypothetique
			 */
			maxValValeur4CelluleMoinHypo=maxTotalValValeur4CelluleMoinHypo;
			celluleMoinHypo=null;
			
			int nbValRemplies=grille.getCellulesRestantes().size();
			
		
			for (int i = 0; i < grille.getCarres().size(); i++) {
			    int ret =heuristiques(new HashSet(grille.getCarres().get(i).getCellulesRestantes()),false,true, historiqueTmp);
			}
			for (int i = 0; i < grille.getColonnes().size(); i++) {
			    int ret =heuristiques(new HashSet(grille.getColonnes().get(i).getCellulesRestantes()),false,false, historiqueTmp);
			}
			if(grille.getCellulesRestantes().size()!=nbValRemplies)
			{
			    for (int i = 0; i < grille.getLignes().size(); i++) {
				int ret =heuristiques(new HashSet(grille.getLignes().get(i).getCellulesRestantes()),false,false,historiqueTmp);
			    }
			}
			else
			{
			    for (int i = 0; i < grille.getLignes().size(); i++) {
				int ret =heuristiques(new HashSet(grille.getLignes().get(i).getCellulesRestantes()),true,false,historiqueTmp);
			    }
			}
		
			
			// y a pas eu de changement
			if(grille.getCellulesRestantes().size()==nbValRemplies)
			{
			    System.currentTimeMillis();
			    break;
			}
		}
		
		//System.out.println("Pre Test  :"+(System.currentTimeMillis()-deb)+" mils"+" NBR cell demasquées par : test valeurs croisées: "+nbrCellusDemasquees+",  test UNIK Val: "+nbrCellusDemasqueesUNIKTEST);
				
		//long fin =System.currentTimeMillis();
		//System.out.println("Tmps  :"+(fin-deb));
		//System.console();
	
		return celluleMoinHypo;
	}
	
	
	/*
	 * STRAGETGIES DE REMPLISSAGE 
	 */


	
	/*
	 * Test par élimination des cellules candidates.
	 * Si une valeur ne peut aller dans aucune autres cellules vides que celle testée.
	 * C'est qu'il s'agit de la valeur de la cellule testée
	 * Test mené sur les autres cellules du meme carré,meme ligne ou meme colonne. 
	 */
	private  int  heuristiques(Set<Cellule> cellules,boolean MAJCellMoinsHypo,boolean look4ValeurLocked,HistoriqueList historiqueTmp) throws CulDeSacException
	{
		int nbrCellusDemasquees=0;
		int nbrCellulesLocked=0;
		if(!grille.isGrilleRemplie() && cellules.size()>0)
		{

			//long deb =System.currentTimeMillis();
			Map valeursUniques=getCellsByValeurs(cellules,MAJCellMoinsHypo,historiqueTmp);
			
			Iterator<Integer> it =	valeursUniques.keySet().iterator();
			while (it.hasNext())
			{
				Integer valeur = it.next();
				Set<Cellule> cells = (Set<Cellule>) valeursUniques.get(valeur);
				/*
				 * Cas des valeurs uniques cachées
				 */
				if(cells.size()==1)
				{
				    Cellule c =cells.iterator().next();
				    c.tryValeur(valeur);
				    nbrCellusDemasquees++;
				    historiqueTmp.add(new ElementHistoriqueValeur4Cellule(c));
				}
				/*
				 * Cas des valeurs vérouillées (carré/colonne ou ligne)
				 */
				else if(look4ValeurLocked)
				{
					if(cells.size()>1 && cells.size()<=Grille.taille1CoteCarre)
					{
					    Iterator<Cellule> itCells =cells.iterator();
					    boolean cellulesVoisinesLigne=true;
					    boolean cellulesVoisinesCol=true;
					    PortionDroite li =null;
					    PortionDroite col =null;
					    
					    while (itCells.hasNext())
					    {
						Cellule c =itCells.next();
						
						if(li==null && col==null)
						{
						    li=c.getPortionLigne(); 
						    col=c.getPortionColonne();
						}
						else
						{
						    if(c.getPortionLigne()!=li)
							cellulesVoisinesLigne=false;
						    if(c.getPortionColonne()!=col)
							cellulesVoisinesCol=false;
						}
						
					    }
					    if(cellulesVoisinesLigne==true )
					    {
						li.getValeursReservess().set(valeur);
						historiqueTmp.add(new ElementHistoriqueValeurLocked(valeur,li));
					    }
					    else if(cellulesVoisinesCol==true)
					    {
						col.getValeursReservess().set(valeur);
						historiqueTmp.add(new ElementHistoriqueValeurLocked(valeur,col));
					    }
					}
				}
				/*
				 * Paires ou triples 
				 */
				/*
				Set<Cellule> cellsPotentAssociees=null;
				Integer valeurResa=null;
				if(cells.size()>1 && cells.size()<=Grille.taille1CoteCarre)
				{
				    if(cellsPotentAssociees==null)
				    {
					cellsPotentAssociees=cells;
					valeurResa=valeur;
				    }
				    
				    else
				    {
					if(cells.containsAll(cellsPotentAssociees))
					{
					    historiqueTmp.add(new ElementHistoriqueValeurResa(valeur,col));
					}
				    }
				}*/
			}
			
			
			//System.out.println("Test :"+(System.currentTimeMillis()-deb)+" mls");

		}
		
		return nbrCellusDemasquees;
	}

	/*
	 * Test Si une valeur peut aller dans plusieurs cellule vides d'une meme untité (ligne,colonnen carre)
	 */
	private  Map getCellsByValeurs(Set<Cellule> celullesAtester,boolean MAJCellMoinsHypo,HistoriqueList historiqueTmp) throws CulDeSacException
	{
	    Map<Integer,Set<Cellule>> valeursUniques = new HashMap();
	    Iterator<Cellule> cellsToTest = celullesAtester.iterator();
	    // je prends une cellule 
		while (cellsToTest.hasNext())
		{
		    Cellule cell =cellsToTest.next();
			//if(cell.getValeur().compareTo(Cellule.CELLULE_VIDE)==0)
			{
			    	
				Set valFiltrees =cell.getValeursPossibles();
				// si je n'ai pas de valeur possible , c'est que c'est un cul de sac. Machines arriere toutes !!!
				if(valFiltrees.size()==0 )
				    throw new CulDeSacException();
				if(MAJCellMoinsHypo && valFiltrees.size()<maxValValeur4CelluleMoinHypo)
				{
					maxValValeur4CelluleMoinHypo=valFiltrees.size();
					celluleMoinHypo=cell;
				}
				// optim : si une seule valeur peut aller ici , alors on valorise la cellule avec la valeur 
				if(valFiltrees.size()==1)
				{
				    Integer valeur =(Integer) valFiltrees.iterator().next();
				    cell.tryValeur(valeur);
				    valeursUniques.remove(valeur);
				    historiqueTmp.add(new ElementHistoriqueValeur4Cellule(cell));
				 
				    // TODO je retire des valeurs de la zone, la cellule qui vient d'etre remplie 
				}
				else
				{
					Iterator<Integer> it =	valFiltrees.iterator();
					//sinon je fais le taf normal 
					while (it.hasNext())
					{
						Integer valeur =it.next();
								
						if(!valeursUniques.containsKey(valeur))
						{
						    Set l =new HashSet();
						    l.add(cell);
						    valeursUniques.put(valeur,l);
						}
						else
						{
						    valeursUniques.get(valeur).add(cell);
						}
					}
				}
			}
		}
				
		
		return valeursUniques;
	}
	

		


	

	



}
