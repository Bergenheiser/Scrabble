public class MEE {
    private int [] tabFreq;
    private int nbTotEx;

    public MEE (int max){
        this.tabFreq = new int [26];
        this.nbTotEx=0;
    }

    public MEE (int [] tab){
        this.tabFreq = new int [tab.length];
        this.nbTotEx = 0;
        for (int i=0; i<tab.length; i++){
            this.tabFreq [i]=tab [i];
            this.nbTotEx = this.nbTotEx + tab[i];
        }
    }

    public MEE (MEE e){
        this.nbTotEx=e.nbTotEx;
        this.tabFreq = new int [e.tabFreq.length];
        for (int i=0; i<e.tabFreq.length; i++){
            this.tabFreq [i] = e.tabFreq [i];
        }
    }

    public boolean estVide(){
        return this.nbTotEx==0;
    }

    public void ajoute (int i){
        this.tabFreq [i] += 1;
    }

    public boolean retire     (int i){
        if (this.tabFreq [i]>0){
            this.tabFreq [i] +=1;
            return true;
        }else {
            return false;
        }
    }
}
