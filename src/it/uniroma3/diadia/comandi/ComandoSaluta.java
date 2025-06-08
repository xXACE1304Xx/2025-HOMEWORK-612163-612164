package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoSaluta extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio() != null)
			IO.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		else 
			IO.mostraMessaggio("Non c'è nessuno qui...");
	}

	@Override
	public String getNome() {
		return "saluta";
	}
}