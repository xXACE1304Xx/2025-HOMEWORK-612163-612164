package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoAiuto extends AbstractComando {
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "guarda", "saluta", "interagisci", "regala", "fine"};
	
	@Override
	public void esegui(Partita partita) {
		IO.mostraMessaggio("Questi sono i comandi che hai a disposizione:");
        for (int i = 0; i < elencoComandi.length; i++)
        	IO.mostraMessaggio("-"+elencoComandi[i]);
	}

	@Override
	public String getNome() {
		return "aiuto";
	}
}