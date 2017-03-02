package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.Maa;
import fi.jussi.pasianssi.kortit.Kortti;
import fi.jussi.pasianssi.kortit.NakyvaKortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KortinpoistoTest {
	Korttipino pino;
	Korttipino toinen;
	
	public KortinpoistoTest() {
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
		
		for (int i = 13; i > 1; i--) {
			pino.lisaaNakyvaKortti(new Kortti(Maa.PATA, i));
		}
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void korttienPoistaminenToimii() {
		toinen.lisaaNakyvaKortti(new Kortti(Maa.PATA, 1));
		Kortinsiirtaja.siirraKortti(toinen, toinen.getNakyvat(), pino);
		
		assertNull(pino.getNakyvat());
	}
	
	@Test
	public void eriMaanKorttiEiAiheutaPoistamista() {
		NakyvaKortti vaara = new NakyvaKortti(new Kortti(Maa.HERTTA, 1));
		toinen.setNakyvat(vaara);
		Kortinsiirtaja.siirraKortti(toinen, toinen.getNakyvat(), pino);
		
		assertEquals(pino.getNakyvat().hanta(), vaara);
	}
	
	/*@Test
	public void poistoaEiTapahduJosPinossaKorttiPoistettavienPaalla() {
		NakyvaKortti assa = new NakyvaKortti(new Kortti(Maa.PATA, 1));
		NakyvaKortti ylimaarainen = new NakyvaKortti(new Kortti(Maa.RUUTU, 4));
		toinen.setNakyvat(assa);
		ylimaarainen.setEdellinen(toinen.getNakyvat());
		toinen.getNakyvat().setSeuraava(ylimaarainen);
		
		Kortinsiirtaja.siirraKortti(toinen, toinen.getNakyvat(), pino);
		
		assertEquals(pino.getNakyvat().hanta(), ylimaarainen);
		assertEquals(pino.getNakyvat().hanta().getEdellinen(), assa);
	}*/
}
