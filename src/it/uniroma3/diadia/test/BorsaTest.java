package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private Borsa borsa;
	private Attrezzo torcia;
	private Attrezzo aria;

	@Before
	public void setUp() {
		borsa = new Borsa(); 
		torcia = new Attrezzo("torcia", 3);
		aria = new Attrezzo("aria", 0);
	}

	@Test
	public void testAddAttrezzo_Peso() {
		assertEquals(0, borsa.getPeso());
		assertTrue(borsa.addAttrezzo(torcia));  
		assertEquals(3, borsa.getPeso());

		assertTrue(borsa.addAttrezzo(new Attrezzo("corda", 4))); 
		assertEquals(7, borsa.getPeso());

		assertTrue(borsa.addAttrezzo(new Attrezzo("piccone", 3))); 
		assertEquals(10, borsa.getPeso());

		assertFalse(borsa.addAttrezzo(new Attrezzo("pietra", 1))); 
	}

	@Test
	public void testAddAttrezzo_SenzaLimiteNumero() {
		for (int i = 1; i <= 50; i++) {
			assertTrue(borsa.addAttrezzo(new Attrezzo("attrezzo" + i, 0))); 
		}
		assertEquals(0, borsa.getPeso()); 
		assertFalse(borsa.isEmpty());
	}

	@Test
	public void testAddAttrezzo_Ricerca() {
		assertFalse(borsa.addAttrezzo(null)); 
		assertNull(borsa.getAttrezzo("torcia")); 

		assertTrue(borsa.addAttrezzo(torcia));
		assertEquals(torcia, borsa.getAttrezzo("torcia"));
	}

	@Test
	public void testRemoveAttrezzo_Singolo() {
		assertTrue(borsa.isEmpty());
		assertFalse(borsa.hasAttrezzo("torcia"));
		assertFalse(borsa.removeAttrezzo("torcia")); 

		assertTrue(borsa.addAttrezzo(torcia));
		assertFalse(borsa.isEmpty());

		assertFalse(borsa.removeAttrezzo("")); 
		assertTrue(borsa.removeAttrezzo("torcia")); 
		assertTrue(borsa.isEmpty());
	}

	@Test
	public void testRemoveAttrezzo_ElementoIntermedio() {
		for (int i = 1; i <= 10; i++) {
			assertTrue(borsa.addAttrezzo(new Attrezzo("attrezzo" + i, 0)));
		}

		String nomeCentrale = "attrezzo5";
		assertTrue(borsa.removeAttrezzo(nomeCentrale));
		assertFalse(borsa.hasAttrezzo(nomeCentrale));

		assertTrue(borsa.addAttrezzo(new Attrezzo("extra", 0))); 

		for (int i = 1; i <= 10; i++) {
			if (i == 5) continue;
			assertTrue(borsa.hasAttrezzo("attrezzo" + i));
		}
		assertTrue(borsa.hasAttrezzo("extra"));
	}

	@Test
	public void testRemoveAttrezzo_Multipli() {
		for (int i = 1; i <= 10; i++) {
			assertTrue(borsa.addAttrezzo(new Attrezzo("attrezzo" + i, 0)));
		}
		for (int i = 1; i <= 10; i++) {
			assertTrue(borsa.removeAttrezzo("attrezzo" + i));
		}
		assertTrue(borsa.isEmpty());
	}
}
