package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final public int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
	
	protected Map<String, Attrezzo> attrezzi;
    protected int numeroAttrezzi;
    
    private Map<Direzione, Stanza> stanzeAdiacenti;
    private int numeroStanzeAdiacenti;
    
    private AbstractPersonaggio personaggio;
    
    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<>();
        this.personaggio = null;
    }

    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
        boolean aggiornato = false;
    	
        if (stanzeAdiacenti.containsKey(direzione)) {
        	this.stanzeAdiacenti.put(direzione,stanza);
        	aggiornato = true;
        }
    	if (!aggiornato)
    		if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
    			this.stanzeAdiacenti.put(direzione,stanza);
    		    this.numeroStanzeAdiacenti++;
    		}
    }
    
    public List<Stanza> getStanzeAdiacenti() {
		List<Stanza> listaStanzeAdiacenti = new ArrayList<>();
		for (Stanza s : stanzeAdiacenti.values()) {
			listaStanzeAdiacenti.add(s);
		}
		return listaStanzeAdiacenti;
	}

	public Stanza getStanzaAdiacente(Direzione direzione) {
        Stanza stanza = null;
		
        if (this.stanzeAdiacenti.containsKey(direzione))
        	stanza = this.stanzeAdiacenti.get(direzione);
        return stanza;
	}

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.toString();
    }

    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (attrezzo != null && this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
        	this.attrezzi.put(attrezzo.getNome(), attrezzo);
        	this.numeroAttrezzi++;
        	return true;
        }
        
        return false;
    }
    
    public Collection<Attrezzo> getAttrezzi() {
        return this.attrezzi.values();
    }

    public String toString() {
   
    	StringBuilder output = new StringBuilder();
    	output.append(this.nome);
    	output.append("\nUscite: ");
    	output.append(this.getDirezioni().toString());
    	
    	if(numeroAttrezzi != 0)
    		output.append("\nAttrezzi nella stanza: " + this.getAttrezzi().toString());
    	
    	if(this.personaggio != null)
    		output.append("\nC'è " + this.personaggio.getClass().getSimpleName() + " " + this.personaggio.getNome() + " nella stanza, valuta se parlargli");
    	

    	return output.toString();
    }

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato = null;

		if (this.attrezzi.containsKey(nomeAttrezzo))
			attrezzoCercato = this.attrezzi.get(nomeAttrezzo);
		return attrezzoCercato;	
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		
		if(attrezzo!=null && this.attrezzi.containsKey(attrezzo.getNome())){
			this.attrezzi.remove(attrezzo.getNome(), attrezzo);
			numeroAttrezzi--;
			return true;
		}
		
		return false;
	}
	
	public int getNumeroAttrezzi() {
		return numeroAttrezzi;
	}

	public Set<Direzione> getDirezioni() {
		return stanzeAdiacenti.keySet();
	}
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
			
		Stanza s = (Stanza) obj;
		return this.getNome().equals(s.getNome());
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
}