package diadia.src.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

    private StanzaMagica stanzaMagica;

    @Before
    public void setUp() {
        stanzaMagica = new StanzaMagica("stanza magica", 3);
    }

    @Test
    public void testAttrezzoPrimaDellaSogliaNonModificato() {
        Attrezzo attrezzo = new Attrezzo("spada", 2);
        stanzaMagica.addAttrezzo(attrezzo);

        assertTrue(stanzaMagica.hasAttrezzo("spada"));
        Attrezzo trovato = stanzaMagica.getAttrezzo("spada");
        assertEquals("spada", trovato.getNome());
        assertEquals(2, trovato.getPeso());
    }

    @Test
    public void testAttrezzoDopoLaSogliaModificato() {
        stanzaMagica.addAttrezzo(new Attrezzo("a", 1));
        stanzaMagica.addAttrezzo(new Attrezzo("b", 1));
        stanzaMagica.addAttrezzo(new Attrezzo("c", 1));
        Attrezzo attrezzoMagico = new Attrezzo("osso", 2); 
        stanzaMagica.addAttrezzo(attrezzoMagico);
        assertTrue(stanzaMagica.hasAttrezzo("osso"));
        Attrezzo trovato = stanzaMagica.getAttrezzo("osso");
        assertNotNull(trovato);
        assertEquals("osso", trovato.getNome());  
        assertEquals(4, trovato.getPeso());        
    }

    @Test
    public void testAttrezzoConNomeNonPalindromoModificato() {
        stanzaMagica.addAttrezzo(new Attrezzo("a", 1));
        stanzaMagica.addAttrezzo(new Attrezzo("b", 1));
        stanzaMagica.addAttrezzo(new Attrezzo("c", 1));
        Attrezzo attrezzoMagico = new Attrezzo("lanterna", 3);
        stanzaMagica.addAttrezzo(attrezzoMagico);
        assertFalse(stanzaMagica.hasAttrezzo("lanterna"));
        assertTrue(stanzaMagica.hasAttrezzo("anretnal"));
        Attrezzo trovato = stanzaMagica.getAttrezzo("anretnal");
        assertEquals("anretnal", trovato.getNome());
        assertEquals(6, trovato.getPeso());
    }

    @Test
    public void testAddAttrezzoOltreLimiteArray() {
        for (int i = 0; i < 10; i++) {
            boolean aggiunto = stanzaMagica.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
            assertTrue(aggiunto);
        }

        
        boolean undicesimo = stanzaMagica.addAttrezzo(new Attrezzo("extra", 1));
        assertFalse(undicesimo);
    }
}
