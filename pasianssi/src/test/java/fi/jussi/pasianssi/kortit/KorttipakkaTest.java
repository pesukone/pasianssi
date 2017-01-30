package fi.jussi.pasianssi.kortit;

import fi.jussi.pasianssi.logiikka.Pakantayttaja;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttipakkaTest {
    Korttipakka pakka;
    
    public KorttipakkaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pakka = new Korttipakka();
        Pakantayttaja.alustaPeruskorttipakka(pakka);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void korttiLisataanPakkaan() {
        int koko = pakka.korttienMaara();
        Kortti lisattava = new Kortti(Maa.HERTTA, 4);
        pakka.lisaaKortti(lisattava);
        
        assertEquals(pakka.korttienMaara(), koko + 1);
    }
    
    @Test
    public void nostettuKorttiPoistetaanPakasta() {
        int koko = pakka.korttienMaara();
        Kortti paalimmainen = pakka.nosta();
        
        assertEquals(pakka.korttienMaara(), koko - 1);
    }
    
    @Test
    public void pakkaSekoitetaan() {
        Kortti paalimmainen = pakka.paalimmainen();
        pakka.sekoita();
        
        // Ei huomioi tapausta, jossa päälimmäinen kortti päätyy taas päälimmäiseksi!
        assertNotSame(pakka.paalimmainen(), paalimmainen);
    }
    
    @Test
    public void luotuPakkaOnTyhja() {
        pakka = new Korttipakka();
        
        assertTrue(pakka.tyhja());
    }
	
	@Test
	public void pakkaJossaOnKorttiEiOleTyhja() {
		assertFalse(pakka.tyhja());
	}
}
