package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Mago;

public class CaricatoreLabirintoTest {

    private CaricatoreLabirinto caricatore;

    private static final String LABIRINTO_DI_TEST = 
        "Stanze: Atrio, Biblioteca, Laboratorio\n" +
        "Speciali: Buia Biblioteca lanterna, Bloccata Laboratorio nord chiave, Magica StanzaMagica\n" +
        "Entrata: Atrio\n" +
        "Uscita: Laboratorio\n" +
        "Attrezzi: lanterna 3 Atrio, libro 2 Biblioteca, chiave 1 Laboratorio\n" +
        "Collegamenti: Atrio nord Biblioteca, Biblioteca est Laboratorio\n" +
        "Personaggi: Mago Gandalf \"Benvenuto nel labirinto\" Atrio";

    @Before
    public void setUp() throws Exception {
        StringReader sr = new StringReader(LABIRINTO_DI_TEST);
        caricatore = new CaricatoreLabirinto(sr);
        caricatore.carica();
    }

    @Test
    public void testStanzeCreate() {
        assertNotNull(caricatore.getStanzaIniziale());
        assertEquals("Atrio", caricatore.getStanzaIniziale().getNome());

        assertNotNull(caricatore.getStanzaVincente());
        assertEquals("Laboratorio", caricatore.getStanzaVincente().getNome());
    }

    @Test
    public void testAttrezziPosati() {
        Stanza atrio = caricatore.getStanzaIniziale();
        assertTrue(atrio.hasAttrezzo("lanterna"));

        Stanza biblioteca = atrio.getStanzaAdiacente(Direzione.fromString("nord"));
        assertTrue(biblioteca.hasAttrezzo("libro"));

        Stanza laboratorio = biblioteca.getStanzaAdiacente(Direzione.fromString("est"));
        assertTrue(laboratorio.hasAttrezzo("chiave"));
    }

    @Test
    public void testUsciteImpostate() {
        Stanza atrio = caricatore.getStanzaIniziale();
        Stanza biblioteca = atrio.getStanzaAdiacente(Direzione.fromString("nord"));
        assertNotNull(biblioteca);
        assertEquals("Biblioteca", biblioteca.getNome());

        Stanza laboratorio = biblioteca.getStanzaAdiacente(Direzione.fromString("est"));
        assertNotNull(laboratorio);
        assertEquals("Laboratorio", laboratorio.getNome());
    }

    @Test
    public void testStanzeSpeciali() {
        Stanza biblioteca = caricatore.getStanzaIniziale().getStanzaAdiacente(Direzione.fromString("nord"));
        assertTrue(biblioteca instanceof StanzaBuia);

        Stanza laboratorio = biblioteca.getStanzaAdiacente(Direzione.fromString("est"));
        assertTrue(laboratorio instanceof StanzaBloccata);
    }

    @Test
    public void testPersonaggiInseriti() {
        AbstractPersonaggio personaggio = caricatore.getStanzaIniziale().getPersonaggio();
        assertNotNull(personaggio);
        assertTrue(personaggio instanceof Mago);
        assertEquals("Gandalf", personaggio.getNome());
    }

    @Test(expected = FormatoFileNonValidoException.class)
    public void testFormatoFileNonValido() throws Exception {
        StringReader sr = new StringReader("Entrata: Atrio\nUscita: Laboratorio\n");
        CaricatoreLabirinto caricatore2 = new CaricatoreLabirinto(sr);
        caricatore2.carica();
    }
}