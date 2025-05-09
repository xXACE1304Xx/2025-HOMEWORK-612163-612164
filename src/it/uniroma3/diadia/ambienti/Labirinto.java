package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Labirinto {
    private Stanza entrata;
    private Stanza uscita;
    private Stanza stanzaCorrente;

    public Labirinto() {
        creaStanze();
    }

    private void creaStanze() {

        /* crea gli attrezzi */
        Attrezzo lanterna = new Attrezzo("lanterna", 3);
        Attrezzo osso = new Attrezzo("osso", 1);
        Attrezzo chiave = new Attrezzo("chiave", 1);

        /* crea stanze del labirinto */
        StanzaBloccata atrio = new StanzaBloccata("Atrio", "nord", "chiave");
        Stanza aulaN11 = new Stanza("Aula N11");
        StanzaBuia aulaN10 = new StanzaBuia("Aula N10", "lanterna");
        StanzaMagica laboratorio = new StanzaMagica("Laboratorio Campus");
        Stanza biblioteca = new Stanza("Biblioteca");

        /* collega le stanze */
        atrio.impostaStanzaAdiacente("nord", biblioteca);
        atrio.impostaStanzaAdiacente("est", aulaN11);
        atrio.impostaStanzaAdiacente("sud", aulaN10);
        atrio.impostaStanzaAdiacente("ovest", laboratorio);
        aulaN11.impostaStanzaAdiacente("est", laboratorio);
        aulaN11.impostaStanzaAdiacente("ovest", atrio);
        aulaN10.impostaStanzaAdiacente("nord", atrio);
        aulaN10.impostaStanzaAdiacente("est", aulaN11);
        aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
        laboratorio.impostaStanzaAdiacente("est", atrio);
        laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
        biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
        laboratorio.addAttrezzo(lanterna);
        atrio.addAttrezzo(osso);
        aulaN10.addAttrezzo(chiave) ;

        /* il gioco comincia nell'atrio */
        entrata = atrio;  // Usa la stanza atrio come entrata
        uscita = biblioteca;  // Usa la stanza biblioteca come uscita
        this.setStanzaCorrente(atrio);
    }
    
    public void setStanzaCorrente(Stanza stanzaCorrente) {
    	this.stanzaCorrente = stanzaCorrente;
    }
    
    public Stanza getStanzaCorrente() {
    	return stanzaCorrente;
    }

    public Stanza getEntrata() {
        return entrata;
    }

    public Stanza getUscita() {
        return uscita;
    }
}