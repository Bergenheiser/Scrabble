public class MainScrabble {
    public static void main(String[] args){

        Ut.afficher("Saissisez le nombre de joeurs (2 à 4): ");
        int nbJoueurs = Ut.saisirEntier();
        if(nbJoueurs<2 || nbJoueurs>4){
            nbJoueurs = Ut.saisirEntier();
        }
        String[] listeJoueurs = new String[nbJoueurs];
        for(int i = 0; i < nbJoueurs; i++){
            Ut.afficher("Nom J"+(i+1)+" :");
            listeJoueurs[i]=Ut.saisirChaine();
        }
        Scrabble LeJEUdeMORT = new Scrabble(listeJoueurs);
        LeJEUdeMORT.partie();
        

        }






    }
