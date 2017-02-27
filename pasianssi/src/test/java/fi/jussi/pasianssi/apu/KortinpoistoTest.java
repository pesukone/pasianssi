package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.Maa;
import fi.jussi.pasianssi.kortit.Kortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KortinpoistoTest {
	Korttipino pino;
	Korttipino toinen;
	
	public KortinpoistoTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		pino = new Korttipino();
		toinen = new Korttipino();
		
		for (int i = 13; i > 1; i--) {
			pino.lisaaNakyvaKortti(new Kortti(Maa.PATA, i));
		}
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void korttienPoistaminenToimii() {
		toinen.lisaaNakyvaKortti(new Kortti(Maa.PATA, 1));
		Kortinsiirtaja.siirraKortti(toinen, toinen.getNakyvat(), pino);
		
		assertNull(pino.getNakyvat());
	}
}
