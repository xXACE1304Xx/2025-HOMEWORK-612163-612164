package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;


public class CaricatoreLabirinto {

	private static final String STANZE_MARKER = "Stanze:";             
	private static final String STANZA_INIZIALE_MARKER = "Entrata:";  
	private static final String STANZA_VINCENTE_MARKER = "Uscita:";  
	private static final String ATTREZZI_MARKER = "Attrezzi:";
	private static final String PERSONAGGI_MARKER = "Personaggi:";
	private static final String STANZE_SPECIALI_MARKER = "Speciali:";
	private static final String USCITE_MARKER = "Collegamenti:";

    private LineNumberReader reader;
    private Map<String, Stanza> nome2stanza;
    private Stanza stanzaIniziale;
    private Stanza stanzaVincente;

    public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
        this.nome2stanza = new HashMap<>();
        this.reader = new LineNumberReader(new FileReader(nomeFile));
    }
	public CaricatoreLabirinto(StringReader reader) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(reader);
	}
	
	public CaricatoreLabirinto(InputStreamReader inputStreamReader) {
	    this.nome2stanza = new HashMap<>();
	    this.reader = new LineNumberReader(inputStreamReader);
	}


    public void carica() throws FormatoFileNonValidoException {
        try {
            this.leggiECreaStanze();
            this.leggiECreaStanzeSpeciali();
            this.leggiEntrataUscita();
            this.leggiECollocaAttrezzi();
            this.leggiEImpostaUscite();
            this.leggiEInserisciPersonaggi();
        } finally {
            try { reader.close(); } 
            catch (IOException e) { throw new RuntimeException(e); }
        }
    }

    private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
        try {
            String riga = reader.readLine();
            check(riga != null && riga.startsWith(marker), "era attesa una riga che cominciasse per "+marker);
            String payload = riga.substring(marker.length()).trim();
            return payload;
        } catch (IOException e) {
            throw new FormatoFileNonValidoException(e.getMessage());
        }
    }

    private void leggiECreaStanze() throws FormatoFileNonValidoException {
        String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
        for (String nome : separaStringheAlleVirgole(nomiStanze)) {
            Stanza stanza = new Stanza(nome);
            nome2stanza.put(nome, stanza);
        }
    }
    
    private void leggiECreaStanzeSpeciali() throws FormatoFileNonValidoException {
        String specifiche = this.leggiRigaCheCominciaPer(STANZE_SPECIALI_MARKER);
        for (String spec : separaStringheAlleVirgole(specifiche)) {
            try (Scanner sc = new Scanner(spec)) {
                String tipo = getNext(sc, "tipo stanza speciale");
                String nome = getNext(sc, "nome stanza");
                
                Stanza stanza = null;

                switch (tipo.toLowerCase()) {
                    case "buia":
                        String attrezzo = getNext(sc, "attrezzo per vedere");
                        stanza = new StanzaBuia(nome, attrezzo);
                        break;
                    case "bloccata":
                        String direzione = getNext(sc, "direzione bloccata");
                        String attrezzoSbloccante = getNext(sc, "attrezzo sbloccante");
                        stanza = new StanzaBloccata(nome, Direzione.fromString(direzione), attrezzoSbloccante);
                        break;
                    case "magica":
                        stanza = new StanzaMagica(nome);
                        break;
                    default:
                        throw new FormatoFileNonValidoException("Tipo stanza speciale sconosciuto: " + tipo);
                }

                nome2stanza.put(nome, stanza);
            }
        }
    }

    private List<String> separaStringheAlleVirgole(String s) {
        List<String> result = new LinkedList<>();
        try (Scanner sc = new Scanner(s)) {
            sc.useDelimiter(",");
            while (sc.hasNext()) {
                String token = sc.next().trim();
                result.add(token);
            }
        }
        return result;
    }

    private void leggiEntrataUscita() throws FormatoFileNonValidoException {
        String inizio = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
        check(isStanzaValida(inizio), inizio+" non definita");
        String vincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
        check(isStanzaValida(vincente), vincente+" non definita");

        stanzaIniziale  = nome2stanza.get(inizio);
        stanzaVincente  = nome2stanza.get(vincente);
    }

    private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
        String specifiche = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
        for (String spec : separaStringheAlleVirgole(specifiche)) {
            try (Scanner sc = new Scanner(spec)) {
                String nome = getNext(sc,"nome attrezzo");
                String peso = getNext(sc,"peso attrezzo "+nome);
                String stanza = getNext(sc,"stanza attrezzo "+nome);
                posaAttrezzo(nome, peso, stanza);
            }
        }
    }

    private String getNext(Scanner sc, String cosa) throws FormatoFileNonValidoException {
        check(sc.hasNext(), msgTerminazionePrecoce(cosa));
        return sc.next();
    }

    private void posaAttrezzo(String nome, String pesoStr, String stanza) throws FormatoFileNonValidoException {
        try {
            int peso = Integer.parseInt(pesoStr);
            Attrezzo att = new Attrezzo(nome, peso);
            check(isStanzaValida(stanza), "stanza "+stanza+" inesistente");
            nome2stanza.get(stanza).addAttrezzo(att);
        } catch (NumberFormatException e) {
            check(false, "Peso attrezzo "+nome+" non valido");
        }
    }

    private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
        String specifiche = this.leggiRigaCheCominciaPer(USCITE_MARKER);
        for (String spec : separaStringheAlleVirgole(specifiche)) {
            try (Scanner sc = new Scanner(spec)) {
                String da   = getNext(sc,"stanza di partenza");
                String dir  = getNext(sc,"direzione uscita di "+da);
                String a    = getNext(sc,"stanza destinazione da "+da);
                impostaUscita(da, dir, a);
            }
        }
    }

    private void impostaUscita(String da, String dir, String a) throws FormatoFileNonValidoException {
        check(isStanzaValida(da), "Stanza di partenza sconosciuta "+da);
        check(isStanzaValida(a),  "Stanza di destinazione sconosciuta "+a);
        Stanza partenza = nome2stanza.get(da);
        Stanza arrivo   = nome2stanza.get(a);
        partenza.impostaStanzaAdiacente(Direzione.fromString(dir), arrivo);
    }
    
    private void leggiEInserisciPersonaggi() throws FormatoFileNonValidoException {
        String specifiche = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
        for (String spec : separaStringheAlleVirgole(specifiche)) {
            try {
                int idxTipo = spec.indexOf(' ');
                int idxNome = spec.indexOf(' ', idxTipo + 1);
                String tipo = spec.substring(0, idxTipo).trim();
                String nome = spec.substring(idxTipo + 1, idxNome).trim();

                int idxInizioQuote = spec.indexOf('"', idxNome);
                int idxFineQuote = spec.indexOf('"', idxInizioQuote + 1);
                check(idxInizioQuote != -1 && idxFineQuote != -1, "presentazione mancante o malformata");

                String presentazione = spec.substring(idxInizioQuote + 1, idxFineQuote);

                String resto = spec.substring(idxFineQuote + 1).trim();
                String[] parti = resto.split("\\s+");
                String stanza = parti[0];

                AbstractPersonaggio personaggio = null;

                switch (tipo.toLowerCase()) {
                    case "mago":
                        personaggio = new Mago(nome, presentazione);
                        break;
                    case "strega":
                        personaggio = new Strega(nome, presentazione);
                        break;
                    case "cane":
                        check(parti.length >= 4, "parametri mancanti per cane");
                        String nomeAttrezzo = parti[1];
                        int peso = Integer.parseInt(parti[2]);
                        String cibo = parti[3];
                        personaggio = new Cane(nome, presentazione, cibo, new Attrezzo(nomeAttrezzo, peso));
                        break;
                    default:
                        throw new FormatoFileNonValidoException("Tipo personaggio sconosciuto: " + tipo);
                }

                check(isStanzaValida(stanza), "stanza " + stanza + " non definita");
                nome2stanza.get(stanza).setPersonaggio(personaggio);

            } catch (Exception e) {
                throw new FormatoFileNonValidoException("Errore nel parsing personaggi: " + e.getMessage());
            }
        }
    }


    private boolean isStanzaValida(String nome) { return nome2stanza.containsKey(nome); }

    private String msgTerminazionePrecoce(String msg) {
        return "Terminazione precoce del file prima di leggere "+msg;
    }

    private void check(boolean cond, String msg) throws FormatoFileNonValidoException {
        if (!cond)
            throw new FormatoFileNonValidoException(
               "Formato file non valido ["+reader.getLineNumber()+"] "+msg);
    }

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}