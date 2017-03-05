package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.NakyvaKortti;
import fi.jussi.pasianssi.kortit.Korttipino;

/**
 * Luokka tarjoaa korttien poistamiseen liittyvän toiminnallisuuden.
 * @see fi.jussi.pasianssi.apu.Kortinsiirtaja
 * @see fi.jussi.pasianssi.kortit.Korttipino
 */
public class Kortinpoistaja {
	
	/**
	 * Metodi poistaa pinosta 13 saman maan kortin sarjan, jos sellainen löytyy
	 * pinosta.
	 * @param pino pino, jota tutkitaan
	 */
	public static void etsiJaPoistaSarja(Korttipino pino) {
		if (pino.getNakyvat() == null || pino.getNakyvat().getSeuraava() == null) {
			return;
		}
		
		NakyvaKortti iteroitava = pino.getNakyvat();
		
		while (iteroitava.getSeuraava() != null) {
			if (iteroitava.getKortti().getArvo() == 13) {
				tutkiSarja(pino, iteroitava);
			}
			
			iteroitava = iteroitava.getSeuraava();
		}
	}
	
	private static void tutkiSarja(Korttipino pino, NakyvaKortti kuningas) {
		if (kuningas.getSeuraava() == null) {
			return;
		}
		
		NakyvaKortti iteroitava = kuningas;
		
		do {
			iteroitava = iteroitava.getSeuraava();
			
			if (!Kortinvertailija.samaaMaataJaYhdenEro(iteroitava.getKortti(), iteroitava.getEdellinen().getKortti())) {
				return;
			}
			
			if (iteroitava.getKortti().getArvo() == 1 && iteroitava.getSeuraava() == null) {
				poistaSarja(pino, kuningas);
				return;
			}
		} while (iteroitava.getSeuraava() != null);
	}
	
	private static void poistaSarja(Korttipino pino, NakyvaKortti kuningas) {
		if (kuningas.equals(pino.getNakyvat())) {
			pino.setNakyvat(null);
		}
		
		if (kuningas.getEdellinen() != null) {
			kuningas.getEdellinen().setSeuraava(null);
		}
		
		kuningas.setEdellinen(null);
	}
}
