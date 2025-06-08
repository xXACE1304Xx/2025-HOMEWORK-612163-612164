package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoFake;

public class LabirintoTest {
    private Labirinto labirinto;


    @Before
    public void setUp () {
    	labirinto = new LabirintoFake();
    }

    @Test 
    public void testGetEntrata_Errore() {
        assertNotEquals("campus", labirinto.getEntrata().getNome());
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
    public void testGetUscita_NotNull() {
        assertNotNull(labirinto.getUscita());
    }
    
    @Test
    public void testEntrata_Uscita_CorrettiENonNull() {
        assertNotNull(labirinto.getEntrata());
        assertEquals("Atrio", labirinto.getEntrata().getNome());
        
        assertNotNull(labirinto.getUscita());
        assertEquals("Laboratorio", labirinto.getUscita().getNome());
    }

}
