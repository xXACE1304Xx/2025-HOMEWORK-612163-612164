
package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.LabirintoFake;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {

    private ComandoPosa comandoPosa;
    private Partita partita;
    private IOSimulator io;
    private Stanza stanza;
    private Attrezzo attrezzoTest;

    @Before
    public void setUp() {
        io = new IOSimulator(Collections.emptyList());
        LabirintoFake labirintoFake = new LabirintoFake();
        partita = new Partita(labirintoFake);
        comandoPosa = new ComandoPosa();
        comandoPosa.setIO(io);

        stanza = new Stanza("Stanza di test");
        partita.setStanzaCorrente(stanza);

        attrezzoTest = new Attrezzo("chiave", 1);

        partita.getGiocatore().getBorsa().addAttrezzo(attrezzoTest);
    }

    @Test
    public void testPosaAttrezzoPresenteInBorsa() {
        comandoPosa.setParametro("chiave");
        comandoPosa.esegui(partita);
  
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));

        assertTrue(stanza.hasAttrezzo("chiave"));
 
        assertEquals("Hai lasciato l'attrezzo nella stanza!", io.ultimoOutput());
    }

    @Test
    public void testPosaAttrezzoNonPresenteInBorsa() {
        comandoPosa.setParametro("lanterna"); 
        comandoPosa.esegui(partita);

        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
   
        assertEquals("Attrezzo non trovato nella borsa", io.ultimoOutput());
    }

    @Test
    public void testPosaStanzaPiena() {

        for (int i = 0; i < 10; i++) {
            stanza.addAttrezzo(new Attrezzo("peso" + i, 1));
        }

        comandoPosa.setParametro("chiave");
        comandoPosa.esegui(partita);
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
        assertFalse(stanza.hasAttrezzo("chiave"));
        assertEquals("La stanza è piena!", io.ultimoOutput());
    }
}
