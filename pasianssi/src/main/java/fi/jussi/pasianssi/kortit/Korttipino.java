package fi.jussi.pasianssi.kortit;

import java.util.Stack;

public class Korttipino {
    private Stack<Kortti> kortit;
    
    public Korttipino() {
        this.kortit = new Stack();
    }
    
    public void siirraKortti(Korttipino kohde) {
        kohde.lisaaKortti(this.kortit.pop());
    }
    
    public void lisaaKortti(Kortti kortti) {
        this.kortit.push(kortti);
    }
}
