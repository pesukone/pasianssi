package fi.jussi.pasianssi.kortit;

import java.util.Stack;
import java.util.LinkedList;

public class Korttipino {
    private Stack<Kortti> kaannetytKortit;
    private LinkedList<Kortti> nakyvatKortit;
    
    public Korttipino() {
        this.kaannetytKortit = new Stack();
        this.nakyvatKortit = new LinkedList();
    }
    
    public void siirraKortti(Korttipino kohde) {
        kohde.lisaaNakyvaKortti(this.nakyvatKortit.removeLast());
    }
    
    public void lisaaNakyvaKortti(Kortti kortti) {
        this.nakyvatKortit.add(kortti);
    }
    
    public void lisaaKaannettyKortti(Kortti kortti) {
        this.kaannetytKortit.push(kortti);
    }
    
    public boolean eiNakyviaKortteja() {
        return this.nakyvatKortit.isEmpty();
    }
	
    public boolean tyhja() {
	return (this.kaannetytKortit.empty() && this.nakyvatKortit.isEmpty());
    }
	
    public Stack<Kortti> getKaannetyt() {
	return this.kaannetytKortit;
    }
	
    public LinkedList<Kortti> getNakyvat() {
	return this.nakyvatKortit;
    }
    
    public int korttimaara() {
        return this.kaannetytKortit.size() + this.nakyvatKortit.size();
    }
}
