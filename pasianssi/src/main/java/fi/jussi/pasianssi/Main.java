package fi.jussi.pasianssi;

import fi.jussi.pasianssi.logiikka.*;
import fi.jussi.pasianssi.kortit.*;

public class Main {
    
    public static void main(String[] args) {
        Pasianssi pasianssi = new Pasianssi();
		
		while (!pasianssi.getPakka().tyhja()) {
			System.out.println(pasianssi.getPakka().nosta());
		}
    }
}
