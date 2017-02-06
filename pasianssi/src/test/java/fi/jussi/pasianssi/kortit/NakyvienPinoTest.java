package fi.jussi.pasianssi.kortit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NakyvienPinoTest {
    
    public NakyvienPinoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        NakyvienPino pino = new NakyvienPino(new Kortti(Maa.HERTTA, 4));
        NakyvienPino toinen = new NakyvienPino();
    }
    
    @After
    public void tearDown() {
    }

    
}
