package motif ;

/**
 *  Classe Mot : dispose de methodes de recherche de facteur.
 *  Classiquement, un facteur est ici appele motif, pour reprendre 
 *  le vocabulaire de ce genre d'algorithmes.
 *  un Mot est forme d'une chaine de caracteres (String)
 *
 *@author     Anne-Cecile Caron
 */
 
public class Mot {

  private String m;
  // le mot


  /**
   *  Constructeur de Mot
   *
   *@param  m  La chaine de caracteres qui constitue le Mot
   */
  public Mot(String m) {
	  this.m = m;
  }
  
  /**
   *  Renvoie une representation du Mot sous forme d'une chaine
   *  
   *@return     la chaine qui constitue le mot courant.
   */
  public String toString(){
    return this.m ;
  }
  
  /**
   *  Renvoie la longueur du mot
   *
   *@return    le nombre de caracteres qui forment le mot
   */
  public int length(){
    return this.m.length() ;
  }
  
   /**
   *  Teste si le mot courant est facteur du mot passe en parametre 
   *  a partir de la position pos
   *
   *@param  mot2  Le mot dans lequel on cherche le mot courant
   *@param  pos   La position dans mot2 a partir de laquelle 
   *              on recherche le mot courant
   *@return       true ssi le mot courant est facteur de mot2 a partir de pos
   */ 
  public boolean estFacteurDe(Mot mot2, int pos){
      for (int j=0 ; j< this.length() ; j++){
        if (mot2.m.charAt(pos+j) != this.m.charAt(j)){
          return false ;
        }
      }
      return true ;
  }
  
  /**
   *  Teste si le mot courant est prefixe du mot passe en parametre 
   *
   *@param  mot2  Le mot dans lequel on recherche le mot courant
   *@return       true ssi le mot courant est prefixe de mot2 
   */ 
  public boolean estPrefixeDe(Mot mot2){
    return this.estFacteurDe(mot2,0);
  }  
  
  /**
   *  Teste si le mot courant est suffixe du mot passe en parametre 
   *
   *@param  mot2  Le mot dans lequel on recherche le mot courant
   *@return       true ssi le mot courant est suffixe de mot2 
   */ 
  public boolean estSuffixeDe(Mot mot2){
    return this.estFacteurDe(mot2, mot2.m.length() - this.m.length()) ;
  }
  
  /**
   *  Recherche d'un motif dans un mot
   *  avec l'algorithme naif.
   *
   *@param  motif  Le motif a rechercher dans le mot this.
   *@return        L'indice ou on a trouve le motif, -1 si pas trouve.
   */
  public int indiceMotifNaif(Mot motif) {

	 int textLen = this.length();
	 int motifLen = motif.length();

	 for (int i = 0; i <= textLen - motifLen; i += 1){
		 if (motif.estFacteurDe(this, i)){
			 return i;
		 }
	 }
	 
    return -1 ;
  }
  
  /**
   *  Recherche d'un motif dans un mot avec l'algorithme base 
   *  sur la construction d'un automate.
   *  L'avantage est que l'on ne traite qu'une seule fois chaque lettre
   *  du mot dans lequel on recherche le motif.
   *
   *@param  motif  Le motif a rechercher dans le mot this.
   *@return        L'indice ou on a trouve le motif, -1 si pas trouve.
   */
  public int indiceMotifAutomate(Mot motif) {
    if (motif.length() == 0){
    	return 0;
    }
    else {
    	AutomateMotif autoMotif = new AutomateMotif(motif);
    	int state = autoMotif.etatInitial();
    	for (int i = 0; i < this.length(); i++) {
    		state = autoMotif.transition(state, this.toString().charAt(i));
    		if (state == autoMotif.etatFinal()){
    			return i + 1 - motif.length();
    		}
		}
    	
    }
     return -1 ;
  } 
  
  /**
   *  Recherche d'un motif dans un mot avec l'algorithme de Knuth-Morris-Pratt.
   *  L'avantage est que l'on n'a pas besoin de construire tout l'automate
   *
   *@param  motif  Le motif a rechercher dans le mot this.
   *@return        L'indice ou on a trouve le motif, -1 si pas trouve.
   */ 
  public int indiceMotifKMP(Mot motif){
	  if (motif.length() == 0){
		  return 0;
	  }
	  else {
		  int[] pref = this.calculerFonctionPrefixe(motif);
		  int q = 0;
		  for (int i = 0; i < this.length(); i += 1){
			  while (q > 0 && motif.m.charAt(q) != this.m.charAt(i)){
				  q = pref[q]; 
			  }
			  if (motif.m.charAt(q) == this.m.charAt(i)){
				  q += 1;
			  }
			  if (q == motif.length()){
				  return i - motif.length() + 1;
			  }
		  }
	  }
	  return -1 ;
  }
  
  /* La convention d'appel de cette methode n'a aucun sens, mais bon... */
  private int[] calculerFonctionPrefixe(Mot motif){
	  int[] pref = new int[motif.length()+1];
	  int k = 0;
	  
	  pref[0] = 0;
	  pref[1] = 0;
	  
	  for (int q = 2; q <= motif.length(); q += 1){
		  while (k > 0 && motif.m.charAt(k) != motif.m.charAt(q-1)){
			  k = pref[k];
		  }
		  if (motif.m.charAt(k) == motif.m.charAt(q-1)){
			  k += 1;
		  }
		  pref[q] = k;
	  }

	  return pref;
  } 
  
  /**
   * le programme principal permet de tester Knuth-Morris-Pratt
   * On passe sur la ligne de commande comme premier argument, 
   * le mot, et comme second argument, le motif.
   */
  public static void main(String args[]){
    Mot mot = new Mot(args[0]) ;
    System.out.println("indice : "+ mot.indiceMotifKMP(new Mot(args[1]))) ;
  
  }

}

