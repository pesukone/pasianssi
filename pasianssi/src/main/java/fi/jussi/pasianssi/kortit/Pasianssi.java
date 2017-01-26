package fi.jussi.pasianssi.kortit;

import java.util.List;
import java.util.ArrayList;

public class Pasianssi {
    private Korttipakka pakka;
    private List<Korttipino> pinot;
    
    public Pasianssi() {
        this.pakka = new Korttipakka();
        this.pinot = new ArrayList();
        this.alustaPinot();
    }
    
    private void alustaPinot() {
        for (int i = 0; i < 10; i++) {
            Korttipino pino = new Korttipino();
            this.pinot.add(pino);
        }
    }
}
