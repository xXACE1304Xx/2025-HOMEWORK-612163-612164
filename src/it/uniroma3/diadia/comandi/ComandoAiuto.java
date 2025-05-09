package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoAiuto implements Comando {
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "guarda", "fine"};
	private IO IO;
	
	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio("Questi sono i comandi che hai a disposizione:");
        for (int i = 0; i < elencoComandi.length; i++)
        	IO.mostraMessaggio("-"+elencoComandi[i]);
	}

	@Override
	public void setParametro (String parametro) {}

	@Override
	public void setIO(IO IO) {
		this.IO = IO;
	}

	@Override
	public String getNome() {
		return "aiuto";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}