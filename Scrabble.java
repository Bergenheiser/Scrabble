public class Scrabble {
    private int[] nbPointsJet = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 10, 1, 2, 1, 1, 3, 8, 1, 1, 1, 1, 4, 10, 10,
            10, 10 };
    private Joueur[] joueurs;
    private int numJoueur; // joueur courant (entre 0 et joueurs.length-1)
    private Plateau plateau;
    private MEE sac;

    public Scrabble(String[] listeJoueurs) {
        int[] sacStandard = { 9, 2, 2, 3, 15, 2, 2, 2, 8, 1, 1, 5, 3, 6, 6, 2, 1, 6, 6, 6, 6, 2, 1, 1, 1, 1 };
        this.plateau = new Plateau();
        this.sac = new MEE(sacStandard);
        this.joueurs = new Joueur[listeJoueurs.length];
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
        // Distribution initiale des jetons
        for (int i = 0; i < joueurs.length; i++) {
            while(joueurs[i].chevalet.getCardinal())
            joueurs[i].prendJetons(this.sac, 7);
        }


        // Début de la partie et itération des tours
        while (!conditionArret) {
            Ut.afficher(this.toString());
            int retourAction = joueurs[this.numJoueur].joue(this.plateau, this.sac, this.nbPointsJet);
            // La méthode
            // joue, gère
            // aussi
            // l'incrémentation
            // du score d'un
            // joueur après
            // chaque
            // placementValide.
            if (retourAction == -1) {
                // Le joueur à passé son tour
                passeMain++;

                if (passeMain == joueurs.length) {
                    conditionArret = true;
                    // Tous les joueurs ont passés leur tours, je leur retire du score les points
                    // des jetons restant sur le chevalet.
                    for (int k = 0; k < joueurs.length; k++) {
                        int pointsRestant = joueurs[k].nbPointsChevalet(nbPointsJet);
                        joueurs[k].ajouteScore(-(pointsRestant));
                    }
                }
            }
            if (retourAction == 1) {
                // le remplissage du chevalet était impossible après placement des jetons; le
                // sac est maintenant vide, ce joueur à gagné et récupère les points des jetons
                // restant sur le chevalet des autres joueurs.
                conditionArret = true;
                int pointsRestantJ = 0;
                for (int j = 0; j < joueurs.length; j++) {
                    // nbPointsChevalet vérifie bien que si le chevalet est vide, le score renvoyé
                    // est 0.
                    pointsRestantJ += joueurs[j].nbPointsChevalet(nbPointsJet);
                }
                joueurs[this.numJoueur].ajouteScore(pointsRestantJ);

            }
            if (this.numJoueur == joueurs.length - 1 && !conditionArret) {
                // Je réinitialise le joueur courant au premier joueur de la liste pour passer
                // au tour suivant.
                passeMain = 0;
                this.numJoueur = 0;
            } else {
                if (!conditionArret) {
                    this.numJoueur++;
                }
            }
        }

        System.out.println(this.vainqueur());

    }

    /**
     * Méthode d'affichage du/des vainqueur(s) (en cas d'ex-aequo)
     * retourne (nom et score)
     */
    public String vainqueur() {

        int[] vainqueurs = new int[joueurs.length];
        int idVainqueur = 0;
        String reponse = "";
        for (int i = 1; i < (joueurs.length); i++) {
            if (joueurs[idVainqueur].getScore() == joueurs[i].getScore()) {
                vainqueurs[i] = i;
            } else {
                if (joueurs[idVainqueur].getScore() < joueurs[i].getScore()) {
                    idVainqueur = i;
                }
            }
        }

        if (idVainqueur == vainqueurs[1] && vainqueurs[1] != 0) { 
            // Ex-aequo 1ere place enregistré toujours valable
            // après parcours du score
            // de tous les joueurs.
            // Son ou ses informations extraites.
            // Cette méthode couvre le cas (encore plus rare) d'un ex-aequo entre plus de 2
            // joueurs.
            reponse += "Ex aequo entre :" + '\n' + joueurs[idVainqueur].toString() + " et ";
            for (int i = 1; i < vainqueurs.length; i++) {
                if (vainqueurs[i] != 0) {
                    reponse += joueurs[vainqueurs[i]].toString() + ", ";
                }
            }
        } else {
            reponse += "Le vainqueur est :" +'\n'+ joueurs[idVainqueur].toString();
        }
        return reponse;
    }

    // La méthode toString permet d’afficher à chaque tour de jeu l’état du plateau
    // et le joueur qui la main.
    public String toString() {
        return (this.plateau.toString() + '\n' + joueurs[this.numJoueur].toString());
    }

}
