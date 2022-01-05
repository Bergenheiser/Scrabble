public class MainScrabble {
    public static void main(String[] args){

        Ut.afficher("Saissisez le nombre de joeurs (2 à 4): ");
        int nbJoueurs=Ut.saisirEntier();
        while(nbJoueurs<2 || nbJoueurs>4){
            nbJoueurs=Ut.saisirEntier();
        }
        String[] listeJoueurs = new String[nbJoueurs];
        for(int i = 0; i < nbJoueurs; i++){
            Ut.afficher("Nom J"+(i+1)+" :");
            listeJoueurs[i]=Ut.saisirChaine();
        }
        Scrabble LeJEUdeMORT = new Scrabble(listeJoueurs);
        LeJEUdeMORT.partie();
        
        //TO DO LIST :
        //Soucis placementValide avec conversions de coordonées maintenant ...
        //A faire :
        // Empecher de placer un mot vide
        // Empecher de choisir numLig ou numCol invalide au placement du mot.
        // Comment changer de choix si on s'est trompé (passer de placer à Echanger par exemple)
        //Vérifier les conditions d'arrêt du jeu quand tous le sac est vide.
        //Vérifier ce qu'il se passe quand le sac a moins de jetons que ce que le joeur aimerai piocher
        // 


        }






    }
