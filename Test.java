public class Test {
    public static void main(String[] args) {
        Plateau test = new Plateau();
        System.out.println(test.tostring());
        MEE chevalet = new MEE(26);
        Mot tester = new Mot();
        tester.ask();

    
        String mot = tester.getMot();
        for (int i = 0; i < mot.length(); i++) {
            int indexalphalettre = Ut.majToIndex(mot.charAt(i));
            chevalet.ajoute(indexalphalettre);

        }
        System.out.println(test.placementValide("BONJOUR",1,8,'v', chevalet));

    }
    
}

