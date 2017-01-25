package fi.jussi.pasianssi;

import fi.jussi.pasianssi.kortit.*;

public class Main {
    
    public static void main(String[] args) {
        Kortti kortti = new Kortti(Maa.HERTTA, 10);
        
        System.out.println(kortti);
    }
}
