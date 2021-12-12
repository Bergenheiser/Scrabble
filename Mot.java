public class Mot {

    private String submit;
/**
 * Je crée un une fonction d'input des variables de lettres, en la forcant
 * à devenir majuscule dans le cas où l'utilisateur n'aurait pas fait attention
 * @return mot choisi
 */
    public void ask(){
        String input= Ut.saisirChaine();
        this.submit= input.toUpperCase();
    }
/**
 * Getter pour notre isntance privée de Mot
 * @return mot
 */
   public String getMot() {
    return this.submit;
}
}