package it.uniroma3.diadia.ambienti;


public class StanzaBloccata extends Stanza {
	
	String dirBloccata;
	String chiave;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrSblocco) {
		super(nome);
		this.dirBloccata = direzioneBloccata;
		this.chiave = attrSblocco;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(this.dirBloccata) && !(this.hasAttrezzo(this.chiave))) 
			return this;
		else 
			return super.getStanzaAdiacente(direzione);	
	}

	@Override
	public String getDescrizione() {
		if(!(this.hasAttrezzo(this.chiave))) 
			return super.getDescrizione() + "\nDirezione " + dirBloccata + " bloccata, serve \"" + chiave + "\" per poterci accedere";
		else 
			return super.getDescrizione() + "\nDirezione " + dirBloccata + " sbloccata con \"" + chiave + "\"";	
	}
}