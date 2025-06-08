package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

    private static final String MESSAGGIO_MORSO = "OH NO! Il cane ti ha morso! Hai perso 1 CFU.";
    private static final String MESSAGGIO_CIBO_ACCETTATO = "Il cane scodinzola felice e lascia cadere qualcosa a terra!";
    private static final String MESSAGGIO_GIA_PREMIO_DATO = "Il cane ha già ricevuto il suo premio.";

    private final String ciboPreferito;
    private Attrezzo premio;

    public Cane(String nome, String presentazione, String ciboPreferito, Attrezzo premio) {
        super(nome, presentazione);
        this.ciboPreferito = ciboPreferito;
        this.premio = premio;
    }

    @Override
    public String agisci(Partita partita) {
        partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
        return MESSAGGIO_MORSO;
    }

    @Override
    public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
        if (attrezzo.getNome().equalsIgnoreCase(this.ciboPreferito)) {
            if (this.premio != null) {
                partita.getStanzaCorrente().addAttrezzo(this.premio);
                this.premio = null; 
                return MESSAGGIO_CIBO_ACCETTATO;
            } 
            else 
                return MESSAGGIO_GIA_PREMIO_DATO;  
        } 
        else 
            return this.agisci(partita);      
    }
}