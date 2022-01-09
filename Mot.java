public class Mot {

    private String submit;

    public Mot (String unMot){
        submit = unMot;
    }

    public Mot(){
        submit="";
    }

    /*
     * Input du mot choisis, avec verification des normes de lettres majuscules.
     */
    public void ask() {
        String input = Ut.saisirChaine();
        int i = 0;
        boolean standard2 = true;
        boolean standard = capeloDico(input);
        while (standard2 && i < input.length()) {
            standard2 = Ut.estUneMajuscule(input.charAt(i));
            i++;
        }
        if (standard && standard2) {
            System.out.println("Mot enregistré avec succès!");
            this.submit = input;
        } else {
            System.out.println("Veuillez saisir un mot valide! (dans le dictionnaire et en toutes majuscules): ");
            ask();
        }
    }

    /**
     * 
     * @param in mot à faire valider par CapeloDico
     * @return réponse de CapeloDico sur le mot in
     */
    public boolean capeloDico(String in) {
        boolean reponse;
        System.out.print(in);
        System.out.println('\n' + "Mot valide (Dans le dictionnaire)? V ou F: ");
        char retour = Ut.saisirCaractere();
        switch (retour) {
            case 'V':
                reponse = true;
                break;
            default:
                reponse = false;
        }
        return (reponse);

    }

    /**
     * Getter pour notre instance de Mot
     * 
     * @return mot
     */
    public String getMot() {
        return this.submit;
    }
}
