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
     *
     */
    public Plateau(Case[][] plateau) {
        this.g = plateau;
    }

    public String tostring() {
        String plateauCourant = "   |01 |02 |03 |04 |05 |06 |07 |08 |09 |10 |11 |12 |13 |14 |15 |" + '\n'
                + "-".repeat(64) + "\n";
        char col = 'A';
        for (int i = 0; i < g.length; i++) {
            plateauCourant += (" " + col + " |");
            for (int j = 0; j < g[0].length; j++) {
                if (g[i][j].getCouleur() == 1) {
                    plateauCourant += "   |";
                } else {
                    plateauCourant += " " + g[i][j].toString() + " |";
                }
            }

            plateauCourant += '\n' + "-".repeat(64) + '\n';
            col++;
        }
        return (plateauCourant);
    }

    public boolean dansChevalet(String mot, MEE e) {
        int[] chevalet = e.getTabFreq();
        boolean result = true;
        int index = 0;
        while (result && index < mot.length()) {
            // Si la lettre du mot saisi représentée par son indice dans le tabFreq du
            // chevalet à au moins 1 exemplaire
            if (chevalet[Ut.majToIndex(mot.charAt(index))] != 0) {
                index++;
            } else {
                result = false;
            }
        }
        return result;
    }
    // REVOIR LA CONDITION DES JETONS DANS CHEVALETS MAIS NON DEJA PRESENTS SUR LE
    // PLATEAU

    /*
     * Méthode de Classe permettant de placer un mot sur le plateau
     * 
     * @return Vrai ssi le placement de mot sur this à partir de la
     * case(numLig,numCol)
     * dans le sens à partir des jetons de e est valide.
     */
    public boolean placementValide(String mot, int numLig, int numCol, char sens, MEE e) {
        boolean result = false;
        int endZone; // Cordonnée X ou Y selon le sens de la dernière case de zone
        Case casePrecedenteZone;
        Case caseSuivanteZone;
        switch (sens) {
            case 'v':
                endZone = numLig + mot.length() - 1;
                if (endZone + 1 > 14) {
                    caseSuivanteZone = null;
                } else {
                    caseSuivanteZone = g[endZone + 1][numCol];
                }
                if (endZone - 1 < 0) {
                    casePrecedenteZone = null;
                } else {
                    casePrecedenteZone = g[endZone - 1][numCol];
                }
                break;

            // Sur un mot vertical les Coordonées X (numCol) de ses lettres sont identiques.

            case 'h':
                // X de la dernière lettre du mot proposé
                endZone = numCol + mot.length() - 1;
                if (endZone + 1 > 14) {
                    caseSuivanteZone = null;
                } else {
                    caseSuivanteZone = g[numLig][numCol + 1];
                }
                if (endZone - 1 < 0) {
                    casePrecedenteZone = null;
                } else {
                    casePrecedenteZone = g[numLig][numCol - 1];
                }
                break;
            default:
                throw new IllegalStateException("Sens incorrect" + sens);
            // Sur un mot horizontal, toutes les lettre partagent le même Y (numLig)
        }
        // Premier placement
        if (!this.g[7][7].estRecouverte() && mot.length() >= 2 && dansChevalet(mot, e) && endZone >= 7) {
            result = true;
        } 
        else {
            // Placement le reste du jeu
            if (this.g[7][7].estRecouverte() && result == false && endZone <= 14 && endZone >= 0
                    && (casePrecedenteZone == null || !casePrecedenteZone.estRecouverte())
                    && (caseSuivanteZone == null || !caseSuivanteZone.estRecouverte())
                    && dansChevalet(mot, e)) {
                boolean conditioncasevide = false;
                int nbcasevide = 0;
                boolean conditioncaseremplie = false;
                int nbcaseremplie = 0;
                boolean contrainteIntegrite = false;
                int indexlettreobservée = 0;
                switch (sens) {
                    case 'v':
                        for (int i = numLig; i <= endZone; i++) {
                            if (g[i][numCol].estRecouverte()
                                    && g[i][numCol].getLettre() == mot.charAt(indexlettreobservée)) {
                                nbcaseremplie++;
                                indexlettreobservée++;
                            } else {
                                nbcasevide++;
                            }
                        }
                        break;

                    case 'h':
                        for (int j = numCol; j <= endZone; j++) {
                            if (g[numLig][j].estRecouverte()
                                    && g[numLig][j].getLettre() == mot.charAt(indexlettreobservée)) {
                                nbcaseremplie++;
                                indexlettreobservée++;
                            } else {
                                nbcasevide++;
                            }
                        }

                }
                if (nbcaseremplie > 0 && nbcasevide > 0 ) {
                    conditioncaseremplie = true;
                    contrainteIntegrite = true;
                    conditioncasevide = true;

                }
                if (contrainteIntegrite && conditioncasevide && conditioncaseremplie) {
                    result = true;
                }
            } else{result=false;}
        }
        return result;
    }
}
