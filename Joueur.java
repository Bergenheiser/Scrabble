public class Joueur {
    private String nom;
    private MEE chevalet;
    private int score;

    public Joueur(String unNom){
        this.nom=unNom;

    }

    public String toString(){
        return("Joueur: "+this.nom+'\n'+"Score: "+this.score);
    }

    public int getScore(){
        return this.score;
    }
    public void ajouteScore(int nb){
        this.score+=nb;
    }
    /**
* pré-requis : nbPointsJet indique le nombre de points rapportés par
* chaque jeton/lettre
* résultat : le nombre de points total sur le chevalet de ce joueur
* suggestion : bien relire la classe MEE !
*/
    public int nbPointsChevalet (int[] nbPointsJet){
   
        int scoreChevalet = this.chevalet.sommeValeurs(nbPointsJet);
        return(scoreChevalet);
    }

/**
* pré-requis : les éléments de s sont inférieurs à 26
* action : simule la prise de nbJetons jetons par this dans le sac s,
* dans la limite de son contenu.
*/
    public void prendJetons (MEE s, int nbJetons) {

        System.out.println(s.transfereAleat(this.chevalet,nbJetons));
    }


/**
* pré-requis : les éléments de s sont inférieurs à 26
* et nbPointsJet.length >= 26
* action : simule le coup de this : this choisit de passer son tour,
* d’échanger des jetons ou de placer un mot
* résultat : -1 si this a passé son tour, 1 si son chevalet est vide,
* et 0 sinon
*/
    public int joue(Plateau p, MEE s, int[] nbPointsJet) {
        int resultat;
        System.out.println("N pour passer, E pour échanger, P pour placer : ");
        char userInput= Ut.saisirCaractere();
        switch(userInput){
            case 'N':
            resultat= -1;
            break;
            case 'E':
            resultat=0;
            break;
            case'P':
            if (this.chevalet.getCardinal()==0){
                resultat=1;
            }
            else{resultat=0;}
            break;
            default: 
            System.out.println("Merci de ressaisir un choix conforme.");
            joue(p,s,nbPointsJet);}
        
        return resultat;
        }
        /** pré-requis : les éléments de s sont inférieurs à 26
* et nbPointsJet.length >= 26
* action : simule le placement d’un mot de this :
* a) le mot, sa position sur le plateau et sa direction, sont saisis
* au clavier
* b) vérifie si le mot est valide
* c) si le coup est valide, le mot est placé sur le plateau
* résultat : vrai ssi ce coup est valide, c’est-à-dire accepté par
* CapeloDico et satisfaisant les règles détaillées plus haut
* stratégie : utilise la méthode joueMotAux
*/
    public boolean joueMot(Plateau p, MEE s, int[] nbPointsJet) {
        boolean resultat;
        Mot motIn = new Mot();
        motIn.ask(); // CAPELODico au boulot!
        String mot= motIn.getMot(); 
        System.out.println("Veuillez Saisir le numéro de ligne de placement souhaité: ");
        int numLig = Ut.saisirEntier()-1;
        System.out.println("Maintenant, le numéro de Colonne de placement souhaité: ");
        int numCol = Ut.saisirEntier()-1;
        System.out.println("Et enfin, le sens de placement, v pour vertical, h pour horizontal");
        char sens = Ut.saisirCaractere();
        if (p.placementValide(mot, numLig, numCol, sens, this.chevalet)){
            joueMotAux(p, s, nbPointsJet, mot, numLig, numCol, sens);
            resultat= true;
        }
        else{resultat=false;}
        return resultat;
    }
/** pré-requis : cf. joueMot et le placement de mot à partir de la case
* (numLig, numCol) dans le sens donné par sens est valide
* action : simule le placement d’un mot de this
*/
//Les jetons sont placés sur le plateau, leurs exemplaires retirés du chevalet
//Le score est calculé en multipliant score lettre*code couleur

    public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot,
    int numLig, int numCol, char sens) {
        p.place(mot, numLig, numCol, sens, this.chevalet);
        if(this.chevalet.getCardinal()<7){
            this.prendJetons(s, 7-this.chevalet.getCardinal());
        }
        this.score+= p.nbPointsPlacement(mot, numLig, numCol, sens, nbPointsJet);
        if(mot.length()==7){
            this.score+=50;
        }
    }



        










        /*    System.out.println("Saissisez le nombre de jetons à échanger :");
            int nbJetons = Ut.saisirEntier();
            if(nbJetons<=s.getCardinal()){
                this.prendJetons(s, nbJetons);
                resultat=0;




            Mot mot = new Mot();
            mot.ask();
            String motConforme= mot.getMot();
            
            p.place(motConforme, numLig, numCol, sens, e)*/


        
    
}   








