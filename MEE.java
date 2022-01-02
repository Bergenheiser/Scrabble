
public class MEE {

    private int[] tabFreq; // tabFreq[i] des jetons.
    private int nbTotEx;// nombre total d’exemplaires ou cardinal de la main courante de jetons*
    
    public int[] getTabFreq(){
        return this.tabFreq;
    }
    public int getCardinal(){
        return this.nbTotEx;
    }
    
    /**
     * pré-requis : max >= 0
     * action : crée un multi-ensemble vide dont les éléments seront
     * inférieurs à max
     */
    public MEE(int max) {
        this.tabFreq = new int[max];
        this.nbTotEx = 0;
    }

    /**
     * pré-requis : les éléments de tab sont positifs ou nuls
     * action : crée un multi-ensemble dont le tableau de fréquences est
     * . une copie de tab
     * 
     * Notes : Je parcoures le tableau
     * en copiant les valeurs et verifie la frequence d'un element pour obtenir un
     * cardinal pour l'ensemble copié
     */

    public MEE(int[] tab) {
        this(tab.length);
        for (int i = 0; i < tab.length; i++) {
            this.tabFreq[i] = tab[i];
            if (tab[i] != 0) {
                this.nbTotEx += tab[i];
            }
        }
    }

    /**
    *La recopie du tableau entraine la copie du cardinal car il est intégré dans le constructeur
     */
    public MEE(MEE e) {
        this(e.tabFreq);
    }

    /**
     * résultat : vrai si et seuleument si (ssi) cet ensemble est vide
     */
    public boolean estVide() {
        return this.nbTotEx == 0;
    }

    /**
     * pré-requis : 0 <= i < tabFreq.length
     * action : ajoute un exemplaire de i à this
     * cela entraine l'augmentation du total de 1 également
     */
    public void ajoute(int i) {
        this.tabFreq[i] += 1;
        this.nbTotEx++;
    }

    /**
     * pré-requis : 0 <= i < tabFreq.length
     * action/résultat : retire un exemplaire de i de this s’il en existe et diminue donc le total,
     * et retourne vrai ssi cette action a pu être effectuée.
     */

    public boolean retire(int i) {
        boolean resultat;

        if (this.tabFreq[i] > 0) {

            this.tabFreq[i]--;
            this.nbTotEx--;
            resultat = true;

        } else {

            resultat = false;
            
        }
        return (resultat);
    }

    /**
    * pré-requis : this est non vide
    * action/résultat : retire de this un exemplaire choisi aléatoirement
    * et le retourne
    */
    public int retireAleat () {
        int choix;

        do{
            choix = Ut.randomMinMax(0,this.tabFreq.length-1);
        }while(this.tabFreq[choix]==0);
        
        this.retire(choix);
        return(choix);
        }
    

    /**
     * pré-requis : 0 <= i < tabFreq.length
     * action/résultat : transfère un exemplaire de i de this vers e s’il
     * en existe, et retourne vrai ssi cette action a pu être effectuée
     */
    public boolean transfere(MEE e, int i) {
           boolean resultat;

           if(this.tabFreq[i] > 0){

               this.retire(i);
               e.ajoute(i);  
               resultat=true;
               
           }else {
               resultat=false;
               }

           return(resultat);
        }


    /**
     * pré-requis : k >= 0
     * action : tranfère k exemplaires choisis aléatoirement de this vers e
     * dans la limite du contenu de this
     * résultat : le nombre d’exemplaires effectivement transférés
     */
    public int transfereAleat(MEE e, int k) {
        int index=1;
        int choix;
        int resultat=0;
        while(index<=k){
            choix=Ut.randomMinMax(0,(this.tabFreq.length-1));
            if(this.transfere(e, choix)){
                resultat++;
            }
            index++;
        }
        return(resultat);
        }

    /**
     * pré-requis : tabFreq.length <= v.length
     * résultat : retourne la somme des valeurs des exemplaires des
     * éléments de this, la valeur d’un exemplaire d’un élément i
     * de this étant égale à v[i]
     */
    public int sommeValeurs(int[] v) {
        int somme = 0;
        for (int i = 0; i<this.tabFreq.length; i++){
            somme += (tabFreq[i]*v[i]);
        }
        return somme;
    }
    /**Pré-requis:
     * c est une Letrre Majuscule
     * @param c
     * @return vrai ssi l'ensemble contient au moins 1 fois le charactère passé en paramètre
     */
    public boolean contientChar(char c){
        int i= Ut.majToIndex(c);
        boolean resultat;
        if(this.tabFreq[i]>0){
            resultat=true;}
        
        else{resultat=false;}
        return resultat;
    }


    

}