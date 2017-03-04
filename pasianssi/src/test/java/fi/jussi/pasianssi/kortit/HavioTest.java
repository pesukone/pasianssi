package fi.jussi.pasianssi.kortit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HavioTest {
	Pasianssi pasianssi;
	
	public HavioTest() {
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
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void josPakkaEiOleTyhjaPeliaEiOleHavitty() {
		pasianssi.getPakka().lisaaKortti(new Kortti(Maa.RUUTU, 12));
		
		assertFalse(pasianssi.havitty());
	}
	
	@Test
	public void josJokuKorttipinoistaOnTyhjaPeliaEiOleHavitty() {
		pasianssi.getPinot().set(0, new Korttipino());
		
		assertFalse(pasianssi.havitty());
	}
}
