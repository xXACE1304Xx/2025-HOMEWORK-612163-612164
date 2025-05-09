package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


public class ComandoGuarda implements Comando {
	
	private IO IO;
	
	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione().toString());
		IO.mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
		IO.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}

	@Override
	public void setParametro (String parametro) {}

	@Override
	public void setIO(IO IO) {
		this.IO = IO;
	}

	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}