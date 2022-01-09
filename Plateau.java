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

    public String toString() {
        String plateauCourant = "    |01 |02 |03 |04 |05 |06 |07 |08 |09 |10 |11 |12 |13 |14 |15 |" + '\n'
                + "-".repeat(64) + "\n";
        String[] col = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15" };
        for (int i = 0; i < g.length; i++) {
            plateauCourant += (" " + col[i] + " |");
            for (int j = 0; j < g[0].length; j++) {
                if (g[i][j].getCouleur() == 1 && !g[i][j].estRecouverte()) {
                    plateauCourant += "   |";
                } else {
                    plateauCourant += " " + g[i][j].toString() + " |";
                }
            }

            plateauCourant += '\n' + "-".repeat(64) + '\n';
        }
        return (plateauCourant);
    }

    public boolean dansChevalet(String mot, MEE e) {
        int[] chevalet = e.copie();
        boolean result = true;
        int position = 0;
        while (result && position < mot.length()) {
            // Si la lettre du mot saisi représentée par son indice dans le tabFreq du
            // chevalet à au moins 1 exemplaire
            if (chevalet[Ut.majToIndex(mot.charAt(position))] != 0) {
                // Je soustrais la lettre observée afin de pouvoir revérifier la condition en
                // cas de 2 lettres indentiques.
                chevalet[Ut.majToIndex(mot.charAt(position))] += -1;
                position++;
            } else {
                result = false;
            }
        }
        return result;
    }


    /* extension de mot qui touche ici on vérifie que les mots qui sont créé sont bien valide.*/
    public boolean touche(String mot, int numLig, int numCol, char sens) {
        int ligne = numLig, i = 0;
        ;
        int colonne = numCol;
        String nvmot;
        boolean test = true;
        Mot nouveau;

        while (i < mot.length() && test == true) {

            nvmot = "";

            if (sens == 'v') {

                while (colonne >= 0 && this.g[ligne][colonne].estRecouverte()) {
                    nvmot = this.g[ligne][colonne].getLettre() + nvmot;
                    colonne = colonne - 1;
                }

                colonne = numCol + 1;

                while (colonne < this.g[ligne].length && this.g[ligne][colonne].estRecouverte()) {
                    nvmot = nvmot + (this.g[ligne][colonne].getLettre());
                    colonne = colonne + 1;
                }

                nouveau = new Mot(nvmot);

                if (nvmot != "" && nouveau.capeloDico(nvmot)) {
                    test = false;
                }

            } else {

                while (ligne >= 0 && this.g[ligne][colonne].estRecouverte()) {
                    nvmot = this.g[ligne][colonne].getLettre() + nvmot;
                    ligne = ligne - 1;
                }

                ligne = numLig + 1;

                while (ligne < this.g.length && this.g[ligne][colonne].estRecouverte()) {
                    nvmot = nvmot + this.g[ligne][colonne].getLettre();
                    ligne = ligne + 1;
                }

                nouveau = new Mot(nvmot);

                if (nvmot != "" && nouveau.capeloDico(nvmot)) {
                    test = false;
                }

            }
            i++;
        }
        return test;
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
        // conversion Entier Naturel Relatif aux coordonnées d'un plan.

        boolean result = false;
        int endZone; // Cordonnée X ou Y (selon le sens) de la dernière case de zone
        Case casePrecedenteZone;
        Case caseSuivanteZone;
        boolean conditionCaseVide = false;
        int nbCaseVide = 0;
        boolean conditionCaseRemplie = false;
        int nbcaseRemplie = 0;
        boolean contrainteIntegrite = true;
        int indexlettreobs = 0;
        boolean conditionCaseCentrale = false;
        int caseCentralePresente = 0;
        // Nous allons devoir déterminer les coordonnées de la fin du mot à partir de la
        // prochaine case non recouverte ou nulle (bord de plateau)
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
                    casePrecedenteZone = g[numLig - 1][numCol];
                }

                if (endZone <= 14) {
                    for (int i = numLig; i <= endZone; i++) {
                        if (g[i][numCol].estRecouverte()) {
                            nbcaseRemplie++;
                            if (g[i][numCol].getLettre() != mot.charAt(indexlettreobs)) {
                                contrainteIntegrite = false;
                            }
                        } else {
                            nbCaseVide++;
                        }
                        if (g[i][numCol] == g[7][7]) {
                            caseCentralePresente++;
                        }
                        indexlettreobs++;
                    }
                } else {
                    System.out.println("---Débordement de plateau---");
                }

                // Retour utilisateur, préférable à indexOutOfBound qui pourrait parraître être
                // un problème de la méthode et pas de l'input.
                break;

            // Sur un mot vertical les Coordonées Y (numCol) de ses lettres sont identiques.

            case 'h':
                // X de la dernière lettre du mot proposé
                endZone = numCol + mot.length() - 1;
                if (endZone + 1 > 14) {
                    caseSuivanteZone = null;
                } else {
                    caseSuivanteZone = g[numLig][endZone + 1];
                }
                if (endZone - 1 < 0) {
                    casePrecedenteZone = null;
                } else {
                    casePrecedenteZone = g[numLig][numCol - 1];
                }
                if (endZone <= 14) {
                    for (int i = numCol; i <= endZone; i++) {
                        if (g[numLig][i].estRecouverte()) {
                            nbcaseRemplie++;
                            if (g[numLig][i].getLettre() != mot.charAt(indexlettreobs)) {
                                contrainteIntegrite = false;
                            }
                        } else {
                            nbCaseVide++;
                        }
                        if (g[numLig][i] == g[7][7]) {
                            caseCentralePresente++;
                        }
                        indexlettreobs++;
                    }
                } else {
                    System.out.println("---Débordement de plateau---");
                }
                break;
            default:
                throw new IllegalStateException("Sens incorrect" + sens);
        }

        conditionCaseCentrale = (caseCentralePresente > 0);
        conditionCaseRemplie = (nbcaseRemplie > 0);
        conditionCaseVide = (nbCaseVide > 0);
       
        // Premier placement
        if (!this.g[7][7].estRecouverte() && mot.length() >= 2 && dansChevalet(mot, e) && conditionCaseCentrale) {
            result = true;
        } else {
            // Placement le reste du jeu
            if (this.g[7][7].estRecouverte()
                    && !result
                    && (casePrecedenteZone == null || !casePrecedenteZone.estRecouverte())
                    && (caseSuivanteZone == null || !caseSuivanteZone.estRecouverte())
                    && dansChevalet(mot, e) && contrainteIntegrite && conditionCaseVide && conditionCaseRemplie) {
                result = true;
            } else {
                result = false;
            }
        }

        if (touche(mot, numLig, numCol, sens) == false) {
            result = false;
        }

        return result;
    }

    /* extension de mot qui touche. Ici, on compte les points des mots qui ont été précédemment vérifié*/
    
    public int touchePoint(String mot, int numLig, int numCol, char sens, int[] nbPointsJet) {
        int ligne = numLig, i = 0;
        ;
        int colonne = numCol;
        String nvmot;
        boolean test = true;
        int endZone, sumPoints = 0;
        int multiplicateurMot = 1;
        int totalPoints = 0;

        while (i < mot.length() && test == true) {

            nvmot = "";
            sumPoints = 0;

            if (sens == 'v') {

                while (colonne >= 0 && this.g[ligne][colonne].estRecouverte()) {
                    nvmot = this.g[ligne][colonne].getLettre() + nvmot;
                    colonne = colonne - 1;
                }

                colonne = numCol + 1;

                while (colonne < this.g[ligne].length && this.g[ligne][colonne].estRecouverte()) {
                    nvmot = nvmot + (this.g[ligne][colonne].getLettre());
                    colonne = colonne + 1;
                }

                endZone = colonne + nvmot.length() - 1;

                for (int p = 0; p < nvmot.length(); p++) {
                    int indexPointsJet = Ut.majToIndex(nvmot.charAt(p));
                    if (g[ligne][colonne].getCouleur() == 4 || g[ligne][colonne].getCouleur() == 5) {
                        switch (g[ligne][colonne].getCouleur()) {
                            case 4:
                                sumPoints += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 2;
                                break;

                            case 5:
                                sumPoints += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 3;
                                break;

                        }

                    } else {
                        sumPoints += nbPointsJet[indexPointsJet] * g[ligne][colonne].getCouleur();
                    }
                    colonne++;

                }

            } else {

                while (ligne >= 0 && this.g[ligne][colonne].estRecouverte()) {
                    nvmot = this.g[ligne][colonne].getLettre() + nvmot;
                    ligne = ligne - 1;
                }

                ligne = numLig + 1;

                while (ligne < this.g.length && this.g[ligne][colonne].estRecouverte()) {
                    nvmot = nvmot + this.g[ligne][colonne].getLettre();
                    ligne = ligne + 1;
                }

                endZone = ligne + nvmot.length() - 1;

                for (int p = 0; p < nvmot.length(); p++) {
                    int indexPointsJet = Ut.majToIndex(nvmot.charAt(p));
                    if (g[ligne][colonne].getCouleur() == 4 || g[ligne][colonne].getCouleur() == 5) { // Mot compte
                                                                                                      // Double Triple
                        switch (g[ligne][colonne].getCouleur()) {
                            case 4:
                                sumPoints += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 2;
                                break;

                            case 5:
                                sumPoints += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 3;
                                break;

                        }

                    } else {
                        sumPoints += nbPointsJet[indexPointsJet] * g[ligne][colonne].getCouleur();
                    }
                    ligne++;
                }

            }

            sumPoints = sumPoints * multiplicateurMot;
            totalPoints = totalPoints + sumPoints;
            i++;
        }
        return totalPoints;
    }

    /**
     * pré-requis : le placement de mot sur this à partir de la case
     * (numLig, numCol) dans le sens donné par sens est valide
     * résultat : retourne le nombre de points rapportés par ce placement,soit le
     * score du mot placé, ou le score de la concatenation du mot placé avec celui
     * déjà présent, le
     * nombre de points de chaque jeton étant donné par le tableau nbPointsJet.
     */
    public int nbPointsPlacement(String mot, int numLig, int numCol, char sens, int[] nbPointsJet) {
        int sumPoints = 0;
        int multiplicateurMot = 1;
        int endZone = 0;
        switch (sens) {
            case 'v':
                endZone = numLig + mot.length() - 1;
                /*
                 * while (g[endZone + 1][numCol].estRecouverte()) {
                 * mot += g[endZone + 1][numCol].getLettre();
                 * endZone++;
                 * }
                 */
                for (int i = 0; i < mot.length(); i++) {
                    int indexPointsJet = Ut.majToIndex(mot.charAt(i));
                    if (g[numLig][numCol].getCouleur() == 4 || g[numLig][numCol].getCouleur() == 5) { // Mot compte
                                                                                                      // Double Triple
                        switch (g[numLig][numCol].getCouleur()) {
                            case 4:
                                sumPoints += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 2;
                                break;

                            case 5:
                                sumPoints += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 3;
                                break;

                        }

                    } else {
                        sumPoints += nbPointsJet[indexPointsJet] * g[numLig][numCol].getCouleur();
                    } // Je multiplie la valeur
                      // score de la lettre par
                      // le code couleur de la
                      // case sous-jacente.
                    numLig++;
                }
                break;
            case 'h':
                endZone = numCol + mot.length() - 1;
                /*
                 * while (g[numLig][endZone+1].estRecouverte()) {
                 * mot += g[numLig][endZone+1].getLettre();
                 * endZone++;
                 * }
                 */
                for (int i = 0; i < mot.length(); i++) {
                    int indexPointsJet = Ut.majToIndex(mot.charAt(i));
                    if (g[numLig][numCol].getCouleur() == 4 || g[numLig][numCol].getCouleur() == 5) {
                        switch (g[numLig][numCol].getCouleur()) {
                            case 4:
                                sumPoints += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 2;
                                break;

                            case 5:
                                sumPoints += nbPointsJet[indexPointsJet] * 1;
                                multiplicateurMot = multiplicateurMot * 3;
                                break;

                        }

                    } else {
                        sumPoints += nbPointsJet[indexPointsJet] * g[numLig][numCol].getCouleur();
                    }
                    numCol++;

                }
                break;
        }
        sumPoints = sumPoints * multiplicateurMot;
        sumPoints=sumPoints+touchePoint(mot, numLig, numCol, sens, nbPointsJet);
        return (sumPoints);
    }

    /**
     * pré-requis : le placement de mot sur this à partir de la case
     * (numLig, numCol) dans le sens donné par sens à l’aide des
     * jetons de e est valide.
     * action/résultat : effectue ce placement et retourne le
     * nombre de jetons retirés de e.
     */
    public int place(String mot, int numLig, int numCol, char sens, MEE e) {
        boolean conditionPlacementValide = placementValide(mot, numLig, numCol, sens, e);
        int resultat = 0;
        if (conditionPlacementValide) {
            switch (sens) {
                case 'v':
                    for (int index = 0; index < mot.length(); index++) {
                        g[numLig][numCol].setLettre(mot.charAt(index));
                        if (e.retire(Ut.majToIndex(mot.charAt(index)))) {
                            resultat++;
                        }
                        numLig++;
                    }
                    break;
                case 'h':
                    for (int index = 0; index < mot.length(); index++) {
                        g[numLig][numCol].setLettre(mot.charAt(index));
                        if (e.retire(Ut.majToIndex(mot.charAt(index)))) {
                            resultat++;
                        }
                        numCol++;
                    }
                    break;
            }
        }
        return resultat;
    }

}
