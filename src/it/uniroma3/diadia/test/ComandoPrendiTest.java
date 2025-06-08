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
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPrendiTest {

    private ComandoPrendi comandoPrendi;
    private Partita partita;
    private IOSimulator io;
    private Stanza stanza;
    private Attrezzo attrezzoTest;

    @Before
    public void setUp() {
        io = new IOSimulator(Collections.emptyList());
        LabirintoFake labirintoFake = new LabirintoFake();
        partita = new Partita(labirintoFake);
        
        comandoPrendi = new ComandoPrendi();
        comandoPrendi.setIO(io);

        stanza = labirintoFake.getEntrata();  
        attrezzoTest = new Attrezzo("chiave", 1);
        stanza.addAttrezzo(attrezzoTest);
        partita.setStanzaCorrente(stanza);
    }

    @Test
    public void testPrendiAttrezzoPresente() {
        comandoPrendi.setParametro("chiave");
        comandoPrendi.esegui(partita);
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
        assertFalse(stanza.hasAttrezzo("chiave"));
        assertEquals("Attrezzo preso!", io.ultimoOutput());

    }

    @Test
    public void testPrendiAttrezzoNonPresente() {
        comandoPrendi.setParametro("martello");
        comandoPrendi.esegui(partita);
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
        assertEquals("Attrezzo non trovato nella stanza", io.ultimoOutput()); 
    }

    @Test
    public void testPrendiConBorsaPiena() {
        for (int i = 0; i < 10; i++) 
            partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("peso" + i, 1));
        
        comandoPrendi.setParametro("chiave");
        comandoPrendi.esegui(partita);
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
        assertTrue(stanza.hasAttrezzo("chiave"));
        assertEquals("Non hai abbastanza spazio nella borsa!", io.ultimoOutput());
    }
}
