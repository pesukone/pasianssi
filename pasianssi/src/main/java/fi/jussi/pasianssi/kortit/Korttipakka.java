package fi.jussi.pasianssi.kortit;

import java.util.Collections;
import java.util.Stack;

/**
 * Luokka korttipakalle.
 * Pakka on kuvattu pinona, jossa on {@link fi.jussi.pasianssi.kortit.Kortti}-luokan
 * olioita. Pakasta voi nostaa vain päälimmäisen kortin kerralla, eikä alempiin
 * kortteihin pääse käsiksi. Viimeisenä lisätyn kortin voi nostaa ensimmäisenä.
 * @see java.util.Stack
 * @see fi.jussi.pasianssi.kortit.Kortti
 */
public class Korttipakka {
    private Stack<Kortti> kortit;
    
    public Korttipakka() {
        this.kortit = new Stack();
    }
    
    /**
     * Metodi lisää korttiolion pakkaan.
     * @param kortti	lisättävä kortti
     */
    public void lisaaKortti(Kortti kortti) {
        this.kortit.push(kortti);
    }
    
    /**
     * Metodi poistaa päälimmäisen kortin pakasta.
     * @return	poistettu kortti
     */
    public Kortti nosta() {
        return this.kortit.pop();
    }
    
    /**
     * Metodi sekoittaa korttipakan.
     */
    public void sekoita() {
        Collections.shuffle(this.kortit);
    }
    
    public boolean tyhja() {
        return this.kortit.empty();
    }
    
    public int korttimaara() {
        return this.kortit.size();
    }
    
    /**
     * Metodi palauttaa pakan päälimmäisen korttiolion poistamatta sitä pakasta.
     * @return	päälimmäinen kortti
     */
    public Kortti paalimmainen() {
        return this.kortit.peek();
    }
}
