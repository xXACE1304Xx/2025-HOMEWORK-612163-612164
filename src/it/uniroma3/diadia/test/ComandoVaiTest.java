package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.LabirintoFake;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {
    
    private Partita partita;
    private ComandoVai comandoVai;
    private Stanza atrio;
    private Stanza biblioteca;
    private IOSimulator IO;  
    
    @Before
    public void setUp() {
        IO = new IOSimulator(Collections.emptyList());
        LabirintoFake labirintoFake = new LabirintoFake();
        partita = new Partita(labirintoFake);
        comandoVai = new ComandoVai();
        comandoVai.setIO(IO);
        
        atrio = new Stanza("Atrio");
        biblioteca = new Stanza("Biblioteca");
        
        atrio.impostaStanzaAdiacente(Direzione.nord, biblioteca);
        biblioteca.impostaStanzaAdiacente(Direzione.sud, atrio);
        
        partita.setStanzaCorrente(atrio);
    }

    @Test
    public void testEseguiDirezioneValida() {
        comandoVai.setParametro("nord");
        comandoVai.esegui(partita);
        assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
        assertEquals(19, partita.getGiocatore().getCfu());
        assertEquals("Biblioteca", IO.ultimoOutput());  
    }

    @Test
    public void testEseguiDirezioneNonValida() {
        comandoVai.setParametro("est");
        comandoVai.esegui(partita);
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());
        assertEquals(20, partita.getGiocatore().getCfu());
        assertEquals("Direzione inesistente", IO.ultimoOutput()); 
    }

    @Test
    public void testEseguiSenzaParametro() {
        comandoVai.setParametro(null);
        comandoVai.esegui(partita);
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());
        assertEquals(20, partita.getGiocatore().getCfu());
        assertEquals("Dove vuoi andare? Devi specificare una direzione", IO.ultimoOutput());
    }

    @Test
    public void testEseguiParametroVuoto() {
        comandoVai.setParametro("");
        comandoVai.esegui(partita);
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());
        assertEquals(20, partita.getGiocatore().getCfu());
        assertEquals("Direzione non valida", IO.ultimoOutput());
    }
}
