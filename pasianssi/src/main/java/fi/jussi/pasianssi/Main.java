package fi.jussi.pasianssi;

import fi.jussi.pasianssi.logiikka.*;
import fi.jussi.pasianssi.kortit.*;

public class Main {
    
    public static void main(String[] args) {
        NakyvaKortti kortti = new NakyvaKortti(new Kortti(Maa.HERTTA, 3));
        System.out.println(kortti.getSeuraava());
    }
}
