package it.uniroma3.diadia.test;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

    private StanzaBuia stanza;
    private Attrezzo attrezzo = new Attrezzo ("lanterna", 1);

    @Before
    public void setUp() {
        stanza = new StanzaBuia("atrio", attrezzo.getNome());
    }

    @Test
    public void testGetDescrizioneConAttrezzo () {
        stanza.addAttrezzo(attrezzo);
        assertNotEquals("Qui c'e' buio pesto!", stanza.getDescrizione());
    }

    @Test
    public void testGetDescrizioneSenzaAttrezzo () {
        String descrizione = stanza.getDescrizione();
        assertTrue(descrizione.contains("buio"));
    }

}
