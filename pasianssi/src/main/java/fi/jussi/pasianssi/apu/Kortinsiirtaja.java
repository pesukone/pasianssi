package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.NakyvaKortti;
import java.util.List;

/**
 * Luokka, joka tarjoaa korttien siirtämiseen liittyviä metodeja.
 * @see fi.jussi.pasianssi.kortit.NakyvaKortti;
 * @see fi.jussi.pasianssi.kortit.Korttipino;
 */
public class Kortinsiirtaja {
	
	/**
	 * Metodi siirtää kortin lähdepinosta kohdepinoon ja poistaa kortit pakasta,
	 * jos 13 kortin sarja täyttyy.
	 * @param lahde pino, josta kortti siirretään
	 * @param kortti siirrettävä kortti, jonka oletetaan olevan lähdepinossa
	 * @param kohde pino, jonne kortti siirretään
	 * @return tosi, jos siirto onnistui
	 */
	public static boolean siirraKortti(Korttipino lahde, NakyvaKortti kortti, Korttipino kohde) {
		if (eiNakyviaKortteja(kohde)) {
			if (!siirraTyhjaan(kortti, kohde)) {
				return false;
			}
		} else {
			if (!kortti.siirra(kohde.getNakyvat())) {
				return false;
			}
		}
		
		jalkisiivous(lahde, kortti, kohde);
		
		return true;
	}
	
	/**
	 * Metodi selvittää, voiko jotakin korttipinon korttia siirtää toiseen pinoon.
	 * Kortit käydään läpi käänteisessä järjestyksessä, eli pinon päälimmäinen
	 * kortti tutkitaan ensin.
	 * @param pinot {@link fi.jussi.pasianssi.kortit.Pasianssi}n korttipinot
	 * @param kortti {@link fi.jussi.pasianssi.kortit.Korttipino}n häntä
	 * @return tosi, jos jonkun korttipinon kortin voi siirtää johonkin toiseen
	 * pinoon
	 */
	public static boolean voiSiirtaa(List<Korttipino> pinot, NakyvaKortti kortti) {
		NakyvaKortti iteroitava = kortti;
		while (iteroitava.getSeuraava() != null) {
			if (!Kortinvertailija.samaaMaataJaYhdenEro(iteroitava.getSeuraava().getKortti(), iteroitava.getKortti())) {
				return false;
			}
			
			iteroitava = iteroitava.getSeuraava();
		}
		
		for (Korttipino pino : pinot) {
			if (pino.onPinossa(kortti)) {
				continue;
			}
			
			if (pino.getNakyvat() == null) {
				return true;
			}
			
			if (Kortinvertailija.yhdenEro(kortti.getKortti(), pino.getNakyvat().hanta().getKortti())) {
				int lahdesarja = 1;
				int siirrettavaSarja = 1;
				int kohdesarja = 1;
				
				iteroitava = kortti;
				while (iteroitava.getSeuraava() != null) {
					siirrettavaSarja++;
					lahdesarja++;
					iteroitava = iteroitava.getSeuraava();
				}
				
				iteroitava = kortti;
				while (iteroitava.getEdellinen() != null) {
					if (!Kortinvertailija.samaaMaataJaYhdenEro(iteroitava.getKortti(), iteroitava.getEdellinen().getKortti())) {
						break;
					}
					
					lahdesarja++;
					iteroitava = iteroitava.getEdellinen();
				}
				
				iteroitava = pino.getNakyvat().hanta();
				while (iteroitava.getEdellinen() != null) {
					if (!Kortinvertailija.samaaMaataJaYhdenEro(iteroitava.getKortti(), iteroitava.getEdellinen().getKortti())) {
						break;
					}
					
					kohdesarja++;
					iteroitava = iteroitava.getEdellinen();
				}
				
				if (kohdesarja + siirrettavaSarja > lahdesarja) {
					return true;
				}
				
				//return true;
			}
		}
		
		if (kortti.getEdellinen() != null) {
			if (voiSiirtaa(pinot, kortti.getEdellinen())) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean eiNakyviaKortteja(Korttipino pino) {
		return pino.getNakyvat() == null;
	}
	
	private static boolean siirraTyhjaan(NakyvaKortti kortti, Korttipino kohde) {
		if (!kortti.voiSiirtaa()) {
			return false;
		}
		
		if (kortti.getEdellinen() != null) {
			kortti.getEdellinen().setSeuraava(null);
		}
		kohde.setNakyvat(kortti);
		kortti.setEdellinen(null);
		
		return true;
	}
	
	private static void jalkisiivous(Korttipino lahde, NakyvaKortti kortti, Korttipino kohde) {
		if (kortti.equals(lahde.getNakyvat())) {
			lahde.setNakyvat(null);
		}
		
		etsiSarja(kohde);
		
		if (eiNakyviaKortteja(lahde)) {
			kaannaKorttiNakyviin(lahde);
		}
		
		if (eiNakyviaKortteja(kohde)) {
			kaannaKorttiNakyviin(kohde);
		}
	}
	
	private static void kaannaKorttiNakyviin(Korttipino pino) {
		if (!pino.getKaannetyt().isEmpty()) {
			pino.setNakyvat(new NakyvaKortti(pino.getKaannetyt().pop()));
		}
	}
	
	private static void etsiSarja(Korttipino pino) {
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
