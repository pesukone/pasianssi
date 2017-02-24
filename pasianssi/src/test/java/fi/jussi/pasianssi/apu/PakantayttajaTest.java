package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.apu.Pakantayttaja;
import fi.jussi.pasianssi.kortit.Korttipakka;
import fi.jussi.pasianssi.kortit.Maa;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PakantayttajaTest {
	Korttipakka pakka;
	
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
		pakka = new Korttipakka();
	}
    
	@After
	public void tearDown() {
	}

	@Test
	public void taytetyssaPakassa13KaikkienMaidenKortteja() {
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
		
		assertEquals(herttoja, ruutuja);
		assertEquals(ruutuja, patoja);
		assertEquals(patoja, risteja);
		assertEquals(risteja, 13);
	}
	
	@Test
	public void tuplapakassa26KaikkienMaidenKortteja() {
		Pakantayttaja.alustaTuplakorttipakka(pakka);
		
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
		
		assertEquals(herttoja, ruutuja);
		assertEquals(ruutuja, patoja);
		assertEquals(patoja, risteja);
		assertEquals(risteja, 26);
	}
}
