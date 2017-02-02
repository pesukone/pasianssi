package fi.jussi.pasianssi.kortit;

import java.util.Collections;
import java.util.Stack;

public class Korttipakka {
    private Stack<Kortti> kortit;
    
    public Korttipakka() {
        this.kortit = new Stack();
    }
    
    public void lisaaKortti(Kortti kortti) {
        this.kortit.push(kortti);
    }
    
    public Kortti nosta() {
        return this.kortit.pop();
    }
    
    public void sekoita() {
        Collections.shuffle(this.kortit);
    }
    
    public boolean tyhja() {
        return this.kortit.empty();
    }
    
    public int korttimaara() {
        return this.kortit.size();
    }
    
    public Kortti paalimmainen() {
        return this.kortit.peek();
    }
}
