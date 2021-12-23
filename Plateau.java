public class Plateau {

    private Case[][] g = new Case[15][15];

    /**
     * Initialisateur du plateau, à partir de la matrice standard selon les règles
     * du Scrabble
     */
    public Plateau() {
        int[][] plateau = { { 5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5 },
                { 1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1 },
                { 1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1 },
                { 2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2 },
                { 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1 },
                { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
                { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
                { 5, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 5 },
                { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
                { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
                { 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1 },
                { 2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2 },
                { 1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1 },
                { 1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1 },
                { 5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5 } };

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                this.g[i][j] = new Case(plateau[i][j]);
            }
        }
    }

    /**
     * Constructeur de plateau à partir d'une grille passée en paramètre
     * 
     * @param inputUn
     */
    public Plateau(Case[][] plateau) {
        this.g = plateau;
    }

    public String toString(){
        String plateauCourant ="   |1 |2 |3 |4 |5 |6 |7 |8 |9 |10|11|12|13|14|15|"+'\n'+"-".repeat(48)+"\n";
        for(int i=0;i<g.length;i++){
            if(i<10){
            plateauCourant+=(" "+i+" |");}
            else{plateauCourant+=(i+" |");}

            for(int j=0;j<g[0].length;j++){
                    if(g[i][j].getCouleur()==1){
                    plateauCourant+="  |";
                    }
                    else{
                    plateauCourant+=g[i][j].toString()+" |";}
                }
        plateauCourant+='\n'+"-".repeat(48)+'\n';
        
        }
        return(plateauCourant);
    }

    public static void main(String[] args) {
        Plateau plateau1 = new Plateau();
        System.out.println(plateau1.toString());
    }
}