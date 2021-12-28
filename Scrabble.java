public class Scrabble {
    private static int [] nbPointsJeton = {1,3,3,2,1,4,2,4,1,8,10,1,2,1,1,3,8,1,1,1,1,4,10,10,10,10};
    private Joueur[] joueurs;
    private int numJoueur; // joueur courant (entre 0 et joueurs.length-1)
    private Plateau plateau;
    private MEE sac;
    
    public Scrabble(String[] listeJoueurs){
        int[] sacStandard = {9,2,2,3,15,2,2,2,8,1,1,5,3,6,6,2,1,6,6,6,6,2,1,1,1,1};
        this.plateau= new Plateau();
        this.sac = new MEE(sacStandard);
        for(int i=0; i<listeJoueurs.length; i++ ){
            this.joueurs[i]= new Joueur(listeJoueurs[i]);
            this.numJoueur=i;
        }

    }
    //La méthode toString permet d’afficher à chaque tour de jeu l’état du plateau et le joueur qui la main.
    public String toString(){}





}
