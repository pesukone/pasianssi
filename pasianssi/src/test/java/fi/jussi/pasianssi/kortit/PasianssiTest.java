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
		pasianssi = new Pasianssi();
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void nostaminenLisaaNakyviaKorttejaYhdellaJokaPinossa() {
		pasianssi.nosta10Korttia();
		
		for (int i = 0; i < 10; i++) {
			assertEquals(pasianssi.getPinot().get(i).getNakyvat().seuraaviaKortteja(), 2);
		}
	}
	
	@Test
	public void tyhjastaPakastaEiVoiNostaa() {
		for (int i = 0; i < 5; i++) {
			pasianssi.nosta10Korttia();
		}
		
		assertFalse(pasianssi.nosta10Korttia());
	}
}
