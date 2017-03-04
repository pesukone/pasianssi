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
	 * Metodi alustaa tavallisen 52 kortin korttipakan. Parametrina
	 * annetun pakan oletetaan olevan tyhjä.
	 * @param pakka	alustettava pakka
	 */
	public static void alustaPeruskorttipakka(Korttipakka pakka) {
		luoMaanKortit(pakka, Maa.HERTTA);
		luoMaanKortit(pakka, Maa.PATA);
		luoMaanKortit(pakka, Maa.RISTI);
		luoMaanKortit(pakka, Maa.RUUTU);
	}
    
	/**
	 * Metodi alustaa 104 kortin pakan, jota Spider-pasianssissa
	 * käytetään. Parametrina annetun pakan oletetaan olevan tyhjä.
	 * @param pakka	alustettava pakka
	 */
	public static void alustaTuplakorttipakka(Korttipakka pakka) {
		alustaPeruskorttipakka(pakka);
		alustaPeruskorttipakka(pakka);
	}
	
	/**
	 * Metodi alustaa kahden maan pasianssin pakan. Parametrina annettavaan
	 * pakkaan lisätään 52 pataa ja 52 herttaa.
	 * @param pakka  alustettava pakka
	 */
	public static void alustaKahdenMaanPakka(Korttipakka pakka) {
		for (int i = 0; i < 4; i++) {
			luoMaanKortit(pakka, Maa.HERTTA);
			luoMaanKortit(pakka, Maa.PATA);
		}
	}
	
	/**
	 * Metodi alustaa yhden maan pasianssin pakan. Parametrina annettava pakka
	 * täytetään padoilla.
	 * @param pakka alustettava pakka
	 */
	public static void alustaYhdenMaanPakka(Korttipakka pakka) {
		for (int i = 0; i < 8; i++) {
			luoMaanKortit(pakka, Maa.PATA);
		}
	}
    
	private static void luoMaanKortit(Korttipakka pakka, Maa maa) {
		for (int i = 1; i <= 13; i++) {
			Kortti kortti = new Kortti(maa, i);
			pakka.lisaaKortti(kortti);
		}
	}
}
