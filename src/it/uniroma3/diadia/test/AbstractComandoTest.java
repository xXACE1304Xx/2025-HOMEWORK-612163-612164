package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoFake;
import it.uniroma3.diadia.comandi.AbstractComando;

public class AbstractComandoTest {

    // Classe concreta di test per AbstractComando
    private static class ComandoDiTest extends AbstractComando {
        private boolean eseguito = false;

        @Override
        public void esegui(Partita partita) {
            eseguito = true;
            if (IO != null && parametro != null) {
                IO.mostraMessaggio("Eseguito con parametro: " + parametro);
            }
        }

        public boolean isEseguito() {
            return eseguito;
        }

        public IO getIO() {
            return this.IO;
        }

        @Override
        public String getNome() {
            return "comando di test";
        }
    }

    private ComandoDiTest comando;
    private IOSimulator ioSimulator;
    Labirinto labirintoFake = new LabirintoFake();

    @Before
    public void setUp() {
        comando = new ComandoDiTest();
        List<String> inputList = new ArrayList<>();
        inputList.add("input simulato");
        ioSimulator = new IOSimulator(inputList);
    }

    @Test
    public void testSetParametro_nonNull() {
        comando.setParametro("nord");
        assertEquals("nord", comando.getParametro());
    }

    @Test
    public void testSetParametro_null() {
        comando.setParametro(null);
        assertNull(comando.getParametro());
    }

    @Test
    public void testSetParametro_vuoto() {
        comando.setParametro("");
        assertEquals("", comando.getParametro());
    }

    @Test
    public void testSetIO_nonNull() {
        comando.setIO(ioSimulator);
        assertEquals(ioSimulator, comando.getIO());
    }

    @Test
    public void testEsegui_eseguitoFlag() {
        Partita partita = new Partita(labirintoFake);
        comando.esegui(partita);
        assertTrue(comando.isEseguito());
    }

    @Test
    public void testEsegui_conIOeParametro() {
        Partita partita = new Partita(labirintoFake);
        comando.setIO(ioSimulator);
        comando.setParametro("sud");
        comando.esegui(partita);
        assertTrue(comando.isEseguito());
    }
}