package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;


public class Labirinto {
	
	private Stanza entrata;
    private Stanza uscita;
    private Stanza stanzaCorrente;

    private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.entrata = c.getStanzaIniziale();
		this.setStanzaCorrente(entrata);
		this.uscita = c.getStanzaVincente();
	}

	public Labirinto(Void void1) {}

	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}
    
    public static class LabirintoBuilder {
        private Map<String, Stanza> nome2stanza;
        private Labirinto labirinto;;
        
        public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(labirinto);
			this.nome2stanza = new HashMap<>();
		}

        public LabirintoBuilder addStanza(String nome) {
            Stanza stanza = new Stanza(nome);
            this.nome2stanza.put(nome, stanza);
            return this;
        }

        public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) {
            Stanza stanza = nome2stanza.get(nomeStanza);
            if (stanza != null) 
                stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
            return this;
        }

        public LabirintoBuilder addAdiacenza(String da, String a, Direzione direzione) {
    		Stanza DA = this.nome2stanza.get(da);
    		Stanza A = this.nome2stanza.get(a);
    		DA.impostaStanzaAdiacente(direzione, A);
    		return this;
    	}
        
        public Labirinto getLabirinto() {
			return this.labirinto;
		}
        
        public LabirintoBuilder setEntrata(String nomeStanza) {
            Stanza stanza = this.nome2stanza.get(nomeStanza);
            if (stanza != null)
                this.labirinto.entrata = stanza;
            return this;
        }

        public LabirintoBuilder setUscita(String nomeStanza) {
            Stanza stanza = this.nome2stanza.get(nomeStanza);
            if (stanza != null)
                this.labirinto.uscita = stanza;
            return this;
        }
        
        public LabirintoBuilder addMago(String stanza, String nome, String presentazione) {
            Stanza s = this.nome2stanza.get(stanza);
            if (s != null) 
                s.setPersonaggio(new Mago(nome, presentazione));
           
            return this;
        }

        public LabirintoBuilder addCane(String stanza, String nome, String presentazione, String attrezzo, int peso, String ciboPreferito) {
            Stanza s = this.nome2stanza.get(stanza);
            if (s != null)
                s.setPersonaggio(new Cane(nome, presentazione, ciboPreferito, new Attrezzo(attrezzo, peso)));
      
            return this;
        }

        public LabirintoBuilder addStrega(String stanza, String nome, String presentazione) {
            Stanza s = this.nome2stanza.get(stanza);
            if (s != null) 
                s.setPersonaggio(new Strega(nome, presentazione));
            
            return this;
        }
        
        public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoSbloccante, String direzioneBloccata) {
			
        	StanzaBloccata stanza = new StanzaBloccata(nome, Direzione.valueOf(direzioneBloccata.toUpperCase()), attrezzoSbloccante);
        	this.nome2stanza.put(nome, stanza);
        	return this;
		}
        
        public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
        	this.nome2stanza.put(nome, new StanzaBuia(nome, attrezzoPerVedere));
            return this;
		}
        
        public LabirintoBuilder addStanzaMagica(String nome) {
        	this.nome2stanza.put(nome, new StanzaMagica(nome));
            return this;
		}
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
    
    protected void setEntrata(Stanza stanza) {
        this.entrata = stanza;
    }

    protected void setUscita(Stanza stanza) {
        this.uscita = stanza;
    }
}