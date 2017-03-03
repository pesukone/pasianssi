package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Kortti;
import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.Maa;
import fi.jussi.pasianssi.kortit.NakyvaKortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KortinsiirtajaTest {
	Korttipino pino;
	Korttipino toinen;
	
	public KortinsiirtajaTest() {
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
	public void korttiaVoiSiirtaaTyhjaanPinoon() {
		NakyvaKortti siirrettava = new NakyvaKortti(new Kortti(Maa.RUUTU, 8));
		pino.setNakyvat(siirrettava);
        
		assertTrue(Kortinsiirtaja.siirraKortti(pino, pino.getNakyvat().hanta(), toinen));
        
		assertEquals(toinen.getNakyvat(), siirrettava);
	}
	
	@Test
	public void yhdenKortinSiirtaminenToimii() {
		toinen.lisaaNakyvaKortti(new Kortti(Maa.RISTI, 4));
		NakyvaKortti siirrettava = new NakyvaKortti(new Kortti(Maa.RISTI, 3));
		pino.setNakyvat(siirrettava);
		
		assertTrue(Kortinsiirtaja.siirraKortti(pino, pino.getNakyvat(), toinen));
		
		assertEquals(toinen.getNakyvat().hanta(), siirrettava);
	}
	
	@Test
	public void monenKortinSiirtaminenToimii() {
		toinen.lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 7));
		NakyvaKortti siirrettava = new NakyvaKortti(new Kortti(Maa.RUUTU, 6));
		pino.setNakyvat(siirrettava);
		pino.lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 5));
		
		assertTrue(Kortinsiirtaja.siirraKortti(pino, pino.getNakyvat(), toinen));
		
		assertEquals(toinen.getNakyvat().getSeuraava(), siirrettava);
		assertEquals(toinen.getNakyvat().hanta().getKortti().getMaa(), Maa.RUUTU);
		assertEquals(toinen.getNakyvat().hanta().getKortti().getArvo(), 5);
	}
	
	@Test
	public void montaKorttiaVoiSiirtaaVaaranMaanKortinPaalle() {
		toinen.lisaaNakyvaKortti(new Kortti(Maa.RISTI, 7));
		NakyvaKortti siirrettava = new NakyvaKortti(new Kortti(Maa.RUUTU, 6));
		pino.setNakyvat(siirrettava);
		pino.lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 5));
		
		assertTrue(Kortinsiirtaja.siirraKortti(pino, pino.getNakyvat(), toinen));
		
		assertEquals(toinen.getNakyvat().getSeuraava(), siirrettava);
		assertEquals(toinen.getNakyvat().hanta().getKortti().getMaa(), Maa.RUUTU);
		assertEquals(toinen.getNakyvat().hanta().getKortti().getArvo(), 5);
	}
	
	@Test
	public void vaaranArvoinenKorttiJatetaanSiirtamatta() {
		NakyvaKortti kortti = new NakyvaKortti(new Kortti(Maa.RUUTU, 12));
		NakyvaKortti toinenKortti = new NakyvaKortti(new Kortti(Maa.HERTTA, 3));
		
		pino.setNakyvat(kortti);
		toinen.setNakyvat(toinenKortti);
		
		Kortinsiirtaja.siirraKortti(toinen, toinen.getNakyvat(), pino);
		
		assertEquals(pino.getNakyvat().hanta(), kortti);
		assertEquals(toinen.getNakyvat().hanta(), toinenKortti);
	}
	
	@Test
	public void josAinoaKorttiSiirretaanNakyviaEiOle() {
		Kortti siirrettava = new Kortti(Maa.PATA, 13);
		pino.lisaaNakyvaKortti(siirrettava);
		Kortinsiirtaja.siirraKortti(pino, pino.getNakyvat(), toinen);
        
		assertEquals(pino.getNakyvat(), null);
	}
	
	@Test
	public void josNakyvatKortitLoppuvatKaannetaanKaannettyKortti() {
		Kortti kaannetty = new Kortti(Maa.RISTI, 3);
		pino.lisaaKaannettyKortti(kaannetty);
		pino.lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 4));
		
		Kortinsiirtaja.siirraKortti(pino, pino.getNakyvat(), toinen);
		
		assertEquals(pino.getNakyvat().getKortti().getMaa(), kaannetty.getMaa());
		assertEquals(pino.getNakyvat().getKortti().getArvo(), kaannetty.getArvo());
	}
	
	@Test
	public void josKaannetytKortitLoppuvatPinoOnTyhja() {
		pino.lisaaKaannettyKortti(new Kortti(Maa.PATA, 3));
		pino.lisaaNakyvaKortti(new Kortti(Maa.RISTI, 4));
		
		Kortinsiirtaja.siirraKortti(pino, pino.getNakyvat(), toinen);
		Kortinsiirtaja.siirraKortti(pino, pino.getNakyvat(), toinen);
		
		assertEquals(pino.getNakyvat(), null);
		assertEquals(pino.getKaannetyt().size(), 0);
	}
}
