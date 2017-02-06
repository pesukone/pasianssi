package fi.jussi.pasianssi.kortit;


public class NakyvienPino {
    private Kortti arvo;
    private NakyvienPino seuraava;
    private NakyvienPino edellinen;
    
    public NakyvienPino(Kortti arvo) {
        this.arvo = arvo;
        this.seuraava = null;
        this.edellinen = null;
    }
    
    public NakyvienPino() {
        this(null);
    }
    
    public void lisaa(NakyvienPino pino) {
        this.setSeuraava(pino);
        pino.setEdellinen(this);
    }
    
    public void siirra(NakyvienPino kohde) {
        this.getEdellinen().setSeuraava(null);
        kohde.lisaa(this);
    }
    
    public NakyvienPino getSeuraava() {
        return this.seuraava;
    }
    
    public NakyvienPino getEdellinen() {
        return this.edellinen;
    }
    
    public Kortti getArvo() {
        return this.arvo;
    }
    
    public void setSeuraava(NakyvienPino seuraava) {
        this.seuraava = seuraava;
    }
    
    public void setEdellinen(NakyvienPino edellinen) {
        this.edellinen = edellinen;
    }
}
