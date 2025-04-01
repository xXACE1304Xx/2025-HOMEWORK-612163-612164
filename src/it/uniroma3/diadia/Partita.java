package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

    private boolean finita;
    private Stanza stanzaCorrente;
    private Giocatore giocatore;
    private Labirinto labirinto;

    public Partita(){
        this.labirinto = new Labirinto();  // Creazione del labirinto
        this.stanzaCorrente = labirinto.getEntrata();  // Imposta la stanza iniziale
        this.finita = false;
        this.giocatore = new Giocatore();  // Crea un giocatore
    }

    public Stanza getStanzaVincente() {
        return labirinto.getUscita();
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public Stanza getStanzaCorrente() {
        return this.stanzaCorrente;
    }
    
    public Giocatore getGiocatore() {
        return this.giocatore;
    }
    
    /**
     * Restituisce vero se e solo se la partita è stata vinta
     * @return vero se partita vinta
     */
    public boolean vinta() {
        return this.getStanzaCorrente() == this.getStanzaVincente();
    }

    /**
     * Restituisce vero se e solo se la partita è finita
     * @return vero se partita finita
     */
    public boolean isFinita() {
        return finita || vinta() || (this.giocatore.getCfu() == 0);
    }

    /**
     * Imposta la partita come finita
     */
    public void setFinita() {
        this.finita = true;
    }
}