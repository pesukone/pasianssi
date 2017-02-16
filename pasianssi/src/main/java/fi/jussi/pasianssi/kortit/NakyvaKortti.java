package fi.jussi.pasianssi.kortit;

/**
 * Linkitettynä listana toteutettu joukko kortteja. Kortin ja kaikki siitä
 * seuraavat kortit voi siirtää toisen korttilistan perälle.
 * @see fi.jussi.pasianssi.kortit.Korttipino
 * @see fi.jussi.pasianssi.kortit.Kortti
 */
public class NakyvaKortti {
	private Kortti kortti;
	private NakyvaKortti seuraava;
	private NakyvaKortti edellinen;
    
	/**
	 * Konstruktori asettaa solmun kortiksi parametrinä annetun kortin.
	 * @param kortti kortti, joka asetetaan solmun parametriksi
	 */
	public NakyvaKortti(Kortti kortti) {
		this.kortti = kortti;
		this.seuraava = null;
		this.edellinen = null;
	}
    
	/**
	 * Metodi liittää parametrinä annetun korttilistan metodia kutsuvan listan
	 * perään.
	 * @param lisattava		listaan lisättävä korttilista
	 */
	public void lisaa(NakyvaKortti lisattava) {
		NakyvaKortti pino = this;
        
		while (pino.getSeuraava() != null) {
			pino = pino.getSeuraava();
		}
        
		pino.setSeuraava(lisattava);
		lisattava.setEdellinen(pino);
	}
    
	/**
	 * Metodi luo listan perään uuden solmun, jonka korttimuuttuja annetaan
	 * metodille parametrinä.
	 * @param kortti	listaan lisättävä kortti
	 */
	public void lisaa(Kortti kortti) {
		NakyvaKortti lisattava = new NakyvaKortti(kortti);
		this.lisaa(lisattava);
	}
    
	/**
	 * Metodi siirtää listan solmun parametrina annetun korttilistan perälle.
	 * @param kohde		lista, johon solmu siirretään
	 */
	public void siirra(NakyvaKortti kohde) {
		if (this.getEdellinen() != null) {
			this.getEdellinen().setSeuraava(null);
			this.setEdellinen(null);
		}
        
		kohde.lisaa(this);
	}
    
	/**
	 * Metodi palauttaa viitteen listan ensimmäiseen solmuun.
	 * @return	listan ensimmäinen solmu
	 */
	public NakyvaKortti paa() {
		NakyvaKortti paa = this;
		while (this.getEdellinen() != null) {
			paa = this.getEdellinen();
		}
        
		return paa;
	}
    
	/**
	 * Metodi palauttaa viitteen listan viimeiseen solmuun.
	 * @return	listan viimeinen solmu
	 */
	public NakyvaKortti hanta() {
		NakyvaKortti hanta = this;
		while (hanta.getSeuraava() != null) {
			hanta = hanta.getSeuraava();
		}
        
		return hanta;
	}
	
	/**
	 * Metodi kertoo, kuinka monta korttia listalla on nykyisen solmun jälkeen.
	 * @return	korttien määrä
	 */
	public int seuraaviaKortteja() {
		int maara = 0;
		NakyvaKortti iteroitava = this;
		
		while (iteroitava != null) {
			iteroitava = iteroitava.seuraava;
			maara++;
		}
		
		return maara;
	}
    
	public NakyvaKortti getSeuraava() {
		return this.seuraava;
	}
    
	public NakyvaKortti getEdellinen() {
		return this.edellinen;
	}
    
	public Kortti getKortti() {
		return this.kortti;
	}
    
	public void setSeuraava(NakyvaKortti seuraava) {
		this.seuraava = seuraava;
	}
    
	public void setEdellinen(NakyvaKortti edellinen) {
		this.edellinen = edellinen;
	}
}
