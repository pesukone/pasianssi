package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Kortti;

/**
 * Luokka tarjoaa {@link fi.jussi.pasianssi.kortit.Kortti}-olioiden
 * vertailemisessa käytettäviä apumetodeja.
 * @see fi.jussi.pasianssi.kortit.Kortti
 */
public class Kortinvertailija {
	
	/**
	 * Metodi kertoo, onko kahden kortin maat samat.
	 * @param kortti vertailtava kortti
	 * @param toinen toinen vertailtava kortti
	 * @return tosi, jos vertailtavien korttien maat ovat samat
	 */
	public static boolean samaMaa(Kortti kortti, Kortti toinen) {
		return kortti.getMaa().equals(toinen.getMaa());
	}
	
	/**
	 * Metodi kertoo, onko toinen parametreinä annetuista korteista yhtä
	 * pienempi kuin toinen.
	 * @param pienempi korteista pienempi
	 * @param suurempi korteista suurempi
	 * @return tosi, jos ensimmäinen kortti on yhden pienempi kuin toinen
	 */
	public static boolean yhdenEro(Kortti pienempi, Kortti suurempi) {
		return pienempi.getArvo() == suurempi.getArvo() - 1;
	}
	
	/**
	 * Metodi kertoo, onko toinen parametreinä annetuista korteista yhtä
	 * pienempi kuin toinen, ja ovatko ne samaa maata.
	 * @param pienempi korteista pienempi
	 * @param suurempi korteista suurempi
	 * @return epätosi, jos ensimmäinen kortti ei ole yhtä pienempi kuin toinen,
	 * tai kortit ovat eri maata
	 */
	public static boolean samaaMaataJaYhdenEro(Kortti pienempi, Kortti suurempi) {
		return samaMaa(pienempi, suurempi) && yhdenEro(pienempi, suurempi);
	}
}
