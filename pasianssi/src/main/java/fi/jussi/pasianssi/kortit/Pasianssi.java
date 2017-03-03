package fi.jussi.pasianssi.kortit;

import fi.jussi.pasianssi.apu.Pasianssinalustaja;
import java.util.List;
import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Pasianssipeliä kuvaava luokka. Pelissä on {@link fi.jussi.pasianssi.kortit.Korttipakka}
 * ja kymmenen {@link fi.jussi.pasianssi.kortit.Korttipino}a.
 * @see fi.jussi.pasianssi.kortit.Korttipakka
 * @see fi.jussi.pasianssi.kortit.Korttipino
 */
public class Pasianssi {
	private Korttipakka pakka;
	private List<Korttipino> pinot;
	private int maita;
   
	/**
	 * Konstruktori konstruktoi.
	 * @param maita maiden lukumäärä, mahdolliset arvot 1, 2 tai 4
	 */
	public Pasianssi(int maita) {
		this.pakka = new Korttipakka();
		this.pinot = new ArrayList();
		this.maita = maita;
		Pasianssinalustaja.alustaPinot(this.pinot);
		Pasianssinalustaja.alustaPakka(this.pakka, maita);
		Pasianssinalustaja.taytaPinot(this.pinot, this.pakka);
	}
    
	/**
	 * Metodi, jolla pasianssin pakasta voi nostaa yhden kortin jokaiseen
	 * korttipinoon.
	 * @return	nostaminen onnistui
	 */
	public boolean nosta10Korttia() {
		for (int i = 0; i < 10; i++) {
			Korttipino pino = this.pinot.get(i);
			Kortti nostettava;
			
			try {
				nostettava = this.pakka.nosta();
			} catch (EmptyStackException e) {
				return false;
			}
			
			pino.lisaaNakyvaKortti(nostettava);
		}
		
		return true;
	}
	
	/**
	 * Metodi, joka kertoo, onko pasianssi voitettu. Peli on voitettu, jos
	 * pakka ja kaikki korttipinot ovat tyhjiä.
	 * @return tosi, jos pasianssi on voitettu
	 */
	public boolean voitettu() {
		if (!this.pakka.tyhja()) {
			return false;
		}
		
		for (Korttipino pino : this.pinot) {
			if (!pino.tyhja()) {
				return false;
			}
		}
		
		return true;
	}
    
	public List<Korttipino> getPinot() {
		return this.pinot;
	}
	
	public Korttipakka getPakka() {
		return this.pakka;
	}
	
	public int getMaita() {
		return this.maita;
	}
}
