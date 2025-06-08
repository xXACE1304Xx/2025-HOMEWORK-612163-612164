package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Strega extends AbstractPersonaggio{

	private static final String MESSAGGIO_SALUTATA = "Hai fatto un grosso errore a venire qui! Spero tu abbia qualcosa per me.";
	private static final String MESSAGGIO_DONO = "Sciocco da parte tua, ora questo lo tengo io!";

	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		return MESSAGGIO_SALUTATA;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_DONO;
	}
}