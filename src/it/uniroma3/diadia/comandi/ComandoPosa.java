package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;


public class ComandoPosa extends AbstractComando {
	
	@Override
	public void esegui (Partita partita) {

		if(partita.getGiocatore().getBorsa().hasAttrezzo(this.parametro)) //controlla che ci sia	
    		if(partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().getAttrezzo(this.parametro))) {//lo mette nella stanza se possibile
    			partita.getGiocatore().getBorsa().removeAttrezzo(this.parametro);
    			IO.mostraMessaggio("Hai lasciato l'attrezzo nella stanza!");
    		}
    		else
    			IO.mostraMessaggio("La stanza è piena!");
    	else
    		IO.mostraMessaggio("Attrezzo non trovato nella borsa");
	}

	@Override
	public String getNome() {
		return "posa";
	}
}