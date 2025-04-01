package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe principale di Diadia, un semplice gioco di ruolo ambientato all'università.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca.
 *
 * Questa è la classe principale che crea e istanzia tutte le altre.
 *
 * @author  docente di POO
 *          (da un'idea di Michael Kolling and David J. Barnes)
 *
 * @version base
 */

public class DiaDia {

    static final private String MESSAGGIO_BENVENUTO = "" +
            "Ti trovi nell'Università, ma oggi è diversa dal solito...\n" +
            "Meglio andare al più presto in biblioteca a studiare. Ma dov'è?\n" +
            "I locali sono popolati da strani personaggi, " +
            "alcuni amici, altri... chissà!\n" +
            "Ci sono attrezzi che potrebbero servirti nell'impresa:\n" +
            "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
            "o regalarli se pensi che possano ingraziarti qualcuno.\n\n" +
            "Per conoscere le istruzioni usa il comando 'aiuto'\n" +
    		"e 'borsa' per visualizzarne il contenuto.\n\n";
    
    static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "borsa", "fine"};

    private Partita partita;
    private Giocatore giocatore;
    private IOConsole IO;

    public DiaDia() {
        this.partita = new Partita();
        this.giocatore = partita.getGiocatore();
        this.IO = new IOConsole();
    }

    public void gioca() {
        String istruzione;      
        
        IO.mostraMessaggio(MESSAGGIO_BENVENUTO + partita.getStanzaCorrente());
        do {
            istruzione = IO.leggiRiga().trim();
            if(istruzione.equals(""))
            	istruzione = "aiuto" ;
        } while (!processaIstruzione(istruzione));
    }

    /**
     * Processa una istruzione
     *
     * @return true se l'istruzione è eseguita e il gioco continua, false altrimenti
     */
    private boolean processaIstruzione(String istruzione) {
        Comando comandoDaEseguire = new Comando(istruzione);
        
        switch(comandoDaEseguire.getNome()) { 
        
        	case "fine": this.fine(); return true;
        	case "vai": this.vai(comandoDaEseguire.getParametro()); break;
        	case "prendi": this.prendi(comandoDaEseguire.getParametro()); break;
        	case "posa": this.posa(comandoDaEseguire.getParametro()); break;
        	case "borsa": this.borsa(); break;
        	case "aiuto": this.aiuto(); break;
        	default: IO.mostraMessaggio("Comando sconosciuto");	
        }

        if (this.partita.isFinita())
            return true;
        return false;
    }

    private void prendi(String attrezzo) {
    	
    	if(this.partita.getStanzaCorrente().hasAttrezzo(attrezzo))//controlla che ci sia	
    		if(this.giocatore.getBorsa().addAttrezzo(this.partita.getStanzaCorrente().getAttrezzo(attrezzo))) {//lo mette nella borsa se possibile		
    			this.partita.getStanzaCorrente().removeAttrezzo(this.partita.getStanzaCorrente().getAttrezzo(attrezzo)); //lo rimuove dalla stanza
    			IO.mostraMessaggio("Attrezzo preso!");
    		}
    		else
    			IO.mostraMessaggio("Non hai abbastanza spazio nella borsa!");
    	else
    		IO.mostraMessaggio("Attrezzo non trovato nella stanza");
    }
    
    
    private void posa(String nomeAttrezzo) {
    	
    	if(this.giocatore.getBorsa().hasAttrezzo(nomeAttrezzo)) //controlla che ci sia	
    		if(this.partita.getStanzaCorrente().addAttrezzo(giocatore.getBorsa().getAttrezzo(nomeAttrezzo))) {//lo mette nella stanza se possibile
    			this.giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
    			IO.mostraMessaggio("Hai lascaito l'attrezzo nella stanza!");
    		}
    		else
    			IO.mostraMessaggio("La stanza è piena!");
    	else
    		IO.mostraMessaggio("Attrezzo non trovato nella borsa");
    }
    
    
    private void borsa() {
    	
    	IO.mostraMessaggio(this.giocatore.getBorsa().toString());
    }
    
    /**
     * Stampa informazioni di aiuto.
     */
    private void aiuto() {
    	IO.mostraMessaggio("Questi sono i comandi che hai a disposizione:");
        for (int i = 0; i < elencoComandi.length; i++)
        	IO.mostraMessaggio("-"+elencoComandi[i]);
    }

    /**
     * Cerca di andare in una direzione. Se c'è una stanza ci entra
     * e ne stampa il nome, altrimenti stampa un messaggio di errore
     */
    private void vai(String direzione) {
        if (direzione == null || direzione.trim().isEmpty()) {
        	IO.mostraMessaggio("Dove vuoi andare?");
            return;
        }

        // Normalizza l'input
        direzione = direzione.trim().toLowerCase(); // Assicurati che l'input sia in minuscolo

        Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);

        if (prossimaStanza == null) {
        	IO.mostraMessaggio("Direzione inesistente");
        } else {
            this.partita.setStanzaCorrente(prossimaStanza);
            int cfu = this.giocatore.getCfu();
            this.giocatore.setCfu(cfu - 1);

            // Mostra la descrizione della stanza
            IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());

            // Verifica se la partita è finita
            if (this.partita.vinta()) {
            	IO.mostraMessaggio("Hai vinto!");
            } else if (this.partita.isFinita()) {
            	IO.mostraMessaggio("Hai perso! I CFU sono finiti.");
            }
        }
    }

    /**
     * Comando "Fine".
     */
    private void fine() {
    	IO.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
    }
    
    public static void main(String[] argc) {
        DiaDia gioco = new DiaDia();
        gioco.gioca();
    }
}