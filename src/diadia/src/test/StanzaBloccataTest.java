package diadia.src.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	private StanzaBloccata stanza;
	private Attrezzo attrezzo = new Attrezzo("chiave", 1);
	
	@Before
	public void setUp () {
		stanza = new StanzaBloccata ("biblioteca", "nord", "chiave");
	}
	
	@Test
	public void testGetDescrizioneConChiave () {
		stanza.addAttrezzo(attrezzo);
		String descrizione = stanza.getDescrizione();
		assertTrue(descrizione.contains("sbloccata"));
	}
	
	@Test
	public void testGetDescrizioneSenzaChiave () {
		String descrizione = stanza.getDescrizione();
		assertTrue(descrizione.contains("bloccata"));
	}
	
	@Test
	public void testGetStanzaAdiacenteBloccataChiave () {
		Stanza biblioteca = new Stanza("biblioteca");
		stanza.impostaStanzaAdiacente("nord", biblioteca);
		stanza.addAttrezzo(attrezzo);
		assertSame(biblioteca, stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacenteBloccataNoChiave () {
		Stanza biblioteca = new Stanza("biblioteca");
		stanza.impostaStanzaAdiacente("nord", biblioteca);
		assertSame(stanza, stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacenteSbloccata () {
		Stanza laboratorio = new Stanza("laboratorio");
		stanza.impostaStanzaAdiacente("sud", laboratorio);
		assertSame(laboratorio, stanza.getStanzaAdiacente("sud"));
	}
}
