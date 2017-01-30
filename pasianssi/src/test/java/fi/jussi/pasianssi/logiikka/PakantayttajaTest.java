package fi.jussi.pasianssi.logiikka;

import fi.jussi.pasianssi.kortit.Korttipakka;
import fi.jussi.pasianssi.kortit.Maa;
import fi.jussi.pasianssi.logiikka.Pakantayttaja;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PakantayttajaTest {
    
    public PakantayttajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void taytetyssaPakassa13KaikkienMaidenKortteja() {
        Korttipakka pakka = new Korttipakka();
        Pakantayttaja.alustaPeruskorttipakka(pakka);
        
        int herttoja = 0;
        int ruutuja = 0;
        int patoja = 0;
        int risteja = 0;
        
        while (!pakka.tyhja()) {
            Maa maa = pakka.nosta().getMaa();
            
            switch (maa) {
                case HERTTA:
                    herttoja++;
                    break;
                case RUUTU:
                    ruutuja++;
                    break;
                case PATA:
                    patoja++;
                    break;
                case RISTI:
                    risteja++;
                    break;
                default:
                    break;
            }
        }
        
        assertTrue(herttoja == ruutuja && herttoja == patoja && herttoja == risteja && herttoja == 13);
    }
}
