package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Kortti;

public class Kortinvertailija {
	
	public static boolean samaMaa(Kortti kortti, Kortti toinen) {
		return kortti.getMaa().equals(toinen.getMaa());
	}
	
	public static boolean yhdenEro(Kortti pienempi, Kortti suurempi) {
		return pienempi.getArvo() == suurempi.getArvo() - 1;
	}
	
	public static boolean samaaMaataJaYhdenEro(Kortti pienempi, Kortti suurempi) {
		return samaMaa(pienempi, suurempi) && yhdenEro(pienempi, suurempi);
	}
}
