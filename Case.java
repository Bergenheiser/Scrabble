public class Case {
        private int couleur;
        private char lettre;
        private boolean recouverte;
        
        
/**
* pré-requis : uneCouleur est un entier entre 1 et 5
* action : constructeur de Case
*/
    public Case(int uneCouleur){
    this.couleur= uneCouleur;
    this.lettre=0;
    this.recouverte=false;
    }
    
    
    /**
    * résultat : la couleur de this, un nombre entre 1 et 5
    */
    public int getCouleur(){
        return(this.couleur);}
   
        /**
    * pré-requis : cette case est recouverte
    */
    public char getLettre(){
        return this.lettre;}
   
   
    /**
    * pré-requis : let est une lettre majuscule
    */
    public void setLettre(char let){
        this.lettre = let;
        this.recouverte=true;
    }
    /**
    * résultat : vrai ssi la case est recouverte par une lettre
    */
    public boolean estRecouverte(){
        boolean resultat;
        if(this.recouverte && this.lettre!=0){
            resultat=true;
        }
        else{resultat=false;}
        return(resultat);
    }
    public String toString(){
        String resultat="";
        if(this.estRecouverte()){
           resultat+=this.getLettre();
        }
        else{resultat+=this.getCouleur();}
        return(resultat);
    }
}