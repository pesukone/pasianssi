package fi.jussi.pasianssi.kortit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttipinoTest {
    Korttipino pino;
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
        pino = new Korttipino();
        toinen = new Korttipino();
    }
    
    @After
		public void tearDown() {
    }
    
    @Test
    public void korttiaVoiSiirtaaPinostaToiseen() {
        pino.lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 8));
        pino.lisaaNakyvaKortti(new Kortti(Maa.RISTI, 4));
        
        pino.siirraKortti(pino.getNakyvat().hanta(), toinen);
        
        assertEquals(toinen.korttimaara(), 1);
    }
	
    @Test
		public void nakyvanKortinVoiLisata() {
		pino.lisaaNakyvaKortti(new Kortti(Maa.HERTTA, 10));
		
		assertEquals(pino.korttimaara(), 1);
    }
	
    @Test
	public void alustettuPinoOnTyhja() {
		assertTrue(pino.tyhja());
    }
        
    @Test
    public void kaannettyKorttiLisataanPinoon() {
        Kortti kortti = new Kortti(Maa.HERTTA, 3);
        pino.lisaaKaannettyKortti(kortti);
        
        assertEquals(pino.getKaannetyt().peek().getMaa(), kortti.getMaa());
        assertEquals(pino.getKaannetyt().peek().getArvo(), kortti.getArvo());
    }
	
    @Test
    public void pinoJossaKaannettyKorttiEiTyhja() {
    	pino.lisaaKaannettyKortti(new Kortti(Maa.RISTI, 2));
		assertFalse(pino.tyhja());
    }
	
    @Test
	public void pinoJossaNakyvaKorttiEiTyhja() {
		pino.lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 12));
    }
    
    @Test
    public void josAinoaKorttiSiirretaanNakyviaEiOle() {
        Kortti siirrettava = new Kortti(Maa.PATA, 13);
        pino.lisaaNakyvaKortti(siirrettava);
        pino.siirraKortti(pino.getNakyvat(), toinen);
        
        assertEquals(pino.getNakyvat(), null);
    }
	
    @Test
    public void josNakyvatKortitLoppuvatKaannetaanKaannettyKortti() {
        Kortti kaannetty = new Kortti(Maa.RISTI, 3);
    	pino.lisaaKaannettyKortti(kaannetty);
    	pino.lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 4));
	
    	pino.siirraKortti(pino.getNakyvat(), toinen);
	
    	assertEquals(pino.getNakyvat().getKortti().getMaa(), kaannetty.getMaa());
        assertEquals(pino.getNakyvat().getKortti().getArvo(), kaannetty.getArvo());
    }
	
	@Test
	public void josKaannetytKortitLoppuvatPinoOnTyhja() {
		pino.lisaaKaannettyKortti(new Kortti(Maa.PATA, 13));
		pino.lisaaNakyvaKortti(new Kortti(Maa.RISTI, 1));
		
		pino.siirraKortti(pino.getNakyvat(), toinen);
		pino.siirraKortti(pino.getNakyvat(), toinen);
		
		assertEquals(pino.getNakyvat(), null);
		assertEquals(pino.getKaannetyt().size(), 0);
	}
	
	@Test
	public void onPinossaToimii() {
		NakyvaKortti kortti = new NakyvaKortti(new Kortti(Maa.PATA, 5));
		pino.setNakyvat(kortti);
		
		assertTrue(pino.onPinossa(kortti));
		assertFalse(pino.onPinossa(new NakyvaKortti(new Kortti(Maa.RUUTU, 4))));
	}
	
	@Test
	public void onPinossaToimiiIsommassaPinossa() {
		pino.setNakyvat(new NakyvaKortti(new Kortti(Maa.RISTI, 7)));
		NakyvaKortti kortti = new NakyvaKortti(new Kortti(Maa.RUUTU, 10));
		
		pino.getNakyvat().lisaa(kortti);
		
		assertTrue(pino.onPinossa(kortti));
	}
	
	@Test
	public void onPinossaEiHajoaTyhjaanPinoon() {
		assertFalse(pino.onPinossa(new NakyvaKortti(new Kortti(Maa.HERTTA, 6))));
	}
	
	@Test
	public void onPinossaToimiiUseammallaSamallaKortilla() {
		Kortti kortti = new Kortti(Maa.RISTI, 12);
		NakyvaKortti sama = new NakyvaKortti(kortti);
		NakyvaKortti myosSama = new NakyvaKortti(kortti);
		
		pino.setNakyvat(sama);
		pino.getNakyvat().lisaa(myosSama);
		
		assertTrue(pino.onPinossa(sama));
	}
}
