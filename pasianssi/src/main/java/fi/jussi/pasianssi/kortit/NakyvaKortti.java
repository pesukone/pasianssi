package fi.jussi.pasianssi.kortit;

/**
 * Linkitettynä listana toteutettu joukko kortteja. Kortin ja kaikki siitä
 * seuraavat kortit voi siirtää toisen korttilistan perälle.
 * @see fi.jussi.pasianssi.kortit.Korttipino
 */
public class NakyvaKortti {
    private Kortti kortti;
    private NakyvaKortti seuraava;
    private NakyvaKortti edellinen;
    
    public NakyvaKortti(Kortti kortti) {
        this.kortti = kortti;
        this.seuraava = null;
        this.edellinen = null;
    }
    
    public void lisaa(NakyvaKortti lisattava) {
        NakyvaKortti pino = this;
        
        while (pino.getSeuraava() != null) {
            pino = pino.getSeuraava();
        }
        
        pino.setSeuraava(lisattava);
        lisattava.setEdellinen(pino);
    }
    
    public void lisaa(Kortti kortti) {
        NakyvaKortti lisattava = new NakyvaKortti(kortti);
        this.lisaa(lisattava);
    }
    
    public void siirra(NakyvaKortti kohde) {
        this.getEdellinen().setSeuraava(null);
        kohde.lisaa(this);
    }
    
    public NakyvaKortti paa() {
        NakyvaKortti paa = this;
        while (this.getEdellinen() != null) {
            paa = this.getEdellinen();
        }
        
        return paa;
    }
    
    public NakyvaKortti hanta() {
        NakyvaKortti hanta = this;
        while (hanta.getSeuraava() != null) {
            hanta = hanta.getSeuraava();
        }
        
        return hanta;
    }
	
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
