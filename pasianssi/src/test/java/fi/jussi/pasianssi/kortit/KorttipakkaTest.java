package fi.jussi.pasianssi.kortit;

import fi.jussi.pasianssi.Pakantayttaja;
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
        this.pakka = new Korttipakka();
        Pakantayttaja.tayta52KortinPakka(this.pakka);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void korttiLisataanPakkaan() {
        int koko = this.pakka.korttienMaara();
        Kortti lisattava = new Kortti(Maa.HERTTA, 4);
        this.pakka.lisaaKortti(lisattava);
        
        assertEquals(this.pakka.korttienMaara(), koko + 1);
    }
    
    @Test
    public void nostettuKorttiPoistetaanPakasta() {
        int koko = this.pakka.korttienMaara();
        Kortti paalimmainen = this.pakka.nostaKortti();
        
        assertEquals(this.pakka.korttienMaara(), koko - 1);
    }
    
    @Test
    public void pakkaSekoitetaan() {
        Kortti paalimmainen = this.pakka.paalimmainen();
        this.pakka.sekoita();
        
        assertNotSame(this.pakka.paalimmainen(), paalimmainen);
    }
    
    @Test
    public void luotuPakkaOnTyhja() {
        this.pakka = new Korttipakka();
        
        assertTrue(this.pakka.tyhja());
    }
    
}
