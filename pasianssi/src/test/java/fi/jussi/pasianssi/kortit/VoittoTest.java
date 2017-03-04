package fi.jussi.pasianssi.kortit;

import fi.jussi.pasianssi.kortit.Pasianssi;
import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.Kortti;
import fi.jussi.pasianssi.kortit.Maa;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VoittoTest {
	Pasianssi pasianssi;
	
	public VoittoTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		pasianssi = new Pasianssi(4);
		while (!pasianssi.getPakka().tyhja()) {
			pasianssi.nosta10Korttia();
		}
		
		for (int i = 0; i < 10; i++) {
			pasianssi.getPinot().set(i, new Korttipino());
		}
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void josPakkaJaPinotTyhjiaPeliOnVoitettu() {
		assertTrue(pasianssi.voitettu());
	}
	
	@Test
	public void josPakassaOnKorttejaPeliaEiOleVoitettu() {
		pasianssi.getPakka().lisaaKortti(new Kortti(Maa.RUUTU, 2));
		
		assertFalse(pasianssi.voitettu());
	}
	
	@Test
	public void josPinossaOnNakyvaKorttiPeliaEiOleVoitettu() {
		pasianssi.getPinot().get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 7));
		
		assertFalse(pasianssi.voitettu());
	}
	
	@Test
	public void josPinossaOnKaannettyKorttiPeliaEiOleVoitettu() {
		pasianssi.getPinot().get(0).lisaaKaannettyKortti(new Kortti(Maa.RISTI, 8));
		
		assertFalse(pasianssi.voitettu());
	}
}
