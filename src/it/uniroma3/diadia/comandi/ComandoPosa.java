package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


public class ComandoPosa implements Comando {
	
	private IO IO;
	private String nomeAttrezzo;
	
	@Override
	public void esegui (Partita partita) {

		if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) //controlla che ci sia	
    		if(partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo))) {//lo mette nella stanza se possibile
    			partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
    			IO.mostraMessaggio("Hai lasciato l'attrezzo nella stanza!");
    		}
    		else
    			IO.mostraMessaggio("La stanza è piena!");
    	else
    		IO.mostraMessaggio("Attrezzo non trovato nella borsa");
	}

	@Override
	public void setParametro (String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public void setIO(IO IO) {
		this.IO = IO;
	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
}