package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.ambienti.LabirintoFake;

public class PartitaTest {

    private Partita partita;
    private FabbricaDiComandiFisarmonica fabbrica;
    private IOFake io;

    private static class IOFake implements IO {
        private String ultimoMessaggio;

        @Override
        public void mostraMessaggio(String msg) {
            this.ultimoMessaggio = msg; 
        }

        @Override
        public String leggiRiga() {
            return null; 
        }

        public String getUltimoMessaggio() {
            return ultimoMessaggio;
        }
    }

    @Before
    public void setUp() {
        LabirintoFake labirinto = new LabirintoFake();
        this.io = new IOFake();
        this.partita = new Partita(labirinto);
        this.fabbrica = new FabbricaDiComandiFisarmonica(io);
    }

    private void esegui(String istruzione) {
        Comando comando = fabbrica.costruisciComando(istruzione);
        comando.esegui(partita);
    }

    private void eseguiListaComandi(List<String> comandi) {
        for (String cmd : comandi) {
            esegui(cmd);
        }
    }

    @Test
    public void testSimulazionePartitaConVittoria() {
        eseguiListaComandi(List.of(
            "prendi lanterna",   
            "vai nord",          
            "prendi libro",
            "vai est",           
            "prendi chiave"
        ));

        assertTrue("Partita deve essere vinta", partita.vinta());
        assertTrue("Partita deve essere finita", partita.isFinita());
    }

    @Test
    public void testOggettiInBorsa() {
        eseguiListaComandi(List.of(
            "prendi lanterna",
            "vai nord",
            "prendi libro",
            "vai est",
            "prendi chiave"
        ));

        assertFalse(partita.getStanzaCorrente().hasAttrezzo("lanterna"));
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));

        assertFalse(partita.getStanzaCorrente().hasAttrezzo("libro"));
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("libro"));

        assertFalse(partita.getStanzaCorrente().hasAttrezzo("chiave"));
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));
    }

    @Test
    public void testSimulazionePartitaCompleta() {
        assertEquals("Atrio", partita.getStanzaCorrente().getNome());

        eseguiListaComandi(List.of(
            "prendi lanterna",
            "vai nord",
            "prendi libro",
            "vai est",
            "prendi chiave"
        ));

        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("libro"));
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("chiave"));

        assertEquals("Laboratorio", partita.getStanzaCorrente().getNome());
        assertTrue(partita.vinta());
        assertTrue(partita.isFinita());
    }

}
