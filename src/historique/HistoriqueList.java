package historique;

import java.util.ArrayList;


public class HistoriqueList extends ArrayList{
  
    public void backToThePastAllElements() {
       
	for (int i = 0; i < size(); i++) {
	    
	    ElementHistorique elementHistorique =  (ElementHistorique) get(i);
	    elementHistorique.clean();
	}
	
    }
}

