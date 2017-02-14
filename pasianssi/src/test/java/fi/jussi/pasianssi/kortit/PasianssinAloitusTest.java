package fi.jussi.pasianssi.kortit;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PasianssinAloitusTest {
    Pasianssi pasianssi;
    
    public PasianssinAloitusTest() {
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
    public void pasianssissa10Pinoa() {
        assertEquals(pasianssi.getPinot().size(), 10);
    }
    
    @Test
    public void pinoissa54Korttia() {
        int kortteja = 0;
        List<Korttipino> pinot = pasianssi.getPinot();
        
        for (Korttipino pino : pinot) {
            kortteja += pino.korttimaara();
        }
		
		assertEquals(54, kortteja);
    }
	
    @Test
    public void pakassa50Korttia() {
		int kortteja = 0;
		Korttipakka pakka = pasianssi.getPakka();
	
		kortteja += pakka.korttimaara();
	
		assertEquals(50, kortteja);
    }
    
    @Test
    public void pakkaSekoitettuPelinAlussa() {
        Korttipakka ensimmainen = pasianssi.getPakka();
        
        pasianssi = new Pasianssi();
        Korttipakka toinen = pasianssi.getPakka();
        
        // Ei huomioi tapausta, jossa päälimmäinen sekoitetaan uudestaan päälimmäiseksi
        assertNotSame(ensimmainen.paalimmainen(), toinen.paalimmainen());
    }
}
