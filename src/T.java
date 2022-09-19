
public class T {

   
    
    public static void main(String[] args) {
	int n=3;
	int [][][] grille = new int [n*n][n*n][((n*n)+1)];
	int i =grille[0][0].length;
	 go(grille);
	 afficher(grille);
    }
    public static void go(int [][][] grille)
    {
	
	
	long deb =System.nanoTime();
	for (int i = 0; i < grille.length; i++) {
	    for (int j = 0; j < grille[i].length; j++) {
		for (int x = 0; x < grille[i][j].length; x++) {
		    grille[i][j][x]=(x);
		}
	    }
	}
	long fin=System.nanoTime();
	System.out.println(fin-deb);
	
    }
    public static void afficher(int [][][] grille)
    {
	for (int i = 0; i < grille.length; i++) {
	    System.out.println();
	    for (int j = 0; j < grille[i].length; j++) {
		 System.out.println();
		 for (int x = 0; x < grille[i][j].length; x++) {
		     System.out.print(grille[i][j][x]);
		}
	    }
	}
    }
}
