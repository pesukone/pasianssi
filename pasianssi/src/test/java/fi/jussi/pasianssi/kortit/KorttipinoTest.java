package fi.jussi.pasianssi.kortit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttipinoTest {
    Korttipino pino;
    Korttipino toinen;
    
    public KorttipinoTest() {
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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void korttiaVoiSiirtaaPinostaToiseen() {
        pino.lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 8));
        pino.lisaaNakyvaKortti(new Kortti(Maa.RISTI, 4));
        
        pino.siirraKortti(toinen);
        
        assertFalse(toinen.eiNakyviaKortteja());
    }
	
	@Test
	public void näkyvanKortinVoiLisata() {
		pino.lisaaNakyvaKortti(new Kortti(Maa.HERTTA, 10));
		
		assertFalse(pino.eiNakyviaKortteja());
	}
	
	@Test
	public void alustettuPinoOnTyhja() {
		assertTrue(pino.tyhja());
	}
	
	@Test
	public void pinoJossaKaannettyKorttiEiTyhja() {
		pino.lisaaKaannettyKortti(new Kortti(Maa.RISTI, 2));
		assertFalse(pino.tyhja());
	}
	
	@Test
	public void pinoJossaNakyvaKorttiEiTyhja() {
		pino.lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 12));
	}
}
