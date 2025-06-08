package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

    private static final String MESSAGGIO_DONO = "Grazie per il dono! Lo alleggerisco con la mia magia!";
    private static final String MESSAGGIO_AGISCI = "(Con un tocco magico scompare per poi riapparire dall'altra parte della stanza)";
    private static final String MESSAGGIO_SCUSE = "Non è prudente far sparire tutta questa materia dall'universo";

    private boolean modificato;

    public Mago(String nome, String presentazione) {
        super(nome, presentazione);
        modificato = false;
    }

    @Override
    public String agisci(Partita partita) {
        return MESSAGGIO_AGISCI;
    }

    @Override
    public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
        if (!modificato) {
            partita.getStanzaCorrente().addAttrezzo(new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2));
            modificato = true;
            return MESSAGGIO_DONO;
        } 
        else
            return MESSAGGIO_SCUSE;
    }
}
