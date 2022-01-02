public class Joueur {
    private String nom;
    private MEE chevalet;
    private int score;

    public Joueur(String unNom) {
        this.nom = unNom;

    }

    public String toString() {
        return ("Joueur: " + this.nom + '\n' + "Score: " + this.score+'\n');
    }

    public int getScore() {
        return this.score;
    }

    public void ajouteScore(int nb) {
        this.score += nb;
    }

    /**
     * pré-requis : nbPointsJet indique le nombre de points rapportés par
     * chaque jeton/lettre
     * résultat : le nombre de points total sur le chevalet de ce joueur
     * suggestion : bien relire la classe MEE !
     */
    public int nbPointsChevalet(int[] nbPointsJet) {
        int scoreChevalet;
        if (this.chevalet.estVide()) {
            scoreChevalet = 0;
        } else {
            scoreChevalet = this.chevalet.sommeValeurs(nbPointsJet);
        }
        return (scoreChevalet);
    }

    /**
     * pré-requis : les éléments de s sont inférieurs à 26
     * action : simule la prise de nbJetons jetons par this dans le sac s,
     * dans la limite de son contenu.
     */
    public void prendJetons(MEE s, int nbJetons) {

        System.out.println(s.transfereAleat(this.chevalet, nbJetons));
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
        char userInput = Ut.saisirCaractere();
        switch (userInput) {
            case 'N':
                // Revenir dessus pour la structure du tour passé
                resultat = -1;
                break;
            case 'E':
                this.echangeJetons(s);
                resultat = 0;
                break;
            case 'P':
                joueMot(p, s, nbPointsJet);
                if (this.chevalet.estVide()) {
                    resultat = 1;
                } else {
                    resultat = 0;
                }
                break;
            default:
                System.out.println("Merci de ressaisir un choix conforme.");
                joue(p, s, nbPointsJet);
        }

        return resultat;
    }

    /**
     * pré-requis : les éléments de s sont inférieurs à 26
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
        String mot = motIn.getMot();
        System.out.println("Veuillez Saisir le numéro de ligne de placement souhaité: ");
        int numLig = Ut.saisirEntier() - 1;
        System.out.println("Maintenant, le numéro de Colonne de placement souhaité: ");
        int numCol = Ut.saisirEntier() - 1;
        System.out.println("Et enfin, le sens de placement, v pour vertical, h pour horizontal");
        char sens = Ut.saisirCaractere();
        if (p.placementValide(mot, numLig, numCol, sens, this.chevalet)) {
            joueMotAux(p, s, nbPointsJet, mot, numLig, numCol, sens);
            resultat = true;
        } else {
            resultat = false;
        }
        return resultat;
    }

    /**
     * pré-requis : cf. joueMot et le placement de mot à partir de la case
     * (numLig, numCol) dans le sens donné par sens est valide
     * action : simule le placement d’un mot de this
     * 
     * //Les jetons sont placés sur le plateau, leurs exemplaires retirés du
     * chevalet
     * //Le score est calculé en multipliant score lettre*code couleur
     */

    public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot,
            int numLig, int numCol, char sens) {
        p.place(mot, numLig, numCol, sens, this.chevalet);
        if (this.chevalet.getCardinal() < 7) {
            this.prendJetons(s, 7 - this.chevalet.getCardinal());
        }
        this.ajouteScore(p.nbPointsPlacement(mot, numLig, numCol, sens, nbPointsJet));
        if (mot.length() == 7) {
            this.ajouteScore(50);
        }
    }

    /**
     * pré-requis : sac peut contenir des entiers de 0 à 25
     * action : simule l’échange de jetons de ce joueur :
     * - saisie de la suite de lettres du chevalet à échanger
     * en vérifiant que la suite soit correcte
     * - échange de jetons entre le chevalet du joueur et le sac
     * stratégie : appelle les méthodes estCorrectPourEchange et echangeJetonsAux
     */
    public void echangeJetons(MEE sac) {
        String mot = Ut.saisirChaine();
        if (this.estCorrectPourEchange(mot)) {
            echangeJetonsAux(sac, mot);
        }
    }

    /**
     * résultat : vrai ssi les caractères de mot correspondent tous à des
     * lettres majuscules et l’ensemble de ces caractères est un
     * sous-ensemble des jetons du chevalet de this
     */
    public boolean estCorrectPourEchange(String mot) {
        boolean resultat = true;
        int i = 0;
        while (resultat && i < mot.length() && Ut.estUneMajuscule(mot.charAt(i))) {
            if (this.chevalet.contientChar(mot.charAt(i))) {
                i++;
            } else {
                resultat = false;
            }
        }
        return resultat;
    }

    /**
     * pré-requis : sac peut contenir des entiers de 0 à 25 et
     * ensJetons est un ensemble de jetons correct pour l’échange
     * action : simule l’échange de jetons de ensJetons avec des
     * jetons du sac tirés aléatoirement.
     */
    public void echangeJetonsAux(MEE sac, String ensJetons) {
        for (int i = 0; i < ensJetons.length(); i++) {
            int index = Ut.majToIndex(ensJetons.charAt(i));
            // transfert d'un jeton aléatoire du sac vers le chevalet
            sac.transfereAleat(this.chevalet, 1);
            // transfert spécifique du jeton du chevalet vers le sac
            // Cet ordre à été choisi pour ne pas accidentellement retirer du sac la lettre
            // qu'on souhaite avoir échangé pour une autre
            this.chevalet.transfere(sac, index);
        }

    }

}
