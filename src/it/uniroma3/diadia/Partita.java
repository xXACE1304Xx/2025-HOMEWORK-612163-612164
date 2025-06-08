package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;


public class Partita {

    private boolean finita;
    private Giocatore giocatore;
    private Labirinto labirinto;

    public Partita(Labirinto labirinto) {
    	this.finita = false;
    	this.labirinto = labirinto;
        this.giocatore = new Giocatore();
    }

    public Stanza getStanzaVincente() {
        return labirinto.getUscita();
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.labirinto.setStanzaCorrente(stanzaCorrente);
    }

    public Stanza getStanzaCorrente() {
        return this.labirinto.getStanzaCorrente();
    }
    
    public Giocatore getGiocatore() {
        return this.giocatore;
    }
    
    public boolean vinta() {
        return this.getStanzaCorrente() == this.getStanzaVincente();
    }

    public boolean isFinita() {
        return finita || vinta() || (this.giocatore.getCfu() == 0);
    }

    public void setFinita() {
        this.finita = true;
    }
}