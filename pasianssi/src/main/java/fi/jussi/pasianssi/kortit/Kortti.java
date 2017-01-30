package fi.jussi.pasianssi.kortit;

public class Kortti {
    private Maa maa;
    private int arvo;
    
    public Kortti(Maa maa, int arvo) {
        if (arvo < 1 || arvo > 13) {
            throw(new IllegalArgumentException());
        }
        
        this.maa = maa;
        this.arvo = arvo;
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