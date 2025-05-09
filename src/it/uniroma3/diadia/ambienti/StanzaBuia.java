package it.uniroma3.diadia.ambienti;


public class StanzaBuia extends Stanza {
	
	String attrezzo; //nome dell'attrezzo che permette di illuminare la stanza
	
	public StanzaBuia(String nome, String attrezzo) {
		super(nome);
		this.attrezzo = attrezzo;
	}
	
	@Override
    public String getDescrizione() {
        if(!(this.hasAttrezzo(this.attrezzo))) 
            return "Qui c'è buio pesto!";
        
        return super.getDescrizione();
    }
}