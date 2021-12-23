public class Mot {

    private String submit;
/**
 * Input du mot choisis, avec verification des 
 * @return mot choisi, standardisé aux normes de lettres majuscules
 */
    public void ask(){
        String input= Ut.saisirChaine();
        int i=0;
        boolean standard= true;
        while(standard && i<input.length()){
            standard= Ut.estUneMajuscule(input.charAt(i));
            i++;
        }
        if(standard){
            System.out.println("Mot enregistré avec succès!");
            this.submit = input;
        }
        else{
            System.out.println("Veuillez resaissir le mot en toutes lettres majuscules: ");
            ask();}
    }
/**
 * Getter pour notre instance de Mot
 * @return mot
 */
   public String getMot() {
    return this.submit;
}
}