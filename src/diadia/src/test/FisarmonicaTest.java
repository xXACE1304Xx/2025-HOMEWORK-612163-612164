package diadia.src.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.comandi.ComandoFine;


public class FisarmonicaTest {

    private FabbricaDiComandiFisarmonica fabbrica;

    @Before
    public void setUp () {
        String[] inputSimulato = {};
        fabbrica = new FabbricaDiComandiFisarmonica (new IOSimulator(inputSimulato));
    }

    @Test
    public void testCostruisciComandoVai () {
        Comando comando = fabbrica.costruisciComando("vai nord");
        assertEquals("nord", comando.getParametro());
    }

    @Test
    public void testCostruisciComandoAiuto () {
        Comando comando = fabbrica.costruisciComando("aiuto");
        assertNull(comando.getParametro());
    }

    @Test
    public void testCostruisciComandoNonValido () {
        Comando comando = fabbrica.costruisciComando("inesistente");
        assertTrue(comando instanceof ComandoNonValido);
    }

    @Test
    public void testCostruisciComandoConParametroUnico () {
        Comando comando = fabbrica.costruisciComando("prendi osso");
        assertEquals("osso", comando.getParametro());
    }

    @Test
    public void testCostruisciComandoConParametroUnico2 () {
        Comando comando = fabbrica.costruisciComando ("posa lanterna");
        assertEquals("lanterna", comando.getParametro());
    }

    @Test
    public void testCostruisciComandoVuoto () {
        Comando comando = fabbrica.costruisciComando("");
        assertTrue(comando instanceof ComandoNonValido);
    }

    @Test
    public void testCostruisciComandoFine () {
        Comando comando = fabbrica.costruisciComando("fine");
        assertTrue(comando instanceof ComandoFine);
    }
}