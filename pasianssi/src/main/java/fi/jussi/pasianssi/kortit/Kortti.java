package fi.jussi.pasianssi.kortit;

public class Kortti {
    private Maa maa;
    private int arvo;
    private boolean kuvaAlas;
      
    public Kortti(Maa maa, int arvo) {
        this.maa = maa;
        this.arvo = arvo;
        this.kuvaAlas = true;
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
