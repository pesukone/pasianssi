package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.NakyvaKortti;
import java.util.List;

/**
 * Luokka, joka tarjoaa korttien siirtämiseen liittyviä metodeja.
 * @see fi.jussi.pasianssi.kortit.NakyvaKortti
 * @see fi.jussi.pasianssi.kortit.Korttipino
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
		if (!kortti.voiSiirtaa()) {
			return false;
		}
		
		if (kannattaaSiirtaa(pinot, kortti)) {
			return true;
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
		
		Kortinpoistaja.etsiJaPoistaSarja(kohde);
		
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
	
	private static int laskeSarja(NakyvaKortti hanta) {
		int korttimaara = 0;
		NakyvaKortti iteroitava = hanta;
		while (iteroitava.getEdellinen() != null) {
			if (!Kortinvertailija.samaaMaataJaYhdenEro(iteroitava.getKortti(), iteroitava.getEdellinen().getKortti())) {
				break;
			}
			
			korttimaara++;
			iteroitava = iteroitava.getEdellinen();
		}
		
		return korttimaara;
	}
	
	private static boolean tutkiKannattavuus(Korttipino pino, NakyvaKortti kortti) {
		if (Kortinvertailija.yhdenEro(kortti.getKortti(), pino.getNakyvat().hanta().getKortti())) {
			int lahdesarja = 1;
			int siirrettavaSarja = 1;
			int kohdesarja = 1;
			
			NakyvaKortti iteroitava = kortti;
			while (iteroitava.getSeuraava() != null) {
				siirrettavaSarja++;
				lahdesarja++;
				iteroitava = iteroitava.getSeuraava();
			}
				
			lahdesarja += laskeSarja(kortti);
			kohdesarja += laskeSarja(pino.getNakyvat().hanta());
				
			if (kohdesarja + siirrettavaSarja > lahdesarja) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean kannattaaSiirtaa(List<Korttipino> pinot, NakyvaKortti kortti) {
		for (Korttipino pino : pinot) {
			if (pino.onPinossa(kortti)) {
				continue;
			}
			
			if (pino.getNakyvat() == null || tutkiKannattavuus(pino, kortti)) {
				return true;
			}
		}
		
		return false;
	}
}
