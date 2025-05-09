package diadia.src.test;

import static org.junit.Assert.*;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;


public class IOSimulatorTest {

    
    @Test
    public void testSimulazioneVittoria() {
        String[] comandi = {"aiuto", "vai ovest", "guarda", "prendi lanterna", "vai est", "vai sud", "guarda", "posa lanterna", "guarda", "prendi chiave", "vai nord", "posa chiave", "vai nord"};

        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();

        assertTrue(io.ultimoOutput().equals("Hai vinto!") && io.tuttiInputLetti(comandi.length));
    }
    
    @Test
    public void testSimulazioneSconfittaCFUFiniti() {
        String[] comandi = {"vai est", "vai est", "vai est", "vai est", "vai est", "vai est", "vai est", "vai est", "vai est", "vai est", 
                            "vai est", "vai est", "vai est", "vai est", "vai est", "vai est", "vai est", "vai est", "vai est", "vai est"}; //20 movimenti per esauire i CFU

        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();

        assertTrue(io.ultimoOutput().equals("Hai perso! I CFU sono finiti.") && io.tuttiInputLetti(comandi.length));
    }
    
    @Test
    public void testSimulazioneFINE() {
        String[] comandi = {"aiuto", "vai ovest", "guarda", "prendi lanterna", "vai est", "vai sud", "guarda", "posa lanterna", "guarda", "prendi chiave", "vai nord", "vai nord", "guarda", "fine"};

        IOSimulator io = new IOSimulator(comandi);
        DiaDia gioco = new DiaDia(io);
        gioco.gioca();

        assertTrue(io.ultimoOutput().equals("Grazie di aver giocato!") && io.tuttiInputLetti(comandi.length));
    }
}