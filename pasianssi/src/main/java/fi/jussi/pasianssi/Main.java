package fi.jussi.pasianssi;

import fi.jussi.pasianssi.kortit.*;
import fi.jussi.pasianssi.kali.*;
import javafx.application.Application;

public class Main {
    
	public static void main(String[] args) {
		Pasianssi pasianssi = new Pasianssi(4);
		Kayttoliittyma kali = new Kayttoliittyma();
		Kayttoliittyma.setPasianssi(pasianssi);
		Application.launch(Kayttoliittyma.class, args);
	}
}
