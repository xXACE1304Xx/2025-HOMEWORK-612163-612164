package diadia.src.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

    private Stanza stanza;
    private Attrezzo lanterna;
    private Attrezzo osso;

    @Before
    public void setUp() {   
        stanza = new Stanza("Atrio");
        lanterna = new Attrezzo("lanterna", 3);
        osso = new Attrezzo("osso", 1);
    }
    
    @Test
    public void testImpostaStanzaAdiacenteNull () {
    	stanza.impostaStanzaAdiacente("sud", null);
    	assertNull(stanza.getStanzaAdiacente("sud"));
    }

    @Test
    public void testImpostaStanzaAdiacente_NuovaStanza() {
        Stanza nuova = new Stanza("biblioteca");
        stanza.impostaStanzaAdiacente("sud", nuova);
        assertEquals(nuova, stanza.getStanzaAdiacente("sud"));
    }
    
    @Test
    public void testImpostastanzaAdiacente_TroppeDirezioni() {
    	stanza.impostaStanzaAdiacente("nord", new Stanza ("Aula N10"));
    	stanza.impostaStanzaAdiacente("sud", new Stanza ("Aula N11"));
    	stanza.impostaStanzaAdiacente("est", new Stanza ("Atrio"));
    	stanza.impostaStanzaAdiacente("ovest", new Stanza ("Biblioteca"));
    	stanza.impostaStanzaAdiacente("nord-ovest", new Stanza ("Campus"));
    	assertNull(stanza.getStanzaAdiacente("nord-est"));
    }
    
    @Test
    public void testGetStanzaAdiacenteNull() {
    	assertNull(stanza.getStanzaAdiacente("nord"));
    }
    
    @Test
    public void testGetStanzaAdiacente_NuovaStanza() {
    	Stanza nuova = new Stanza ("Aula N10");
    	stanza.impostaStanzaAdiacente("nord", nuova);
    	assertEquals(nuova, stanza.getStanzaAdiacente("nord"));
    }
    
    @Test
    public void testGetStanzaAdiacente_DueNuoveStanze() {
    	Stanza nuova1 = new Stanza ("Aula N10");
    	Stanza nuova2 = new Stanza ("Aula N11");
    	stanza.impostaStanzaAdiacente("nord", nuova1);
    	assertEquals(nuova1, stanza.getStanzaAdiacente("nord"));
    	stanza.impostaStanzaAdiacente("sud", nuova2);
    	assertEquals(nuova2, stanza.getStanzaAdiacente("sud"));
    }
    
    @Test
    public void testAddAttrezzo_Null() {
    	assertFalse(stanza.addAttrezzo(null));
    	assertEquals(0, stanza.getNumeroAttrezzi());
    }
    
    @Test
    public void testAddAttrezzo_NuovoAttrezzo() {
    	assertTrue(stanza.addAttrezzo(osso));
    	assertEquals(1, stanza.getNumeroAttrezzi());
    	assertTrue(stanza.hasAttrezzo("osso")); 
    }
    
    @Test
    public void testAddAttrezzo_Troppi() {
    	for (int i=0; i<Stanza.NUMERO_MASSIMO_ATTREZZI; i++)
    		if (stanza.getNumeroAttrezzi() < Stanza.NUMERO_MASSIMO_ATTREZZI)
    			assertTrue(stanza.addAttrezzo(lanterna));
    	assertFalse(stanza.addAttrezzo(lanterna));		
    }
    
    @Test
    public void testRemoveAttrezzo_Null() {
    	assertFalse(stanza.removeAttrezzo(null));
    }
    
    @Test
    public void testRemoveAttrezzo_UnSoloAttrezzo() {
    	assertTrue(stanza.addAttrezzo(osso));
    	assertEquals(1, stanza.getNumeroAttrezzi());
    	assertTrue(stanza.hasAttrezzo("osso")); 
    	assertTrue(stanza.removeAttrezzo(osso));
    	assertEquals(0, stanza.getNumeroAttrezzi());
    	assertFalse(stanza.hasAttrezzo("osso"));
    }
    
    @Test
    public void testRemoveAttrezzo_Inesistente () {
    	assertTrue(stanza.addAttrezzo(osso));
    	assertEquals(1, stanza.getNumeroAttrezzi());
    	assertTrue(stanza.hasAttrezzo("osso"));
    	assertFalse(stanza.removeAttrezzo(lanterna));
    }
}