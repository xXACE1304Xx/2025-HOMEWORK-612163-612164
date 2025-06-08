package it.uniroma3.diadia.test;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class GiocatoreTest {
	private Giocatore giocatore;
	
	@Before
	public void setUp () {
		giocatore = new Giocatore ();
	}
	
	@Test
	public void testGetCfu() {
		assertEquals(20, giocatore.getCfu());
	}
	
	@Test
	public void testGetCfu_Set() {
		giocatore.setCfu(10);
		assertEquals(10, giocatore.getCfu());
	}
	
	@Test
	public void testGetCfu_0() {
		giocatore.setCfu(0);
		assertEquals(0, giocatore.getCfu());
	}
	
	@Test
	public void testGetBorsa_Null() {
		assertNotEquals(null, giocatore.getBorsa());
	}
	
	@Test
	public void testGetBorsa_Vuota() {
		assertTrue(giocatore.getBorsa().isEmpty());
	}
	
	@Test
	public void testGetBorsa_inserimento() {
		assertFalse(giocatore.getBorsa().hasAttrezzo("osso"));
		assertTrue(giocatore.getBorsa().addAttrezzo(new Attrezzo("osso", 1)));
		assertTrue(giocatore.getBorsa().hasAttrezzo("osso"));
		assertTrue(giocatore.getBorsa().removeAttrezzo("osso"));
		assertFalse(giocatore.getBorsa().hasAttrezzo("osso"));
	}
}
