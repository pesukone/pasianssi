package fi.jussi.pasianssi.kortit;

import java.util.Stack;

public class Korttipakka {
    private Stack<Kortti> kortit;
    
    public Korttipakka() {
        this.kortit = new Stack();
    }
    
    public Kortti nostaKortti() {
        return this.kortit.pop();
    }
}
