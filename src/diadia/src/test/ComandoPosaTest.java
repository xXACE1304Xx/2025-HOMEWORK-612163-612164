package diadia.src.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
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
        io = new IOSimulator(new String[0]);
        partita = new Partita();
        comandoPosa = new ComandoPosa();
        comandoPosa.setIO(io);

        stanza = new Stanza("Stanza di test");
        attrezzoTest = new Attrezzo("chiave", 1);

        partita.setStanzaCorrente(stanza);
        partita.getGiocatore().getBorsa().addAttrezzo(attrezzoTest);
    }

    @Test
    public void testPosaAttrezzoPresenteInBorsa() {
        comandoPosa.setParametro("chiave");
        comandoPosa.esegui(partita);
        assertTrue(stanza.hasAttrezzo("chiave"));
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
        assertEquals("Hai lascaito l'attrezzo nella stanza!", io.ultimoOutput());
    }

    @Test
    public void testPosaAttrezzoNonPresenteInBorsa() {
        comandoPosa.setParametro("lanterna"); 
        comandoPosa.esegui(partita);
        assertFalse(stanza.hasAttrezzo("lanterna"));
        assertEquals("Attrezzo non trovato nella borsa", io.ultimoOutput());
    }

    @Test
    public void testPosaConStanzaPiena() {
        for (int i = 0; i < 10; i++) 
            stanza.addAttrezzo(new Attrezzo("oggetto" + i, 1));
       
        comandoPosa.setParametro("chiave");
        comandoPosa.esegui(partita);
        assertFalse(stanza.hasAttrezzo("chiave")); 
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("chiave")); 
        assertEquals("La stanza è piena!", io.ultimoOutput());
    }
}
