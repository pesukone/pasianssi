package fi.jussi.pasianssi.kortit;

import fi.jussi.pasianssi.logiikka.Pakantayttaja;
import java.util.List;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Pasianssi {
    private Korttipakka pakka;
    private List<Korttipino> pinot;
    
    public Pasianssi() {
        this.pakka = new Korttipakka();
        this.pinot = new ArrayList();
        this.alustaPinot();
        this.alustaPakka();
        this.taytaPinot();
    }
    
    private void alustaPinot() {
        for (int i = 0; i < 10; i++) {
            Korttipino pino = new Korttipino();
            this.pinot.add(pino);
        }
    }
    
    private void alustaPakka() {
        Pakantayttaja.alustaTuplakorttipakka(this.pakka);
        this.pakka.sekoita();
    }
    

    // refaktoroidaan sitten joskus omaan luokkaan
    private void taytaPinot() {
	for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
		this.pinot.get(j).lisaaKaannettyKortti(this.pakka.nosta());
            }
	}
		
	for (int i = 0; i < 4; i++) {
            this.pinot.get(i).lisaaKaannettyKortti(this.pakka.nosta());
	}
		
	for (int i = 0; i < 10; i++) {
            this.pinot.get(i).lisaaNakyvaKortti(this.pakka.nosta());
	}
    }
    
    public boolean nosta10Korttia() {
        for (int i = 0; i < 10; i++) {
            Korttipino pino = this.pinot.get(i);
			Kortti nostettava;
			
			try{
				nostettava = this.pakka.nosta();
			} catch (EmptyStackException e) {
				return false;
			}
			
			pino.lisaaNakyvaKortti(nostettava);
        }
		
		return true;
    }
    
    public List<Korttipino> getPinot() {
        return this.pinot;
    }
	
	public Korttipakka getPakka() {
		return this.pakka;
	}
}
