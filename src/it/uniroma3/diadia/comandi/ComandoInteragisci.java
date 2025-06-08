package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


public class ComandoInteragisci extends AbstractComando{
	
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";

	private String messaggio;
	
	@Override
	public void esegui(Partita partita) {
		
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			this.messaggio = personaggio.agisci(partita);
			IO.mostraMessaggio(this.messaggio);
		} 
		else 
			IO.mostraMessaggio(MESSAGGIO_CON_CHI);
	}

	@Override
	public String getNome() {
		return "Interagisci";
	}
}