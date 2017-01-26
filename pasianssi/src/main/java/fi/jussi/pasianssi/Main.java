package fi.jussi.pasianssi;

import fi.jussi.pasianssi.logiikka.Pakantayttaja;
import fi.jussi.pasianssi.kortit.*;

public class Main {
    
    public static void main(String[] args) {
        Korttipakka pakka = new Korttipakka();
        Pakantayttaja.tayta52KortinPakka(pakka);
        pakka.sekoita();
        
        System.out.println(pakka.nostaKortti());
        System.out.println(pakka.nostaKortti());
    }
}
