public class MEE{

    private int [] tabFreq; // tabFreq[i] des jetons.
    private int nbTotEx;// nombre total d’exemplaires ou cardinal de la main courante de jetons*

    /**
* pré-requis : max >= 0
* action : crée un multi-ensemble vide dont les éléments seront
* inférieurs à max
*/
public MEE (int max){
    this.tabFreq= new int[max];
    this.nbTotEx=7;
} // A revoir j'ai pas compris la question 
   



/*
    * pré-requis : les éléments de tab sont positifs ou nuls
    * action : crée un multi-ensemble dont le tableau de fréquences est
    *. une copie de tab
   
    Notes : Je parcoures le tableau 
    en copiant les valeurs et verifie la frequence d'un element pour obtenir un cardinal pour l'ensemble copié */
    
    public MEE (int[] tab){
    for(int i = 0; i<tab.length;i++){
    this.tabFreq[i]= tab[i];
    if (tab[i]!=0){
        this.nbTotEx+=tab[i];}}
    }


    public MEE (MEE e){
    this(e.tabFreq);}
    // la recopie du cardinal nbTotEx à été intégré au constructeur par recopie d'un tableau
    
    
    
    /**
* résultat : vrai ssi cet ensemble est vide
*/
public boolean estVide (){
    boolean res = true;
    int i;
    while(res && i<this.tabFreq.length){
        if(this.tabFreq[i]==0){
            i++;
        }
        else{res=false;}
    }
    return(res);
}

    /**
    * pré-requis : 0 <= i < tabFreq.length
    * action : ajoute un exemplaire de i à this
    */
    public void ajoute (int i) { 






        
    }
    /**
    * pré-requis : 0 <= i < tabFreq.length
    * action/résultat : retire un exemplaire de i de this s’il en existe,
    * et retourne vrai ssi cette action a pu être effectuée
    */
    public boolean retire (int i) {}
    /**
    * pré-requis : this est non vide
    * action/résultat : retire de this un exemplaire choisi aléatoirement
    * et le retourne
    */
    public int retireAleat () {}
    /**
    * pré-requis : 0 <= i < tabFreq.length
    * action/résultat : transfère un exemplaire de i de this vers e s’il
    * en existe, et retourne vrai ssi cette action a pu être effectuée
    */
    public boolean transfere (MEE e, int i) {}
    /** pré-requis : k >= 0
    * action : tranfère k exemplaires choisis aléatoirement de this vers e
    * dans la limite du contenu de this
    * résultat : le nombre d’exemplaires effectivement transférés
    */
    public int transfereAleat (MEE e, int k) {}
    4
    /**
    * pré-requis : tabFreq.length <= v.length
    * résultat : retourne la somme des valeurs des exemplaires des
    * éléments de this, la valeur d’un exemplaire d’un élément i
    * de this étant égale à v[i]
    */
    public int sommeValeurs (int[] v){}


}