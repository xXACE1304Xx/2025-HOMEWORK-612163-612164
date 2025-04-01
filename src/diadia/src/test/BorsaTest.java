package diadia.src.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	
	private Borsa borsa;
    private Attrezzo torcia;
    private Attrezzo aria;
    private static int numMax = Borsa.DEFAULT_ATTREZZI_MAX_BORSA;;

    @Before
    public void setUp() {
        borsa = new Borsa();
        torcia = new Attrezzo("torcia", 3);
        aria = new Attrezzo("aria", 0);
    }
	
    @Test  //controlla che il peso venga aggiornato correttamente e che al raggiungimento del peso massimo non vengano più inseriti attrezzi
    public void testAddAttrezzo_Peso() {
    	assertEquals(0, borsa.getPeso());
    	for(int i=3; i<numMax; i+=3) {
    		assertTrue(borsa.addAttrezzo(torcia));
    		assertEquals(i, borsa.getPeso());
    	}
    	assertFalse(borsa.addAttrezzo(torcia));	
    }
    
    @Test	//test numero massiomo di attrezzi
    public void testAddAttrezzo_Inserimento() {
        for(int i=1; i<=numMax; i++)
        	assertTrue(borsa.addAttrezzo(aria));
        assertFalse(borsa.addAttrezzo(aria));
    }
    
    @Test
    public void testAddAttrezzo_Ricerca() {
        assertFalse(borsa.addAttrezzo(null));
        assertEquals(null, borsa.getAttrezzo("torcia"));
        assertTrue(borsa.addAttrezzo(torcia));
        assertEquals(torcia, borsa.getAttrezzo("torcia"));
    }
    
    @Test
    public void testRemoveAttrezzo_Singolo() {
    	assertTrue(borsa.isEmpty());
    	assertFalse(borsa.hasAttrezzo("torcia"));
    	assertFalse(borsa.removeAttrezzo("torcia"));
    	assertTrue(borsa.addAttrezzo(torcia));
    	assertFalse(borsa.isEmpty());
    	assertFalse(borsa.removeAttrezzo(""));
    	assertTrue(borsa.removeAttrezzo("torcia"));
    	assertTrue(borsa.isEmpty());	
    }
    
    @Test //controlla che un elemento centrale venga rimosso correttamente
    public void testRemoveAttrezzo_ElementoIntermedio() {
    	for(int i=1; i<=numMax; i++) 
    		assertTrue(borsa.addAttrezzo(new Attrezzo("attrezzo"+i, 0)));
    	assertTrue(borsa.removeAttrezzo("attrezzo"+(numMax/2)));
    	assertFalse(borsa.hasAttrezzo("attrezzo"+(numMax/2)));
    	assertTrue(borsa.addAttrezzo(new Attrezzo("attrezzo"+(numMax+1), 0)));
    	assertTrue(borsa.hasAttrezzo("attrezzo"+(numMax+1)));
    	
    	for(int i=1; i<=numMax+1; i++) {
    		if(i == numMax/2)
    			i++ ;
    		assertTrue(borsa.hasAttrezzo("attrezzo"+i)) ;
    	}		
    }
    
    @Test 
    public void testRemoveAttrezzo_Multipli() {
    	for(int i=1; i<=numMax; i++) 
    		assertTrue(borsa.addAttrezzo(new Attrezzo("attrezzo"+i, 0)));
    	for(int i=1; i<=numMax; i++) 
    		assertTrue(borsa.removeAttrezzo("attrezzo"+i));
    	assertTrue(borsa.isEmpty());
    }
}