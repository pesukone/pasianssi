package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.NakyvaKortti;

public class Kortinsiirtaja {
	
	public static boolean siirraKortti(Korttipino lahde, NakyvaKortti kortti, Korttipino kohde) {
		if (eiNakyviaKortteja(kohde)) {
			siirraTyhjaan(kortti, kohde);
		} else {
			if (kortti.siirra(kohde.getNakyvat()) == false) {
				return false;
			}
		}
		
		jalkisiivous(lahde, kortti, kohde);
		
		return true;
	}
	
	private static boolean eiNakyviaKortteja(Korttipino pino) {
		return pino.getNakyvat() == null;
	}
	
	private static void siirraTyhjaan(NakyvaKortti kortti, Korttipino kohde) {
		kohde.setNakyvat(kortti);
		kortti.setEdellinen(null);
	}
	
	private static void jalkisiivous(Korttipino lahde, NakyvaKortti kortti, Korttipino kohde) {
		if (kortti.equals(lahde.getNakyvat())) {
			lahde.setNakyvat(null);
		}
		
		etsiSarja(kohde);
		
		if (eiNakyviaKortteja(lahde)) {
			kaannaKorttiNakyviin(lahde);
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
