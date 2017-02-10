package fi.jussi.pasianssi.kortit;

import java.util.Stack;

public class Korttipino {
    private Stack<Kortti> kaannetytKortit;
    private NakyvaKortti nakyvatKortit;
    
    public Korttipino() {
        this.kaannetytKortit = new Stack();
    }
    
    public void siirraKortti(NakyvaKortti kortti, Korttipino kohde) {
		if (kohde.eiNakyviaKortteja()) {
			kohde.setNakyvat(kortti);
		} else {
			kortti.siirra(kohde.getNakyvat());
		}
		
        if (this.eiNakyviaKortteja()) {
            this.kaannaKorttiNakyviin();
		}
    }
	
    public void lisaaNakyvaKortti(Kortti kortti) {
		if (this.nakyvatKortit == null) {
			this.nakyvatKortit = new NakyvaKortti(kortti);
		} else {
			this.nakyvatKortit.lisaa(kortti);
		}
    }
    
    public void lisaaKaannettyKortti(Kortti kortti) {
        this.kaannetytKortit.push(kortti);
    }
    
    public boolean tyhja() {
		return (this.kaannetytKortit.empty() && this.nakyvatKortit == null);
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
    
    public int korttimaara() {
        return this.kaannetytKortit.size() + this.nakyvatKortit.seuraaviaKortteja();
    }
	
    private void kaannaKorttiNakyviin() {
		if (!this.kaannetytKortit.isEmpty()) {
			this.nakyvatKortit.lisaa(this.kaannetytKortit.pop());
		}
    }
	
    private boolean eiNakyviaKortteja() {
        return this.nakyvatKortit == null;
    }
}
