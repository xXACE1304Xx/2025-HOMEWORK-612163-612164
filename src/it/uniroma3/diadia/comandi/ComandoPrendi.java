package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.IO;


public class ComandoPrendi implements Comando{
	
	private IO IO;
	private String parametro;

	@Override
	public void esegui(Partita partita) {
		
		StanzaBuia fittizia = new StanzaBuia("fittizia","inesistente") ;
        if(partita.getStanzaCorrente().getDescrizione().equals(fittizia.getDescrizione()))
            IO.mostraMessaggio(fittizia.getDescrizione());
        else if(partita.getStanzaCorrente().hasAttrezzo(parametro))//controlla che ci sia	
    		if(partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(parametro))) {	
    			partita.getStanzaCorrente().removeAttrezzo(partita.getStanzaCorrente().getAttrezzo(parametro)); 
    			IO.mostraMessaggio("Attrezzo preso!");
    		}
    		else
    			IO.mostraMessaggio("Non hai abbastanza spazio nella borsa!");
    	else
    		IO.mostraMessaggio("Attrezzo non trovato nella stanza");
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public void setIO(IO IO) {
		this.IO = IO;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}
}