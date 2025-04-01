package diadia.src.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class LabirintoTest {
    private Labirinto labirinto;


    @Before
    public void setUp () {
        labirinto = new Labirinto ();
    }

    @Test 
    public void testGetEntrata_Errore() {
        assertNotEquals("campus", labirinto.getEntrata().getNome());
    }

    @Test
    public void testGetEntrata_Giusto() {
        assertEquals("Atrio", labirinto.getEntrata().getNome());
    }

    @Test
    public void testGetEntrata_NotNull() {
        assertNotNull(labirinto.getEntrata());
    }


    @Test 
    public void testGetUscita_Errore() {
        assertNotEquals("campus", labirinto.getUscita().getNome());
    }

    @Test
    public void testGetUscita_Giusto() {
        assertEquals("Biblioteca", labirinto.getUscita().getNome());
    }

    @Test
    public void testGetUscita_NotNull() {
        assertNotNull(labirinto.getUscita());
    }
}