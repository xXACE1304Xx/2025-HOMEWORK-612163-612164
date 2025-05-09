package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


public class ComandoNonValido implements Comando {
	
	private IO IO;

	@Override
	public void esegui (Partita partita) {
		IO.mostraMessaggio("Comando non valido!");
	}

	@Override
	public void setParametro (String parametro) {}

	@Override
	public void setIO(IO IO) {
		this.IO = IO;
	}

	@Override
	public String getNome() {
		return "non valido";
	}

	@Override
	public String getParametro() {
		return null;
	}
}