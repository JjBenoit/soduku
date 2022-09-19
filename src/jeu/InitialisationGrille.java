package jeu;

import grille.Grille;
import grille.elements.Cellule;
import grille.elements.Zone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class InitialisationGrille {


	
	private Grille grille;
	
	
	
	private InitialisationGrille(int tailleTab,int nbrTab) {
		
		grille = new Grille(tailleTab, nbrTab);
	}
	
	
	public static void main(String[] args) {
	
	    boolean continuer=true;
	    while (continuer) {
		int dim =3;
		Grille g =InitialisationGrille.genererGrille(dim,dim);
		continuer=g.validerGrille();
	    }
		
		
	}

	
	public static Grille genererGrille(int tailleTab,int nbrTab) {
		
		InitialisationGrille init = new InitialisationGrille(tailleTab,nbrTab);
				
		long deb =System.currentTimeMillis();
		// remplit une diagonale de carrés
		int index=0;
		for (int i = 0; i <nbrTab; i++) {
		    init.remplirCarre((i*nbrTab)+index);
		    index++;
		}
		
		
		//init.grille.afficher();
		init.parcourirTabCellules(0,init.grille.getCellules());
		
		
		long fin =System.currentTimeMillis();
		init.grille.afficher();
		/*
		init.grille.afficher();
		
		System.out.println("Grille generées en  :"+(fin-deb)+" mils");
		System.out.println("Grille valide :"+init.grille.validerGrille());
		//System.out.println("retourArr :"+init.retourArr);*/
		
		return init.grille;
	}
	
	public  static void supressRandomValues(int nrbCellulesRemplies,Grille grille)
	{
		List<Integer> l = new ArrayList();
			
		for (int i = 0; i <grille.getNbTotalCellules(); i++){
		    l.add(i);
		}
		
		Collections.shuffle(l, new Random());
		int nbrCellAvider=grille.getNbTotalCellules()-nrbCellulesRemplies;
		
		for (int i = 0; i <nbrCellAvider; i++)	{
		    Integer index =l.get(i);
		    grille.getCellules().get(index).removeValeur();
		}
		grille.afficher();
	}
		
	
	

	private  void remplirCarre(int index)
	{
	    List<Integer> mTemp = new ArrayList(Grille.valeursPossibles);
	    Collections.shuffle(mTemp);					
	    for (int i = 0; i < mTemp.size(); i++)
	    {		
		Integer valeur =  mTemp.get(i);
					
		// toutes les cellules du carré vont y passer
		List<Cellule> cellules =grille.getCarres().get(index).getCellules();
		for (int j = 0; j < cellules.size(); j++)
		{
		    if(cellules.get(j).getValeur().compareTo(0)==0)
		    {
			if(cellules.get(j).tryValeur(valeur))
			    break;
		    }
				
		}
	    }
			
	}
	/*
	 * Au dela de 9, parfois des difficultés de generer la grille simplement se produise
	 * Au dela de 18, impossible de generer la grille simplement
	 */
	private  void randomInit(int nrbChiffres)
	{

		for (int i = 0; i <nrbChiffres; i++)
		{
			Random random = new Random();
			int uncote =(grille.getTailleTab()*grille.getTailleTab());
				
			while(true)
			{
				Cellule cell =grille.getCellule(random.nextInt(uncote),random.nextInt(uncote));
				if(cell.tryValeur(random.nextInt(uncote)))
					break;
			}
			
		}
		
	
			
	}
	
	

	

	public  boolean parcourirTabCellules(int indexProgression,List<Cellule> cellules)
	{
		boolean succes=true;
	
		if(!grille.isGrilleRemplie())
		{
			for (;indexProgression <cellules.size(); indexProgression++)
			{
				
				Cellule cell =cellules.get(indexProgression);
				if(cell.getValeur().compareTo(Cellule.CELLULE_VIDE)==0)
				{
					
					Set valPossible =cell.getValeursPossibles();
					
					if(valPossible.size()==0)
						return false;
					else
						succes=choisirHypothese(indexProgression,valPossible,cellules);
					
					if(!succes)
					{
						
						return false;
						
					}
						
				}
				
					
			}
		}
	
				
		return succes;
	
			
	}
	
	private  boolean choisirHypothese(int indexProgression,Set valPossible,List<Cellule> cellules)
	{
		boolean succes=false;
		Cellule cell = cellules.get(indexProgression);
		List<Integer> valeursPossibles = new ArrayList(valPossible);
		Collections.shuffle(valeursPossibles);
				
		for (int i=0; i<valeursPossibles.size();i++)
		{
			//int index =random.nextInt(valeursPossibles.size());
			
			//valeursPossibles.remove(index);
			Integer valeur =valeursPossibles.get(i);
			if(valeur>0 && cell.tryValeur(valeur))
			{
				succes =parcourirTabCellules((indexProgression+1),cellules);
				if(!succes)
				{
					cell.removeValeur();
				
				}
			}
					
		}
		
		return succes;
	}
	
}
