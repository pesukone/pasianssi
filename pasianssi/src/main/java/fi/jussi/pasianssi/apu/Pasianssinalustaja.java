package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Pasianssi;
import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.Korttipakka;

/**
 * Luokka tarjoaa pasianssipelin alustamiseen liittyviä apumetodeja.
 * @see fi.jussi.pasianssi.kortit.Pasianssi
 */
public class Pasianssinalustaja {
	
	public static void alustaPinot(Pasianssi pasianssi) {
		for (int i = 0; i < 10; i++) {
            Korttipino pino = new Korttipino();
            pasianssi.getPinot().add(pino);
        }
	}
	
	public static void alustaPakka(Pasianssi pasianssi) {
		Pakantayttaja.alustaTuplakorttipakka(pasianssi.getPakka());
		pasianssi.getPakka().sekoita();
	}
	
	public static void taytaPinot(Pasianssi pasianssi) {
		Korttipakka pakka = pasianssi.getPakka();
		
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
				pasianssi.getPinot().get(j).lisaaKaannettyKortti(pakka.nosta());
            }
		}
		
		for (int i = 0; i < 4; i++) {
            pasianssi.getPinot().get(i).lisaaKaannettyKortti(pakka.nosta());
		}
		
		for (int i = 0; i < 10; i++) {
            pasianssi.getPinot().get(i).lisaaNakyvaKortti(pakka.nosta());
		}
	}
}
