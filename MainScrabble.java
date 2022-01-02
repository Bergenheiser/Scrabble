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
        //Revoir pourquoi il n'y pas 7 jetons dans le sac à tout moment!!!
        //A faire :
        // Créer CapeloDico
        // Empecher de placer un mot vide
        // Empecher de choisir numLig ou numCol invalide au placement du mot.
        // Comment changer de choix si on s'est trompé (passer de placer à Echanger par exemple)

        // 


        }






    }
