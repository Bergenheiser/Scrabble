public class MainScrabble {
    public static void main(String[] args) {

        Ut.afficher("Saissisez le nombre de joeurs (2 à 4): ");
        int nbJoueurs = Ut.saisirEntier();
        while (nbJoueurs < 2 || nbJoueurs > 4) {
            nbJoueurs = Ut.saisirEntier();
        }
        String[] listeJoueurs = new String[nbJoueurs];
        for (int i = 0; i < nbJoueurs; i++) {
            Ut.afficher("Nom J" + (i + 1) + " :");
            listeJoueurs[i] = Ut.saisirChaine();
        }

        listeJoueurs=listeJoueursRanges(listeJoueurs);//ici on retrouve l'appel de l'extension concernant l'ordre tiré aléatoirement des joueurs
        Scrabble tastyScrabble = new Scrabble(listeJoueurs);
        tastyScrabble.partie();

        // TO DO LIST :

        // A faire :
        // Empecher de placer un mot vide
        // Empecher de choisir numLig ou numCol invalide au placement du mot.

    }
    /*voici la fonction qui pemet de tirer aléatoirement l'ordre des joueurs. Si ils tirent une lettre en début de l'alphabet ils commencent.
    L'ordre se poursuit ensuite de la même facon*/

    public static String[] listeJoueursRanges(String[] listeJoueurs) { 
        int[] listeTemoin = new int[listeJoueurs.length];
        int j;
        int stock;
        int[] sacStandard = { 9, 2, 2, 3, 15, 2, 2, 2, 8, 1, 1, 5, 3, 6, 6, 2, 1, 6, 6, 6, 6, 2, 1, 1, 1, 1 };
        boolean test = true;
        String joueur;

        for (int i = 0; i < listeTemoin.length; i++){
            retirage(i, listeTemoin, sacStandard);
        }

        for (int i = 0; i < listeTemoin.length; i++) {

            j=i;

            while (j > 0 && test) {
                
                if (listeTemoin[j - 1] > listeTemoin[j]) {

                    stock = listeTemoin[j - 1];
                    listeTemoin[j - 1] = listeTemoin[j];
                    listeTemoin[j] = stock;

                    joueur = listeJoueurs[j - 1];
                    listeJoueurs[j - 1] = listeJoueurs[j];
                    listeJoueurs[j] = joueur;

                } else {
                    test = false;
                }
            }
        }

        return listeJoueurs;
    }
    
    /*Cette fonction permet de continuer a tiré jusqu'a ce que tout le monde est une lettre différente.*/

    public static int[] retirage(int i, int[] listeTemoin, int[] sac) {
        listeTemoin[i] = Ut.randomMinMax(1, 100);
        int k = 0, cardinal = sac[k];

        while (listeTemoin[i] > cardinal) {
            k++;
            cardinal = cardinal + sac[k];
        }
        int j = i - 1;
        boolean test = true;
        while (j >= 0) {
            if (listeTemoin[i] == listeTemoin[j]) {
                retirage(j, listeTemoin,sac);
                test = false;
            }
            j--;
        }
        if (test == false) {
            retirage(i, listeTemoin,sac);
        }
        return listeTemoin;
    }
}
