package it.uniroma3.diadia.test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.LabirintoFake;

public class IOSimulatorTest {

	@Test
	public void testSimulazioneVittoria() throws Exception {
	    List<String> comandi = Arrays.asList(
	        "prendi lanterna",
	        "vai nord",
	        "prendi libro",
	        "vai est",
	        "prendi chiave"
	    );
	    LabirintoFake labirintoFake = new LabirintoFake();
	    IOSimulator io = new IOSimulator(comandi);
	    DiaDia gioco = new DiaDia(labirintoFake, io);
	    gioco.gioca();

	    
	    assertTrue(io.getTuttiOutput().stream().anyMatch(m -> m.contains("Hai vinto!")));
	}

	
	@Test
	public void testMessaggiIOSimulator() {
	    List<String> input = Arrays.asList("fine");
	    IOSimulator io = new IOSimulator(input);
	    io.mostraMessaggio("Hai perso! I CFU sono finiti.");
	    io.mostraMessaggio("Grazie di aver giocato!");

	    assertTrue(io.getTuttiOutput().stream().anyMatch(s -> s.contains("Hai perso! I CFU sono finiti.")));
	    assertTrue(io.getTuttiOutput().stream().anyMatch(s -> s.contains("Grazie di aver giocato!")));
	}

}
