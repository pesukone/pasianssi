package fi.jussi.pasianssi.kortit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttiTest {
    Kortti kortti;
    
    public KorttiTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.kortti = new Kortti(Maa.HERTTA, 5);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void kortillaMaa() {
        assertEquals(this.kortti.getMaa(), Maa.HERTTA);
    }
    
    @Test
    public void kortillaArvo() {
        assertEquals(this.kortti.getArvo(), 5);
    }
    
    
}
