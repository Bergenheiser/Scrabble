public class Plateau {
    
    private Case[][] g = new Case [15][15];

    public Plateau(){
        int[][] plateau = {{5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5},
                           {1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1},
                           {1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1},
                           {2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2},
                           {1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1},
                           {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
                           {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                           {5, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 5},
                           {1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1},
                           {1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1},
                           {1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1},
                           {2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2},
                           {1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1},
                           {1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1},
                           {5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5}};
        
            for(int i=0; i<plateau.length;i++){
            for(int j=0; j<plateau[0].length; j++){
               this.g[i][j] = g[i][j].Case(plateau[i][j]);
            }}
        
        }
    
    /**
     * Constructeur de plateau à partir d'une grille passée en paramètre
     * @param inputUn
     */
    public Plateau (Case[][] plateau) {
        this.g = plateau;
        }
    
    
    public String toString(){
        
    }

}