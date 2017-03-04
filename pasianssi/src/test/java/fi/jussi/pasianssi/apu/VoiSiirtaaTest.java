package fi.jussi.pasianssi.apu;

import fi.jussi.pasianssi.kortit.Kortti;
import fi.jussi.pasianssi.kortit.Korttipino;
import fi.jussi.pasianssi.kortit.Maa;
import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VoiSiirtaaTest {
	List<Korttipino> pinot;
	
	public VoiSiirtaaTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		pinot = new ArrayList();
		for (int i = 0; i < 3; i++) {
			pinot.add(new Korttipino());
		}
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void tyhjaanPinoonVoiSiirtaa() {
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 4));
		assertTrue(Kortinsiirtaja.voiSiirtaa(pinot, pinot.get(0).getNakyvat().hanta()));
	}
	
	@Test
	public void arvoltaanOikeanKortinPaalleVoiSiirtaa() {
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 4));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 5));
		pinot.get(2).lisaaNakyvaKortti(new Kortti(Maa.RISTI, 9));
		
		assertTrue(Kortinsiirtaja.voiSiirtaa(pinot, pinot.get(0).getNakyvat().hanta()));
	}
	
	@Test
	public void josSopivaaKorttiaEiOleEiVoiSiirtaa() {
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 4));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.HERTTA, 12));
		pinot.get(2).lisaaNakyvaKortti(new Kortti(Maa.PATA, 8));
		
		assertFalse(Kortinsiirtaja.voiSiirtaa(pinot, pinot.get(0).getNakyvat().hanta()));
	}
	
	@Test
	public void korttiaEiVoiSiirtaaToisenAlta() {
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 4));
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.RISTI, 1));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.HERTTA, 12));
		pinot.get(2).lisaaNakyvaKortti(new Kortti(Maa.PATA, 8));
		
		assertFalse(Kortinsiirtaja.voiSiirtaa(pinot, pinot.get(0).getNakyvat().hanta()));
	}
	
	@Test
	public void metodiToimiiMonellaMahdollisellaKohteella() {
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 4));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.RISTI, 5));
		pinot.get(2).lisaaNakyvaKortti(new Kortti(Maa.PATA, 5));
		
		assertTrue(Kortinsiirtaja.voiSiirtaa(pinot, pinot.get(0).getNakyvat().hanta()));
	}
	
	@Test
	public void metodiToimiiSuuremmallaLahdepinolla() {
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 4));
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 3));
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 2));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.RISTI, 5));
		pinot.get(2).lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 12));
		
		assertTrue(Kortinsiirtaja.voiSiirtaa(pinot, pinot.get(0).getNakyvat().hanta()));
	}
	
	@Test
	public void metodiToimiiSuuremmallaKohdepinolla() {
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 4));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.PATA, 7));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.PATA, 6));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.PATA, 5));
		pinot.get(2).lisaaNakyvaKortti(new Kortti(Maa.HERTTA, 12));
		
		assertTrue(Kortinsiirtaja.voiSiirtaa(pinot, pinot.get(0).getNakyvat().hanta()));
	}
	
	@Test
	public void metodiHuomioiMaanLahdepinossa() {
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 4));
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.HERTTA, 3));
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 2));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.PATA, 5));
		pinot.get(2).lisaaNakyvaKortti(new Kortti(Maa.HERTTA, 12));
		
		assertFalse(Kortinsiirtaja.voiSiirtaa(pinot, pinot.get(0).getNakyvat().hanta()));
	}
	
	@Test
	public void siirtoHuomioidaanVainJosUusiSarjaOnSuurempiKuinKumpikaanVahnoista() {
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 5));
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 4));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.PATA, 5));
		pinot.get(2).lisaaNakyvaKortti(new Kortti(Maa.HERTTA, 12));
		
		assertFalse(Kortinsiirtaja.voiSiirtaa(pinot, pinot.get(0).getNakyvat().hanta()));
	}
	
	@Test
	public void toisenMaanKortinPaalleMahdollista() {
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 4));
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 3));
		pinot.get(0).lisaaNakyvaKortti(new Kortti(Maa.PATA, 2));
		pinot.get(1).lisaaNakyvaKortti(new Kortti(Maa.RUUTU, 5));
		pinot.get(2).lisaaNakyvaKortti(new Kortti(Maa.HERTTA, 12));
		
		assertTrue(Kortinsiirtaja.voiSiirtaa(pinot, pinot.get(0).getNakyvat().hanta()));
	}
}
