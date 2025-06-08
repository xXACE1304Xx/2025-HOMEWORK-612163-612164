package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.StanzaBuia;


public class ComandoPrendi extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		
		StanzaBuia fittizia = new StanzaBuia("fittizia","inesistente") ;
        if(partita.getStanzaCorrente().getDescrizione().equals(fittizia.getDescrizione()))
            IO.mostraMessaggio(fittizia.getDescrizione());
        else if(partita.getStanzaCorrente().hasAttrezzo(this.parametro))//controlla che ci sia	
    		if(partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(this.parametro))) {	
    			partita.getStanzaCorrente().removeAttrezzo(partita.getStanzaCorrente().getAttrezzo(this.parametro)); 
    			IO.mostraMessaggio("Attrezzo preso!");
    		}
    		else
    			IO.mostraMessaggio("Non hai abbastanza spazio nella borsa!");
    	else
    		IO.mostraMessaggio("Attrezzo non trovato nella stanza");
	}

	@Override
	public String getNome() {
		return "prendi";
	}
}