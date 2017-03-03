package fi.jussi.pasianssi.kortit;

import fi.jussi.pasianssi.kortit.Pasianssi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PasianssiTest {
	private Pasianssi pasianssi;
	
	public PasianssiTest() {
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
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void nostaminenLisaaNakyviaKorttejaYhdellaJokaPinossa() {
		assertTrue(pasianssi.nosta10Korttia());
		
		for (int i = 0; i < 10; i++) {
			assertEquals(pasianssi.getPinot().get(i).getNakyvat().korttimaara(), 2);
		}
	}
	
	@Test
	public void tyhjastaPakastaEiVoiNostaa() {
		for (int i = 0; i < 5; i++) {
			pasianssi.nosta10Korttia();
		}
		
		assertFalse(pasianssi.nosta10Korttia());
	}
	
	@Test
	public void keskeneraistaPeliaEiOleVoitettu() {
		assertFalse(pasianssi.voitettu());
	}
	
	@Test
	public void josPakassaOnKorttejaPeliaEiOleVoitettu() {
		for (Korttipino pino : pasianssi.getPinot()) {
			while (!pino.getKaannetyt().empty()) {
				pino.lisaaNakyvaKortti(pino.getKaannetyt().pop());
			}
			pino.setNakyvat(null);
		}
		
		assertFalse(pasianssi.voitettu());
	}
	
	@Test
	public void josPinotJaPakkaTyhjiaPeliVoitettu() {
		while (pasianssi.nosta10Korttia());
		
		for (Korttipino pino : pasianssi.getPinot()) {
			while (!pino.getKaannetyt().empty()) {
				pino.lisaaNakyvaKortti(pino.getKaannetyt().pop());
			}
			pino.setNakyvat(null);
		}
		
		assertTrue(pasianssi.voitettu());
	}
}
