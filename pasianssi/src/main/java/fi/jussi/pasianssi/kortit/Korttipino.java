package fi.jussi.pasianssi.kortit;

import java.util.Stack;

/**
 * Näkyvien ja käännettyjen korttien pino pasianssissa. Pohjimmaisten
 * korttien kuvapuolet voivat olla alaspäin. Kortit, joiden kuvapuolet ovat
 * ylöspäin, kuvataan linkitettynä listana. Kortit, joiden kuvapuolet ovat
 * alaspäin, kuvataan pinona.
 * @see fi.jussi.pasianssi.kortit.NakyvaKortti
 * @see java.util.Stack
 */
public class Korttipino {
	private Stack<Kortti> kaannetytKortit;
	private NakyvaKortti nakyvatKortit;
    
	/**
	 * Konstruktori konstruktoi näkyvien korttien oletusarvoksi null.
	 */
	public Korttipino() {
		this.kaannetytKortit = new Stack();
	}
    
	/**
	 * Metodi lisää korttipinoon kortin, jonka kuvapuoli on näkyvissä.
	 * @param kortti pinoon lisättävä kortti
	 */
	public void lisaaNakyvaKortti(Kortti kortti) {
		if (this.eiNakyviaKortteja()) {
			this.nakyvatKortit = new NakyvaKortti(kortti);
		} else {
			this.nakyvatKortit.lisaa(kortti);
		}
	}
    
	/**
	 * Metodi lisää korttipinoon kortin, jonka kuvapuoli on käännetty alaspäin.
	 * Kutsutaan pasianssipelin alustamisen yhteydessä.
	 * @param kortti pinoon lisättävä kortti
	 */
	public void lisaaKaannettyKortti(Kortti kortti) {
		this.kaannetytKortit.push(kortti);
	}
	
	/**
	 * Metodi siirtää pöydällä olevan näkyvän kortin ja sitä seuraavat kortit
	 * toiseen korttipinoon.
	 * @param kortti siirrettävä kortti
	 * @param kohde	korttipino, jonka perään kortti/kortit siirretään
	 */
	public boolean siirraKortti(NakyvaKortti kortti, Korttipino kohde) {
		if (kohde.eiNakyviaKortteja()) {
			siirraTyhjaan(kortti, kohde);
		} else {
			if (kortti.siirra(kohde.getNakyvat()) == false) {
				return false;
			}
		}
        
		jalkisiivous(kortti);
		
		return true;
	}
	
	/**
	 * Kertoo, onko korttipino tyhjä.
	 * @return korttipino on tyhjä.
	 */
	public boolean tyhja() {
		return (this.kaannetytKortit.empty() && this.nakyvatKortit == null);
	}
	
	/**
	 * Metodi, joka laskee korttipinon korttien yhteismäärän.
	 * @return korttien määrä
	 */
	public int korttimaara() {
		return this.kaannetytKortit.size() + this.nakyvatKortit.seuraaviaKortteja();
	}
	
	/**
	 * Metodi, joka selvittää, onko parametrinä annettu {@link fi.jussi.pasianssi.kortit.NakyvaKortti}
	 * pakassa.
	 * @param kortti kortti jota etsitään pinosta
	 * @return palautetaan tosi, jos kortti on pinossa
	 */
	public boolean onPinossa(NakyvaKortti kortti) {
		if (eiNakyviaKortteja()) {
			return false;
		}
		
		if (this.nakyvatKortit.getSeuraava() == null) {
			return this.nakyvatKortit.equals(kortti);
		}
		
		return tutkiPino(this.nakyvatKortit, kortti);
	}
      	
	public Stack<Kortti> getKaannetyt() {
		return this.kaannetytKortit;
	}
	
	public NakyvaKortti getNakyvat() {
		return this.nakyvatKortit;
	}
	
	public void setNakyvat(NakyvaKortti kortti) {
		this.nakyvatKortit = kortti;
	}

	private void kaannaKorttiNakyviin() {
		if (!this.kaannetytKortit.isEmpty()) {
			this.nakyvatKortit = new NakyvaKortti(this.kaannetytKortit.pop());
		}
	}
	
	private boolean eiNakyviaKortteja() {
		return this.nakyvatKortit == null;
	}
	
	private static void siirraTyhjaan(NakyvaKortti kortti, Korttipino kohde) {
		kohde.setNakyvat(kortti);
		kortti.setEdellinen(null);
	}
	
	private void jalkisiivous(NakyvaKortti kortti) {
		if (kortti.equals(this.nakyvatKortit)) {
			this.nakyvatKortit = null;
		}
		
		if (this.eiNakyviaKortteja()) {
			this.kaannaKorttiNakyviin();
		}
	}
	
	private boolean tutkiPino(NakyvaKortti iteroitava, NakyvaKortti verrattava) {
		do {
			if (iteroitava.equals(verrattava)) {
				return true;
			}
			iteroitava = iteroitava.getSeuraava();
		} while (iteroitava != null);
		
		return false;
	}
}
