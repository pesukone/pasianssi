package fi.jussi.pasianssi.kortit;

/**
 * 
 * Pelikortti, jolla on {@link fi.jussi.pasianssi.kortit.Maa} ja arvo.
 */
public class Kortti {
	private Maa maa;
	private int arvo;
    
	/**
	 * Konstruktori konstruktoi.
	 * @param maa	Pelikortin {@link fi.jussi.pasianssi.kortit.Maa}
	 * @param arvo	Pelikortin arvo
	 */	
	public Kortti(Maa maa, int arvo) {
		if (arvo < 1 || arvo > 13) {
			throw(new IllegalArgumentException());
		}
        
		this.maa = maa;
		this.arvo = arvo;
	}
	
	public boolean samaMaa(Kortti kortti) {
		return this.maa.equals(kortti.getMaa());
	}
	
	public boolean yhtaPienempi(Kortti kortti) {
		return this.arvo == (kortti.getArvo() - 1);
	}
    
	public Maa getMaa() {
		return this.maa;
	}
    
	public int getArvo() {
		return this.arvo;
	}
    
	@Override
	public String toString() {
		return this.maa + " " + this.arvo;
	}
}