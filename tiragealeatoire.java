    public int tirageAlea (MEE [] sacJetons){
        int [] frequeceJeton = sacJetons.getTabFreq;
        int i = 0, jeton, nbJeton = frequeceJeton [0];
        jeton = Ut.randomMinMax (1,100);
        while (jeton>frequeceJeton){
            i++;
            nbJeton += frequeceJeton[i];
        }
        return i;
    }
