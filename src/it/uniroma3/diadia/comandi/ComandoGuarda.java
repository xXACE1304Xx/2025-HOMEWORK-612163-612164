package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


public class ComandoGuarda extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione().toString());
		IO.mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
		IO.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}

	@Override
	public String getNome() {
		return "guarda";
	}
}