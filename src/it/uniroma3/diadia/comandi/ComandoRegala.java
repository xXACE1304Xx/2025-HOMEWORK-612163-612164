package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoRegala extends AbstractComando{
	
	@Override
	public void esegui(Partita partita) {

		if(partita.getStanzaCorrente().getPersonaggio()!=null) {
			if(this.getParametro() == null) 
				IO.mostraMessaggio("Devi specificare un oggetto da regalare");
			else {
				Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());

				if(attrezzo!=null) {
					IO.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzo, partita));	
					partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
				}
				else {
					IO.mostraMessaggio("Attrezzo non trovato");
				}	
			}
		}
		else {
			IO.mostraMessaggio("Non ci sono personaggi nella stanza, nulla da regalare");
		}
	}

	@Override
	public String getNome() {
		return "regala";
	}
}