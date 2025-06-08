package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;


public class ComandoVai extends AbstractComando{
	
	@Override
	public void esegui(Partita partita) {
	    Stanza stanzaCorrente = partita.getStanzaCorrente();
	    Stanza prossimaStanza = null;

	    if (this.parametro == null) {
	        IO.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
	        return;
	    }

	    IO.mostraMessaggio("Cerco uscita in direzione: '" + this.parametro + "'");

	    Direzione direzione = null;
	    try {
	        direzione = Direzione.fromString(this.parametro);
	    } catch (IllegalArgumentException e) {
	        IO.mostraMessaggio("Direzione non valida");
	        return;
	    }

	    prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
	    if (prossimaStanza == null) {
	        IO.mostraMessaggio("Direzione inesistente");
	        return;
	    }

	    partita.setStanzaCorrente(prossimaStanza);
	    IO.mostraMessaggio(partita.getStanzaCorrente().getNome());
	    partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}

	@Override
	public String getNome() {
		return "vai";
	}
}