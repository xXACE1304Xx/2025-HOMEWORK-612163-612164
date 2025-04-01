package diadia.src.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class PartitaTest {
	private Partita partita;
	
	@Before
    public void setUp() {
        partita = new Partita();
    }
    
    @Test
    public void testIsFinita_Comando() {
    	assertFalse(partita.isFinita());
    	partita.setFinita();
    	assertTrue(partita.isFinita());
    }
    
    @Test
    public void testIsFinita_Vittoria() {
    	assertFalse(partita.isFinita());
    	partita.setStanzaCorrente(partita.getStanzaVincente());
    	assertTrue(partita.isFinita());
    }
    
    @Test
    public void testIsFinita_Sconfitta() {
    	assertFalse(partita.isFinita());
    	partita.getGiocatore().setCfu(0);;
    	assertTrue(partita.isFinita());
    } 
 }