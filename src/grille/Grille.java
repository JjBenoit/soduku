package grille;


import grille.elements.Cellule;
import grille.elements.Droite;
import grille.elements.PortionDroite;
import grille.elements.Zone;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class Grille {
	
    	public static Set<Integer> valeursPossibles =new HashSet();
	public static int nbTotalValPoss=0;
	public static int nbrCarreParParCote;
	public static int taille1CoteCarre;
	public static int nbTotalCellules=0;
	public static int nbCellulesParCarre=0;
	
	
	private Cellule [][] theGrille ;
	private List<Cellule> cellules ;
	private Set<Cellule> cellulesRestantes ;
	
	private List<Zone>  carres ;
	private List<Droite> lignes ;
	private List<Droite> colonnes ;
	
	
	Set<PortionDroite> portionsCandidatesRecherchesValeursReservees = new HashSet();
	Set<Zone> zonesCandidatesRecherchesByValeursCroisees = new HashSet();
	
	public Grille(int taille1CoteCarre,int nbrCarreParParCote) {
		this.taille1CoteCarre=taille1CoteCarre;
		this.nbrCarreParParCote=nbrCarreParParCote;
		this.nbCellulesParCarre=taille1CoteCarre*taille1CoteCarre;
		
		nbTotalValPoss=taille1CoteCarre*taille1CoteCarre;
		nbTotalCellules=(taille1CoteCarre*taille1CoteCarre)*(nbrCarreParParCote*nbrCarreParParCote);
		
		theGrille= new Cellule [nbrCarreParParCote*nbrCarreParParCote][nbrCarreParParCote*taille1CoteCarre];
		cellules = new LinkedList();
		carres= new LinkedList();
		lignes= new LinkedList();
		colonnes= new LinkedList();
		
		init();
		
		cellulesRestantes = new HashSet<Cellule>(cellules);
	}
	public  void test()
	{
		afficher();
		for (int x=0; x < lignes.size(); x++) 
		{
			for (int i=0; i < lignes.get(x).getCellules().size(); i++) 
			{
				lignes.get(x).getCellules().get(i).forceValeur(x);
			}
			afficher();
		}
		for (int x=0; x < cellules.size(); x++) 
		{
			cellules.get(x).forceValeur(0);
		}
		afficher();
		for (int x=0; x < colonnes.size(); x++) 
		{
			
			for (int i=0; i < colonnes.get(x).getCellules().size(); i++) 
			{
				colonnes.get(x).getCellules().get(i).forceValeur(x);
			}
			afficher();
		}
		for (int x=0; x < cellules.size(); x++) 
		{
			cellules.get(x).forceValeur(0);
		}
		afficher();
		for (int x=0; x < carres.size(); x++) 
		{
			
			for (int i=0; i < carres.get(x).getCellules().size(); i++) 
			{
				carres.get(x).getCellules().get(i).forceValeur(x);
			}
			afficher();
		}
	
		
	}
	public  void testCelluleslIees()
	{
		for (int x=0; x < cellules.size(); x++) 
		{
			
			for (int i=0; i <cellules.get(x).getCellulesLiees().size(); i++) 
			{
				cellules.get(x).getCellulesLiees().get(i).forceValeur(1);
			}
			afficher();
			for (int i=0; i < cellules.size(); i++) 
			{
				cellules.get(i).forceValeur(0);
			}
		}
	}
	public  void init()
	{
	
	    for (int i=0; i < taille1CoteCarre*taille1CoteCarre; i++) 
	    {
		valeursPossibles.add((i+1));
	    }
		//init lignes
		for (int ligne=0; ligne < taille1CoteCarre*taille1CoteCarre; ligne++) 
		{
		    Droite ligneValeurs = new Droite(taille1CoteCarre*taille1CoteCarre,Integer.toString(ligne),Droite.LIGNE);
			
			lignes.add(ligneValeurs);
			PortionDroite portionZone=null;  
			for (int col=0;col < taille1CoteCarre*taille1CoteCarre; col++)
			{
			 		    	
				theGrille[ligne][col]=new Cellule("l:"+ligne+" c:"+col,this);
				theGrille[ligne][col].setLigne(ligneValeurs);
				ligneValeurs.addCellule(theGrille[ligne][col]);
				cellules.add(theGrille[ligne][col]);
				ligneValeurs.getCellulesRestantes().add(theGrille[ligne][col]);
				zonesCandidatesRecherchesByValeursCroisees.add(ligneValeurs);
				
				// Portions de ligne (utiles aux calcul des probailités des cellules d'une meme ligne, mais de portion différente (contenues dans un autre carré) )
				if(col==0 || (col%taille1CoteCarre)==0)
				{ 
					portionZone=new PortionDroite(ligneValeurs);
					ligneValeurs.getPortionsDroite().add(portionZone);
			    	    
				}
			    	
				
				theGrille[ligne][col].setPortionLigne(portionZone);
				portionsCandidatesRecherchesValeursReservees.add(portionZone);
			}
		}
		//init colonne
		for (int col=0; col < taille1CoteCarre*taille1CoteCarre; col++)
		{
		    Droite colonneValeurs = new Droite(taille1CoteCarre*taille1CoteCarre,Integer.toString(col),Droite.COLONNE);
				
			colonnes.add(colonneValeurs);
			PortionDroite portionZone=null;
			for (int ligne =0; ligne < taille1CoteCarre*taille1CoteCarre; ligne++) 
			{
			    
			    theGrille[ligne][col].setColonne(colonneValeurs);
			    colonneValeurs.addCellule(theGrille[ligne][col]);
			    colonneValeurs.getCellulesRestantes().add(theGrille[ligne][col]);
			    zonesCandidatesRecherchesByValeursCroisees.add(colonneValeurs);
			 // Portions de ligne (utiles aux calcul des probailités des cellules d'une meme ligne, mais de portion différente (contenues dans un autre carré) )
			    if(ligne==0 || (ligne%taille1CoteCarre)==0)
			    { 
				portionZone=new PortionDroite(colonneValeurs);
				colonneValeurs.getPortionsDroite().add(portionZone);
				
			    }
			    	
			   
			    theGrille[ligne][col].setPortionColonne(portionZone);
			    portionsCandidatesRecherchesValeursReservees.add(portionZone);
				
			}
		}
		
		int col =0;
		int ligne=0;
		
		// tous les carrés
		for (int indexCarre =1; indexCarre < (nbrCarreParParCote*nbrCarreParParCote)+1; indexCarre++) 
		{
			// debut haut gauche
			col =setCellulesFor1Carre( col, ligne,indexCarre);
			
			// on passe vers un autre caré de la meme ligne (autre colonne)
			if(indexCarre%nbrCarreParParCote==0)
			{
				// retour à gauche : saut de ligne 
				col=0;
				ligne+=taille1CoteCarre;
			}
		}
		
		/*
		 * Determine les cellules appartenant à une meme zone (carre+ligne+colonne) 
		 */
		for (int i=0; i < cellules.size(); i++) 
		{
			Cellule cellule =cellules.get(i);
						
			Set m = new HashSet();
			m.addAll(cellule.getLigne().getCellules());
			m.addAll(cellule.getCarre().getCellules());
			m.addAll(cellule.getColonne().getCellules());
			m.remove(cellule);
			
			cellule.setCellulesLiees(new ArrayList(m));
		
		}
		// init les carres pour les portions 
		for (int i=0; i < cellules.size(); i++) 
		{
			Cellule cellule =cellules.get(i);
			cellule.getPortionColonne().setCarre(cellule.getCarre());
			cellule.getPortionLigne().setCarre(cellule.getCarre());
		}
		// init les portions soeurs
		for (int i=0; i < cellules.size(); i++) 
		{
			Cellule cellule =cellules.get(i);
			Set s = new HashSet(cellule.getPortionColonne().getZoneMere().getPortionsDroite());
			s.remove(cellule.getPortionColonne());
			cellule.getPortionColonne().setPortionsVoisines(s);
			
			 s = new HashSet(cellule.getPortionLigne().getZoneMere().getPortionsDroite());
			 s.remove(cellule.getPortionLigne());
			 cellule.getPortionLigne().setPortionsVoisines(s);
			
		}
	}
	private  int setCellulesFor1Carre(int col,int ligne,int index)
	{
		// un carré
	    	Zone c = new Zone(taille1CoteCarre*taille1CoteCarre,Integer.toString(index));
		zonesCandidatesRecherchesByValeursCroisees.add(c);
		carres.add(c);
				
		int colI=0;
		for (;colI < taille1CoteCarre; colI++)
		{
			for (int lignex =0; lignex < taille1CoteCarre; lignex++) 
			{
				//init cellules
				theGrille[ligne+lignex][col+colI].setCarre(c);
				c.addCellule(theGrille[ligne+lignex][col+colI]);
				c.getCellulesRestantes().add(theGrille[ligne+lignex][col+colI]);
			}
			
		}
		return colI+col;
	}	
	

	
	

	public  boolean validerUnciteValeurs(List<Cellule> valeurs)
	{
		Map map = new HashMap();
		for (int i = 0; i < valeurs.size(); i++) {
			// si la cellule n'est pas vide
			if(valeurs.get(i).getValeur().compareTo(Cellule.CELLULE_VIDE)!=0)
			{
				// est ce que la valeur existe deja dans cette ligne
				if(!map.containsKey(valeurs.get(i).getValeur()))
					map.put(valeurs.get(i).getValeur(), valeurs.get(i).getValeur());
				else
					return false;
			}
			//si la cellule est vide, la grille n'est pas remplie
			else 
				return false;
			
		}
		return true;
	}
		

	public  boolean validerGrille()
	{
		for (int i = 0; i < lignes.size(); i++) {
			if(!validerUnciteValeurs(lignes.get(i).getCellules()))
				return false;
		}
		for (int i = 0; i < colonnes.size(); i++) {
			if(!validerUnciteValeurs(colonnes.get(i).getCellules()))
				return false;
		}
		for (int i = 0; i < carres.size(); i++) {
			if(!validerUnciteValeurs(carres.get(i).getCellules()))
				return false;
		}
		
		return true;
	}
	public  void afficher()
	{
		
		int sizeOfOneCote =nbrCarreParParCote*taille1CoteCarre;
		System.out.println("Cells remplies : "+(nbTotalCellules-cellulesRestantes.size())+ " vides: "+cellulesRestantes.size());
		System.out.print("  ");
		for (int xx=0; xx < sizeOfOneCote; xx++) {
			System.out.print(xx+"|");
		}
		System.out.println();
		
		for (int ligne =0; ligne < sizeOfOneCote; ligne++) {
		
			System.out.print(ligne+":");
			
			for (int col=0; col < sizeOfOneCote; col++) {
				Cellule cell =theGrille[ligne][col];
			
				if(cell.getValeur().compareTo(Cellule.CELLULE_VIDE)==0)
					System.out.print("X");
				else
					System.out.print(cell.getValeur());
				
				System.out.print("|");
			}
			System.out.println();
			
		}
		System.out.println();System.out.println();
	}
	public  boolean isGrilleRemplie()
	{
	    return (cellulesRestantes.size()==0);
	}
	public Cellule[][] getTheGrille() {
		return theGrille;
	}
	public List<Cellule> getCellules() {
		return cellules;
	}
	public Cellule getCellule(int ligne,int col) {
		return theGrille[ligne][col];
	}
	public int getNbrTab() {
		return nbrCarreParParCote;
	}
	public int getTailleTab() {
		return taille1CoteCarre;
	}
	public List<Zone> getCarres() {
		return carres;
	}
	public void setCarres(List carres) {
		this.carres = carres;
	}
	public List<Droite> getLignes() {
		return lignes;
	}
	public void setLignes(List lignes) {
		this.lignes = lignes;
	}
	public List<Droite> getColonnes() {
		return colonnes;
	}
	public void setColonnes(List colonnes) {
		this.colonnes = colonnes;
	}
	public int getNbTotalCellules() {
		return nbTotalCellules;
	}
	public void setNbTotalCellules(int nbTotalCellules) {
		this.nbTotalCellules=nbTotalCellules; 
	}
	public Set<Cellule> getCellulesRestantes() {
		return cellulesRestantes;
	}
	public int getNbTotalValPoss() {
		return nbTotalValPoss;
	}
	public void setNbTotalValPoss(int nbTotalValPoss) {
		this.nbTotalValPoss = nbTotalValPoss;
	}

	public static Map suffle(Map valeurs) {
		
		List mTemp = new ArrayList(valeurs.keySet());
		Collections.shuffle(mTemp,new Random());
		
		Map shuffleValeurs = new LinkedHashMap();
		
		for (int i = 0; i < mTemp.size(); i++) {
			
			shuffleValeurs.put(mTemp.get(i), mTemp.get(i));
		}
		
		return shuffleValeurs ;
	}
}