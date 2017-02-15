package fi.jussi.pasianssi.apu;

import java.util.List;
import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.Korttipakka;

/**
 * Luokka tarjoaa pasianssipelin alustamiseen liittyviä apumetodeja.
 * @see fi.jussi.pasianssi.kortit.Pasianssi
 */
public class Pasianssinalustaja {
	
	/**
	 * Metodia luo pasianssipeliä alustaessa tyhjät
	 * {@link fi.jussi.pasianssi.kortit.Korttipino}t.
	 * @param pinot	pasianssin korttipinot
	 */
	public static void alustaPinot(List<Korttipino> pinot) {
		for (int i = 0; i < 10; i++) {
            Korttipino pino = new Korttipino();
            pinot.add(pino);
        }
	}
	
	/**
	 * Metodi täyttää pasianssin pakan 104 kortilla ja sekoittaa sen.
	 * @param pakka	pasianssin korttipakka
	 */
	public static void alustaPakka(Korttipakka pakka) {
		Pakantayttaja.alustaTuplakorttipakka(pakka);
		pakka.sekoita();
	}
	
	/**
	 * Metodi täyttää pasianssin korttipinot alkuasetelman mukaisiksi.
	 * @param pinot pasianssin korttipinot
	 * @param pakka pasianssin korttipakka
	 */
	public static void taytaPinot(List<Korttipino> pinot, Korttipakka pakka) {
		for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
				pinot.get(j).lisaaKaannettyKortti(pakka.nosta());
            }
		}
		
		for (int i = 0; i < 4; i++) {
            pinot.get(i).lisaaKaannettyKortti(pakka.nosta());
		}
		
		for (int i = 0; i < 10; i++) {
            pinot.get(i).lisaaNakyvaKortti(pakka.nosta());
		}
	}
}
