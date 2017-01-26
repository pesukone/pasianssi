package fi.jussi.pasianssi.logiikka;

import fi.jussi.pasianssi.kortit.Kortti;
import fi.jussi.pasianssi.kortit.Korttipakka;
import fi.jussi.pasianssi.kortit.Maa;

public class Pakantayttaja {
    
    public static void tayta52KortinPakka(Korttipakka pakka) {
        luoMaanKortit(pakka, Maa.HERTTA);
        luoMaanKortit(pakka, Maa.PATA);
        luoMaanKortit(pakka, Maa.RISTI);
        luoMaanKortit(pakka, Maa.RUUTU);
    }
    
    private static void luoMaanKortit(Korttipakka pakka, Maa maa) {
        for (int i = 1; i <= 13; i++) {
            Kortti kortti = new Kortti(maa, i);
            pakka.lisaaKortti(kortti);
        }
    }
}
