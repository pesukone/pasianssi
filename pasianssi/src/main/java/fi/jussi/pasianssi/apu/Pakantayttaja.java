package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Kortti;
import fi.jussi.pasianssi.kortit.Korttipakka;
import fi.jussi.pasianssi.kortit.Maa;


/**
 * Luokka tarjoaa korttipakan täyttämiseen liittyviä apumetodeja.
 * @see fi.jussi.pasianssi.kortit.Korttipakka
 */
public class Pakantayttaja {
    
	/**
	 * Metodi, joka alustaa tavallisen 52 kortin korttipakan. Parametrina
	 * annetun pakan oletetaan olevan tyhjä.
	 * @param pakka		alustettava pakka
	 */
    public static void alustaPeruskorttipakka(Korttipakka pakka) {
        luoMaanKortit(pakka, Maa.HERTTA);
        luoMaanKortit(pakka, Maa.PATA);
        luoMaanKortit(pakka, Maa.RISTI);
        luoMaanKortit(pakka, Maa.RUUTU);
    }
    
	/**
	 * Metodi, joka alustaa 104 kortin pakan, jota Spider-pasianssissa
	 * käytetään. Parametrina annetun pakan oletetaan olevan tyhjä.
	 * @param pakka		alustettava pakka
	 */
    public static void alustaTuplakorttipakka(Korttipakka pakka) {
        alustaPeruskorttipakka(pakka);
        alustaPeruskorttipakka(pakka);
    }
    
    private static void luoMaanKortit(Korttipakka pakka, Maa maa) {
        for (int i = 1; i <= 13; i++) {
            Kortti kortti = new Kortti(maa, i);
            pakka.lisaaKortti(kortti);
        }
    }
}
