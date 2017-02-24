package fi.jussi.pasianssi.kortit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NakyvaKorttiTest {
	NakyvaKortti pino;
	NakyvaKortti toinen;
    
	public NakyvaKorttiTest() {
	}
    
	@BeforeClass
	public static void setUpClass() {
	}
    
	@AfterClass
	public static void tearDownClass() {
	}
    
	@Before
	public void setUp() {
		pino = new NakyvaKortti(new Kortti(Maa.HERTTA, 4));
		toinen = new NakyvaKortti(new Kortti(Maa.RISTI, 12));
	}
    
	@After
	public void tearDown() {
	}
    
	@Test
	public void hantaToimiiYhdenKortinPinolla() {
		assertEquals(pino.hanta(), pino);
	}
    
	@Test
	public void hantaPalauttaaViimeisenKortin() {
		NakyvaKortti lisattava = new NakyvaKortti(new Kortti(Maa.HERTTA, 11));
		pino.lisaa(lisattava);
        
		assertEquals(pino.hanta(), lisattava);
	}
    
	@Test
	public void paaToimiiYhdenKortinPinolla() {
		assertEquals(pino.paa(), pino);
	}
    
	@Test
	public void paaPalauttaaEnsimmaisenKortin() {
		NakyvaKortti lisattava = new NakyvaKortti(new Kortti(Maa.RUUTU, 5));
		pino.lisaa(lisattava);
        
		assertEquals(pino.paa(), pino);
	}
    
	@Test
	public void pinoonVoiLisataTavallisenKortin() {
		Kortti tavallinen = new Kortti(Maa.RISTI, 3);
		pino.lisaa(tavallinen);
        
		assertEquals(pino.hanta().getKortti(), tavallinen);
	}

	@Test
	public void lisattyKorttiLisataanPinonLoppuun() {
		pino.lisaa(new NakyvaKortti(new Kortti(Maa.PATA, 2)));
		pino.lisaa(toinen);
        
		assertEquals(pino.hanta(), toinen);
	}
    
	@Test
	public void kortitLisataanOikeassaJarjestyksessa() {
		Kortti viimeinen = new Kortti(Maa.RUUTU, 2);
		Kortti toiseksiViimeinen = new Kortti(Maa.HERTTA, 8);
        
		pino.lisaa(new Kortti(Maa.RISTI, 9));
		pino.lisaa(toiseksiViimeinen);
		pino.lisaa(viimeinen);
        
		assertEquals(pino.hanta().getKortti(), viimeinen);
		assertEquals(pino.hanta().getEdellinen().getKortti(), toiseksiViimeinen);
	}
    
	@Test
	public void siirtaminenPoistaaViitteenEdellisestaKortista() {
		pino.lisaa(new Kortti(Maa.RUUTU, 2));
		pino.hanta().siirra(toinen);
        
		assertNull(pino.getSeuraava());
	}
    
	@Test
	public void siirrettyKorttiViittaaSiirrettyynPinoon() {
		pino.lisaa(new Kortti(Maa.PATA, 8));
		NakyvaKortti siirrettava = pino.hanta();
		siirrettava.siirra(toinen);
        
		assertEquals(siirrettava.getEdellinen(), toinen);
	}
    
	@Test
	public void useanKortinVoiSiirtaaKerralla() {
		Kortti ensimmainen = new Kortti(Maa.RUUTU, 7);
		Kortti viimeinen = new Kortti(Maa.RISTI, 4);
        
		pino.lisaa(ensimmainen);
		pino.lisaa(viimeinen);
        
		pino = pino.getSeuraava();
		pino.siirra(toinen);
        
		assertEquals(toinen.hanta().getEdellinen().getKortti(), ensimmainen);
		assertEquals(toinen.hanta().getKortti(), viimeinen);
	}
}

