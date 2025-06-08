package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaComandiRiflessiva;


public class DiaDia {

    static final private String MESSAGGIO_BENVENUTO = "" +
            "Ti trovi nell'Università, ma oggi è diversa dal solito...\n" +
            "Meglio andare al più presto in biblioteca a studiare. Ma dov'è?\n" +
            "I locali sono popolati da strani personaggi, " +
            "alcuni amici, altri... chissà!\n" +
            "Ci sono attrezzi che potrebbero servirti nell'impresa:\n" +
            "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
            "o regalarli se pensi che possano ingraziarti qualcuno.\n\n" +
            "Per conoscere le istruzioni usa il comando 'aiuto'\n\n";

    private Partita partita;
    private IO IO;

    public DiaDia(Labirinto labirinto, IO IO) {
    	this.IO = IO;
		this.partita = new Partita(labirinto);
    }

    public void gioca() throws Exception {
        String istruzione;      
        
        IO.mostraMessaggio(MESSAGGIO_BENVENUTO + partita.getStanzaCorrente().getNome());
        do {
            istruzione = IO.leggiRiga();
        } while (!processaIstruzione(istruzione));
    }

    private boolean processaIstruzione(String istruzione) throws Exception {
    	
    	Comando comandoDaEseguire;
    	FabbricaComandiRiflessiva factory = new FabbricaComandiRiflessiva(this.IO);
    	comandoDaEseguire = factory.costruisciComando(istruzione);
    	comandoDaEseguire.esegui(this.partita);
    	
    	if (this.partita.vinta())
    		IO.mostraMessaggio("Hai vinto!");
    	if(partita.getGiocatore().getCfu() == 0)
			IO.mostraMessaggio("Hai perso! I CFU sono finiti.");

    	return this.partita.isFinita();
    }
    
    public static void main(String[] argc) throws Exception {
    	
    	IO IO = new IOConsole();
		Labirinto labirinto = Labirinto.newBuilder("C:/Users/Carmen/Desktop/Labirinto.txt").getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, IO);
		gioco.gioca();
	}
}