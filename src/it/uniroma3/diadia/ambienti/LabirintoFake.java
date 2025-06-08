package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoFake extends Labirinto {

    public LabirintoFake() {
        super((Void) null);

        Stanza atrio = new Stanza("Atrio");
        Stanza biblioteca = new Stanza("Biblioteca");
        Stanza laboratorio = new Stanza("Laboratorio");

        atrio.impostaStanzaAdiacente(Direzione.nord, biblioteca);
        biblioteca.impostaStanzaAdiacente(Direzione.sud, atrio);
        biblioteca.impostaStanzaAdiacente(Direzione.est, laboratorio);
        laboratorio.impostaStanzaAdiacente(Direzione.ovest, biblioteca);

        atrio.addAttrezzo(new Attrezzo("lanterna", 1));
        biblioteca.addAttrezzo(new Attrezzo("libro", 2));
        laboratorio.addAttrezzo(new Attrezzo("chiave", 1));


        this.setEntrata(atrio);
        this.setUscita(laboratorio);
        this.setStanzaCorrente(atrio);
    }
}