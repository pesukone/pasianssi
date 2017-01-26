package fi.jussi.pasianssi.kortit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttipinoTest {
    Korttipino ensimmainen;
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
        ensimmainen = new Korttipino();
        toinen = new Korttipino();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void korttiaVoiSiirtaaPinostaToiseen() {
        ensimmainen.lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 8));
        ensimmainen.lisaaNakyvaKortti(new Kortti(Maa.RISTI, 4));
        
        ensimmainen.siirraKortti(toinen);
        
        assertFalse(toinen.eiNakyviaKortteja());
    }
}
