public class Scrabble {
    private int[] nbPointsJet = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 10, 1, 2, 1, 1, 3, 8, 1, 1, 1, 1, 4, 10, 10,
            10, 10 };
    private Joueur[] joueurs;
    private int numJoueur; // joueur courant (entre 0 et joueurs.length-1) //Je sais pas à quoi il sert
    private Plateau plateau;
    private MEE sac;

    public Scrabble(String[] listeJoueurs) {
        int[] sacStandard = { 9, 2, 2, 3, 15, 2, 2, 2, 8, 1, 1, 5, 3, 6, 6, 2, 1, 6, 6, 6, 6, 2, 1, 1, 1, 1 };
        this.plateau = new Plateau();
        this.sac = new MEE(sacStandard);
        for (int i = 0; i < listeJoueurs.length; i++) {
            this.joueurs[i] = new Joueur(listeJoueurs[i]);
            this.numJoueur = i;
        }

    }

    /**
     * 1. la distribution initiale des jetons aux joueurs,
     * 2. des itérations sur les différents tours de jeu jusqu’à la fin de la
     * partie,
     * 3. le calcul des scores finaux,
     * 4. l’affichage du ou des gagnants.
     */
    public void partie() {
        boolean conditionArret = false;
        int passeMain = 0;
        this.numJoueur = Ut.randomMinMax(0, joueurs.length - 1);
        while (!conditionArret) {
            this.toString();
            int retourAction = joueurs[this.numJoueur].joue(this.plateau, this.sac, this.nbPointsJet);
            
            if (retourAction == -1) {
                //Le joueur à passé son tour
                passeMain++;
                this.numJoueur++;
            } else {
                if (retourAction == 1) {
                    //le remplissage du chevalet était impossible après placement des jetons; le sac est maintenant vide.
                    conditionArret = true;

                } else {
                    this.numJoueur++;
                }

                if (!conditionArret && this.numJoueur == joueurs.length - 1) {
                    if (passeMain == joueurs.length - 1) {
                        conditionArret = true;
                    } else {
                        //Je réinitialise le joueur courant au premier joueur de la liste pour passer au tour suivant.
                        passeMain = 0;
                        this.numJoueur = 0;
                    }

                }

            }
        }
    }

    // La méthode toString permet d’afficher à chaque tour de jeu l’état du plateau
    // et le joueur qui la main.
    public String toString() {
        return (this.plateau.toString()+'\n'+"Le Joueur: "+joueurs[this.numJoueur]+" à la main!");
    }

}
