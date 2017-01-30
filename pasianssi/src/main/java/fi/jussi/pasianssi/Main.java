package fi.jussi.pasianssi;

import fi.jussi.pasianssi.logiikka.Pakantayttaja;
import fi.jussi.pasianssi.kortit.*;

public class Main {
    
    public static void main(String[] args) {
        Korttipakka pakka = new Korttipakka();
        Pakantayttaja.alustaPeruskorttipakka(pakka);
        pakka.sekoita();
        
        System.out.println(pakka.nosta());
        System.out.println(pakka.nosta());
    }
}
